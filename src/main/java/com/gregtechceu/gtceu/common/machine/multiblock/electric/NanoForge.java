package com.gregtechceu.gtceu.common.machine.multiblock.electric;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.nanites;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.api.pattern.Predicates.frames;
import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_RADIANT_NAQUADAH_ALLOY_FRAMEWORK;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Carbon;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Gold;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Neutronium;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Silver;
import static com.gregtechceu.gtceu.common.data.GTMaterials.StellarAlloy;
import static com.gregtechceu.gtceu.common.data.GTMaterials.TranscendentMetal;

import java.util.HashSet;
import java.util.function.Supplier;

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
import com.gregtechceu.gtceu.api.item.DataSaverItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory.PCBAttachment;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.SetBlockCommand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import oshi.software.os.OSProcess.State;

public class NanoForge extends WorkableElectricMultiblockMachine implements IMachineModifyDrops{

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NanoForge.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Persisted @DescSynced
    public final NotifiableItemStackHandler machineStorage;
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
    public final int tier;
    public NanoForge(IMachineBlockEntity holder , int tier) {
        super(holder, tier);
        this.tier=tier;
        this.machineStorage = createMachineStorage();
    }
    protected NotifiableItemStackHandler createMachineStorage(Object... args) {
        var storage = new NotifiableItemStackHandler(this, 1, IO.NONE, IO.NONE, slots -> new ItemStackTransfer(1) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        });
        storage.setFilter(this::isNanites);
        return storage;
    }

    protected boolean isNanites(ItemStack itemStack) {
        return ChemicalHelper.getItems(new UnificationEntry(nanites)).contains(itemStack.getItem());
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

    protected void onMachineChanged() {
        this.recipeLogic.markLastRecipeDirty();
    }
    @Override
    public void addDisplayText(List<Component> textList)
    {
        super.addDisplayText(textList);
        int tier;
        if(ChemicalHelper.getItems(new UnificationEntry(nanites,Carbon)).contains(machineStorage.storage.getStackInSlot(0)))
        {
            tier=1;
        }
        else if(ChemicalHelper.getItems(new UnificationEntry(nanites,Neutronium)).contains(machineStorage.storage.getStackInSlot(0)))
        {
            tier=2;
        }
        else if(ChemicalHelper.getItems(new UnificationEntry(nanites,TranscendentMetal)).contains(machineStorage.storage.getStackInSlot(0)))
        {
            tier=3;
        }
        else
        {
            textList.add(Component.literal("Insert a valid nanites to start."));
            return;
        }
        if(tier!=this.tier)
        {
            textList.add(Component.literal("Incorrect nanites."));
        }
    }
    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @Nonnull GTRecipe original) 
    {
        if(machine instanceof NanoForge nano_forge)
        {
            int tier;
            if(ChemicalHelper.getItems(new UnificationEntry(nanites,Carbon)).contains(nano_forge.machineStorage.storage.getStackInSlot(0)))
            {
                tier=1;
            }
            else if(ChemicalHelper.getItems(new UnificationEntry(nanites,Neutronium)).contains(nano_forge.machineStorage.storage.getStackInSlot(0)))
            {
                tier=2;
            }
            else if(ChemicalHelper.getItems(new UnificationEntry(nanites,TranscendentMetal)).contains(nano_forge.machineStorage.storage.getStackInSlot(0)))
            {
                tier=3;
            }
            else
            {
                return null;
            }
            if(original.data.getInt("tier")>tier || tier != nano_forge.tier)
            {
                return null;
            }
            if(original.data.getInt("tier")==tier)
            {
                return RecipeHelper.applyOverclock(OverclockingLogic.NON_PERFECT_OVERCLOCK, original, nano_forge.getMaxVoltage());
            }
            return RecipeHelper.applyOverclock(OverclockingLogic.PERFECT_OVERCLOCK, original, nano_forge.getMaxVoltage());
        }
        return null;
    }

    @Override
    public Widget createUIWidget() {
        var widget =  super.createUIWidget();
        if (widget instanceof WidgetGroup group) {
            var size = group.getSize();
            group.addWidget(new SlotWidget(machineStorage.storage, 0, size.width - 90, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
        }
        return widget;
    }
}
