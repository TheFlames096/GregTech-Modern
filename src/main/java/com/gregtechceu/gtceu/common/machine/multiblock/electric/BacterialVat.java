package com.gregtechceu.gtceu.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.item.PetriDishItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.TieredWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.gregtechceu.gtceu.data.recipe.misc.BacterialVatLoader;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import appeng.libs.micromark.commonmark.Content;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.gregtechceu.gtceu.api.GTValues.V;
import static com.gregtechceu.gtceu.common.data.GTItems.PETRI_DISH;
import static com.gregtechceu.gtceu.common.data.GTItems.STERILIZED_PETRI_DISH;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author KilaBash
 * @date 2023/7/23
 * @implNote ProcessingArrayMachine
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BacterialVat extends WorkableElectricMultiblockMachine implements IMachineModifyDrops{

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(BacterialVat.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Persisted @DescSynced
    public final NotifiableItemStackHandler machineStorage;


    public BacterialVat(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.machineStorage = createMachineStorage(args);
    }

    //////////////////////////////////////
    //*****     Initialization    ******//
    //////////////////////////////////////
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    protected NotifiableItemStackHandler createMachineStorage(Object... args) {
        var storage = new NotifiableItemStackHandler(this, 1, IO.NONE, IO.NONE, slots -> new ItemStackTransfer(1) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        });
        storage.setFilter(this::isPetriDish);
        return storage;
    }

    protected boolean isPetriDish(ItemStack itemStack) {
        return itemStack.getItem() instanceof PetriDishItem;
    }

    @Nullable
    public String getCulture() {
        if(!(machineStorage.storage.getStackInSlot(0).getCount()>0))
            return null;
        return ((PetriDishItem)(machineStorage.storage.getStackInSlot(0).getItem())).containedCulture;
    }

    @Override
    @Nonnull
    public GTRecipeType[] getRecipeTypes() {
        return new GTRecipeType[]{GTRecipeTypes.BACTERIAL_VAT_RECIPES};
    }

    @NotNull
    @Override
    public GTRecipeType getRecipeType() {
        if(machineStorage.storage.getStackInSlot(0).getCount()==0)
        {
            return GTRecipeTypes.DUMMY_RECIPES;
        }
        return ((PetriDishItem)(machineStorage.storage.getStackInSlot(0).getItem())).getRecipe();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            machineStorage.addChangedListener(this::onMachineChanged);
        }
    }

    protected void onMachineChanged() {
        if (isFormed) {
            if (getRecipeLogic().getLastRecipe() != null) {
                getRecipeLogic().markLastRecipeDirty();
            }
            getRecipeLogic().updateTickSubscription();
        }
    }

    @Override
    public void onDrops(List<ItemStack> drops, Player entity) {
        clearInventory(drops, machineStorage.storage);
    }


    public int getSievert()
    {
        return 0;
    }
    //////////////////////////////////////
    //*******    Recipe Logic    *******//
    //////////////////////////////////////

    public long getOutputCapacity()
    {
        long output_limit=0;
        for(IMultiPart part : getParts())
        {
            if(part instanceof FluidHatchPartMachine fluidHatch && fluidHatch.io==IO.OUT)
            {
                for(int i=0;i<fluidHatch.tank.getTanks();i++)
                {
                    output_limit+=fluidHatch.tank.getTankCapacity(i);
                }
            }
        }
        GTCEu.LOGGER.info("Output Limit: "+output_limit);
        return output_limit;
    }

    public long getUsedCapacity(Fluid type)
    {
        long used_amount=0;

        for(IMultiPart part : getParts())
        {
            if(part instanceof FluidHatchPartMachine fluidHatch && fluidHatch.io==IO.OUT)
            {
                
                for(int i=0;i<fluidHatch.tank.getTanks();i++)
                {
                    if(fluidHatch.tank.getFluidInTank(i).getRawFluid()==type)
                    {
                        used_amount+=fluidHatch.tank.getFluidInTank(i).getAmount();
                    }
                }
            }
        }
        GTCEu.LOGGER.info("Used Amount:"+used_amount);
        return used_amount;
    }

    public long getLeftCapacity(Fluid type)
    {
        long output_limit=0;
        for(IMultiPart part : getParts())
        {
            if(part instanceof FluidHatchPartMachine fluidHatch && fluidHatch.io==IO.OUT)
            {
                for(int i=0;i<fluidHatch.tank.getTanks();i++)
                {
                    if(fluidHatch.tank.getFluidInTank(i).isEmpty())
                    {
                        output_limit+=fluidHatch.tank.getTankCapacity(i);
                    }
                    if(fluidHatch.tank.getFluidInTank(i).getRawFluid()==type)
                    {
                        output_limit+=(fluidHatch.tank.getTankCapacity(i)-fluidHatch.tank.getFluidInTank(i).getAmount());
                    }
                }
            }
        }
        GTCEu.LOGGER.info("LeftCapacity:"+output_limit);
        return output_limit;
    }
    public int getMaxParallel(Object output)
    {
        FluidIngredient fluidoutput=(FluidIngredient) output;
        Fluid fluidtype= Objects.requireNonNull(fluidoutput.getStacks()[0].getRawFluid());
        long x=getUsedCapacity(fluidtype);
        long y=getOutputCapacity()/2;
        return Math.max((int)Math.min(getLeftCapacity(fluidtype)/fluidoutput.getAmount(),(int)Math.ceil((-1D / y * Math.pow(x - y, 2D) + y) / y * 1001D)),1);

    }

    public GTRecipe parallelRecipe(GTRecipe recipe,int parallel)
    {
        var temp1=recipe.inputs.get(FluidRecipeCapability.CAP);
        var temp2=recipe.outputs.get(FluidRecipeCapability.CAP);
        for(int i = 0; i < temp1.size(); i++)
        {
            var fluid = temp1.get(i);
            ((FluidIngredient)fluid.content).setAmount(((FluidIngredient)fluid.content).getAmount()*parallel);
            temp1.set(i, fluid);
        }
        for (int i = 0; i < temp2.size(); i++) {
            var fluid = temp2.get(i);
            ((FluidIngredient)fluid.content).setAmount(((FluidIngredient)fluid.content).getAmount()*parallel);
            temp2.set(i, fluid);
        }
        recipe.inputs.put(FluidRecipeCapability.CAP, temp1);
        recipe.outputs.put(FluidRecipeCapability.CAP, temp2);
        return recipe;
    }
    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @Nonnull GTRecipe original) {
        if (machine instanceof BacterialVat bacterialvat) {
            var recipe=original.copy();
            if(recipe.data.getBoolean("is_exact"))
            {
                if(recipe.data.getInt("min_sievert")!=bacterialvat.getSievert())
                {
                    GTCEu.LOGGER.info("mismatch sievert");
                    return null;
                }
            }
            else
            {
                if(recipe.data.getInt("min_sievert")>bacterialvat.getSievert())
                {
                    GTCEu.LOGGER.info("mismatch sievert");
                    return null;
                }
            }
            int contentFluidAmount = 0;
            Fluid fluidtype=((FluidIngredient)(recipe.getInputContents(FluidRecipeCapability.CAP).get(0).getContent())).getStacks()[0].getRawFluid();
            for(IMultiPart part : bacterialvat.getParts())
            {
                if(part instanceof FluidHatchPartMachine fluidHatch && fluidHatch.io==IO.IN)
                {
                    for(int i=0;i<fluidHatch.tank.getTanks();i++)
                    {
                        if(fluidtype==fluidHatch.tank.getFluidInTank(i).getRawFluid())
                        {
                            contentFluidAmount+=fluidHatch.tank.getFluidInTank(i).getAmount();
                        }
                    }
                }
            } 
            if(((FluidIngredient)(recipe.getInputContents(FluidRecipeCapability.CAP).get(0).getContent())).isEmpty())
            {
                return null;
            }

            int parallel = (int)(contentFluidAmount/((FluidIngredient)(recipe.getInputContents(FluidRecipeCapability.CAP).get(0).getContent())).getAmount());

            parallel=Math.min(parallel,bacterialvat.getMaxParallel((FluidIngredient) recipe.getOutputContents(FluidRecipeCapability.CAP).get(0).getContent()));
            if(parallel == 0)
            {
                return null;
            }
            recipe=Objects.requireNonNull(bacterialvat.parallelRecipe(recipe,parallel));
            
            recipe = RecipeHelper.applyOverclock(OverclockingLogic.NON_PERFECT_OVERCLOCK, recipe,bacterialvat.getMaxVoltage());
            return recipe;
        }
        return null;
    }

    //////////////////////////////////////
    //********        Gui       ********//
    //////////////////////////////////////

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if(getCulture()!=null)
        {
            textList.add(Component.translatable("gtceu.machine.culture",getCulture()));
        }
        textList.add(Component.translatable("gtceu.machine.sievert",getSievert()));
        if (isActive()) {
            textList.add(Component.translatable("gtceu.machine.machine_hatch.locked").withStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
        }
    }

    @Override
    public Widget createUIWidget() {
        var widget =  super.createUIWidget();
        if (widget instanceof WidgetGroup group) {
            var size = group.getSize();
            group.addWidget(new SlotWidget(machineStorage.storage, 0, size.width - 30, size.height - 30, true, true)
                    .setBackground(GuiTextures.SLOT));
        }
        return widget;
    }

    //////////////////////////////////////
    //********     Structure    ********//
    //////////////////////////////////////
    public static Block getCasingState(int tier) {
        if (tier <= GTValues.IV) {
            return GTBlocks.CASING_TUNGSTENSTEEL_ROBUST.get();
        } else {
            return GTBlocks.CASING_HSSE_STURDY.get();
        }
    }

}
