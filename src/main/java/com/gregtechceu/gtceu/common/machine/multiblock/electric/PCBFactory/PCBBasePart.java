package com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.nanites;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Gold;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Silver;

import java.util.HashSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.IControllable;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.item.DataSaverItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.PCBAccuracyConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic.Status;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.BacterialVat;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import oshi.software.os.OSProcess.State;

public class PCBBasePart extends WorkableElectricMultiblockMachine implements IMachineModifyDrops{

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(PCBBasePart.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Persisted @DescSynced
    public final NotifiableItemStackHandler machineStorage;

    private Set<PCBAttachment> lastAttached = new HashSet<PCBAttachment>();
    PCBEnhancer enhancer=null;
    PCBOverclocker overclocker=null;
    PCBBiochamber biochamber=null;

    @Persisted @DescSynced
    public int accuracy;

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public PCBBasePart(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.machineStorage = createMachineStorage(args);
        this.accuracy=100;
    }
    protected NotifiableItemStackHandler createMachineStorage(Object... args) {
        var storage = new NotifiableItemStackHandler(this, 3, IO.NONE, IO.NONE, slots -> new ItemStackTransfer(3) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        });
        storage.setFilter(this::isDataStick);
        return storage;
    }

    protected boolean isDataStick(ItemStack itemStack) {
        return itemStack.getItem() instanceof DataSaverItem;
    }
    @Override
    public void onDrops(List<ItemStack> drops, Player entity) {
        clearInventory(drops, machineStorage.storage);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            machineStorage.addChangedListener(this::onMachineChanged);
        }
    }
    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        for(int i=0;i<3;i++)
        {
            MetaMachine substructre=getMachineFromDataStick(machineStorage.storage.getStackInSlot(i),getLevel());
            if(substructre==null || !(substructre instanceof PCBAttachment))
            {
                continue;
            }
            ((PCBAttachment)substructre).attachedMachine.remove(this);
        }
    }

    protected void onMachineChanged() {
        for(var machine : lastAttached)
        {
            machine.attachedMachine.remove(this);
        }
        for(int i=0;i<3;i++)
        {
            MetaMachine substructre=getMachineFromDataStick(machineStorage.storage.getStackInSlot(i),getLevel());
            if(substructre==null || !(substructre instanceof PCBAttachment))
            {
                continue;
            }
            ((PCBAttachment)substructre).attachedMachine.add(this);
            lastAttached.add((PCBAttachment)substructre);
        }
        this.recipeLogic.markLastRecipeDirty();
    }
    //////////////////////////////////////
    //********        Gui       ********//
    //////////////////////////////////////

    public static MetaMachine getMachineFromDataStick(ItemStack datastick,Level level)
    {
        if(datastick.getTag()==null)
            return null;
        if(datastick.getTag().get("coordinate")!=null)
            return MetaMachine.getMachine(level,new BlockPos(datastick.getTag().getCompound("coordinate").getInt("x"),datastick.getTag().getCompound("coordinate").getInt("y"),datastick.getTag().getCompound("coordinate").getInt("z")));
        return null;
    }

    private int countItem(UnificationEntry item)
    {
        int count=0;
        for(IMultiPart part : getParts())
        {
            if(part instanceof ItemBusPartMachine itembus && itembus.io==IO.IN)
            {
                for(int i=0;i<itembus.getInventory().getSlots();i++)
                {
                    if(ChemicalHelper.getItems(item).contains(itembus.getInventory().storage.getStackInSlot(i).getItem()))
                    {
                        count+=itembus.getInventory().storage.getStackInSlot(i).getCount();
                    }
                }
            }
        }
        return count;
    }

    @Override
    public void addDisplayText(List<Component> textList)
    {
        super.addDisplayText(textList);
        boolean bioenable=false;
        boolean tierenable=false;
        boolean coolingenable=false;
        textList.add(Component.literal("Outputting Rate: "+String.valueOf(10000.0F/accuracy)+"%"));
        for(int i=0;i<3;i++)
        {
            if(machineStorage.storage.getStackInSlot(i).getCount()==0)
            {
                textList.add(Component.literal("Slot "+i+": Empty"));
                continue;
            }
            var machine = getMachineFromDataStick(machineStorage.storage.getStackInSlot(i),getLevel());
            if(machine==null || !(machine instanceof PCBAttachment))
            {
                textList.add(Component.literal("Slot "+i+": Invalid"));
                continue;
            }
            if(machine instanceof PCBBiochamber bio)
            {
                if(bioenable)
                {
                    textList.add(Component.literal("Slot "+i+": Biochamber  DUPLICATE"));
                    continue;
                }
                if((!bio.isWorkingEnabled()) || (!bio.isFormed()))
                {
                    textList.add(Component.literal("Slot "+i+": Biochamber  SHUTDOWN"));
                }
                else
                {
                    textList.add(Component.literal("Slot "+i+": Biochamber  RUNNING FINE"));
                    bioenable=true;
                }
            }
            if(machine instanceof PCBEnhancer enhancer)
            {
                if(tierenable)
                {
                    textList.add(Component.literal("Slot "+i+": Upgrade T"+enhancer.getTier()+"  DUPLICATE"));
                    continue;
                }
                if((!enhancer.isWorkingEnabled()) || (!enhancer.isFormed()))
                {
                    textList.add(Component.literal("Slot "+i+": Upgrade T"+enhancer.getTier()+"  SHUTDOWN"));
                }
                else
                {
                    textList.add(Component.literal("Slot "+i+": Upgrade T"+enhancer.getTier()+"  RUNNING FINE"));
                    tierenable=true;
                }
            }
            if(machine instanceof PCBOverclocker overclocker)
            {
                if(coolingenable)
                {
                    textList.add(Component.literal("Slot "+i+": "+(overclocker.getTier()==0?"Cooling Tower":"Thermosink")+"  DUPLICATE"));
                    continue;
                }
                if((!overclocker.isWorkingEnabled()) || (!overclocker.isFormed()))
                {
                    textList.add(Component.literal("Slot "+i+": "+(overclocker.getTier()==0?"Cooling Tower":"Thermosink")+"  SHUTDOWN"));
                }
                else if(!overclocker.isActive())
                {
                    textList.add(Component.literal("Slot "+i+": "+(overclocker.getTier()==0?"Cooling Tower":"Thermosink")+"  Out of Coolent"));
                }
                else
                {
                    textList.add(Component.literal("Slot "+i+": "+(overclocker.getTier()==0?"Cooling Tower":"Thermosink")+"  RUNNING FINE"));
                    coolingenable=true;
                }
            }
        }
    }

    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @Nonnull GTRecipe original) 
    {
        if(machine instanceof PCBBasePart pcb_factory)
        {
            pcb_factory.biochamber=null;
            pcb_factory.enhancer=null;
            pcb_factory.overclocker=null;
            int structure_count=1;
            int tier=1;
            // init attachment
            for(int i=0;i<3;i++)
            {
                MetaMachine substructre=getMachineFromDataStick(pcb_factory.machineStorage.storage.getStackInSlot(i),machine.getLevel());
                if(substructre==null || !(substructre instanceof PCBAttachment))
                {
                    continue;
                }
                if(substructre instanceof PCBBiochamber bio)
                {
                    if(bio.isWorkingEnabled()  && bio.isFormed() && pcb_factory.biochamber==null)
                    {
                        pcb_factory.biochamber=bio;
                        bio.attachedMachine.add(pcb_factory);
                        structure_count++;
                    }
                }
                if(substructre instanceof PCBEnhancer enhancer)
                {
                    if(enhancer.isWorkingEnabled() && enhancer.isFormed()&& pcb_factory.enhancer==null)
                    {
                        pcb_factory.enhancer=enhancer;
                        enhancer.attachedMachine.add(pcb_factory);
                        tier=enhancer.getTier();
                    }
                }
                if(substructre instanceof PCBOverclocker overclocker)
                {
                    if(overclocker.isActive() && overclocker.isFormed() && pcb_factory.overclocker==null)
                    {
                        pcb_factory.overclocker=overclocker;
                        overclocker.attachedMachine.add(pcb_factory);
                        structure_count++;
                    }
                }
            }
            if(original.data.getBoolean("require_bio") && pcb_factory.biochamber==null)
            {
                return null;
            }
            if(original.data.getInt("tier")!=tier)
            {
                return null;
            }
            var recipe=original.copy();
            recipe.duration=(int)(recipe.duration*Math.pow(100.0D/pcb_factory.accuracy,2.0D));
            if(pcb_factory.accuracy>=100)
            {
                for(var a : recipe.getOutputContents(ItemRecipeCapability.CAP))
                {
                    a.chance=100.0F/pcb_factory.accuracy;
                }
            }
            else
            {
                for(var a : recipe.getOutputContents(ItemRecipeCapability.CAP))
                {
                    if(new Random().nextFloat()<100.0F/pcb_factory.accuracy-1.0F)
                    {
                        for(var b : ((Ingredient)a.content).getItems())
                        {
                            b.setCount(b.getCount()*2);
                        }
                    }
                }
            }
            long euUsage=0;
            for(var a : recipe.getTickInputContents(EURecipeCapability.CAP))
                {
                    a.content=(long)(((long)a.content)*Math.sqrt((double)structure_count));
                    euUsage+=(long)a.content;
                }
            if(pcb_factory.enhancer!=null)
            {
                int count= pcb_factory.countItem(new UnificationEntry(nanites, pcb_factory.enhancer.tier==2?Silver:Gold));
                GTCEu.LOGGER.info(String.valueOf(count));
                var parallel_recipe = Objects.requireNonNull(GTRecipeModifiers.accurateParallel(
                    machine, recipe ,Math.min((int)(Math.log(count)/Math.log(2.0D)+1),(int)(pcb_factory.getMaxHatchVoltage()/euUsage)), false));
                GTCEu.LOGGER.info(String.valueOf(parallel_recipe.getB()));
                recipe=parallel_recipe.getA();
            }
            if(pcb_factory.overclocker==null)
                return recipe;
            else if(pcb_factory.overclocker.getTier()==0)
                return RecipeHelper.applyOverclock(OverclockingLogic.NON_PERFECT_OVERCLOCK, recipe,pcb_factory.getMaxVoltage());
            else
                return RecipeHelper.applyOverclock(OverclockingLogic.PERFECT_OVERCLOCK, recipe,pcb_factory.getMaxVoltage());
        }
        return null;
    }

    @Override
    public Widget createUIWidget() {
        var widget =  super.createUIWidget();
        if (widget instanceof WidgetGroup group) {
            var size = group.getSize();
            group.addWidget(new SlotWidget(machineStorage.storage, 0, size.width - 70, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
            group.addWidget(new SlotWidget(machineStorage.storage, 1, size.width - 50, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
            group.addWidget(new SlotWidget(machineStorage.storage, 2, size.width - 30, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
        }
        return widget;
    }
    @Override
    public void attachConfigurators(ConfiguratorPanel configuratorPanel)
    {
        super.attachConfigurators(configuratorPanel);
        configuratorPanel.attachConfigurators(new PCBAccuracyConfigurator(this));
    }
}
