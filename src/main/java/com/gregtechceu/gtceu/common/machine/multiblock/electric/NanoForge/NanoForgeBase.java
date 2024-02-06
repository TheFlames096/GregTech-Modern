package com.gregtechceu.gtceu.common.machine.multiblock.electric.NanoForge;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.nanites;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Carbon;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Gold;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Neutronium;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Silver;
import static com.gregtechceu.gtceu.common.data.GTMaterials.TranscendentMetal;

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
import oshi.software.os.OSProcess.State;

public class NanoForgeBase extends WorkableElectricMultiblockMachine implements IMachineModifyDrops{

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NanoForgeBase.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Persisted @DescSynced
    public final NotifiableItemStackHandler machineStorage;
    @Persisted @DescSynced
    public final NotifiableItemStackHandler machineNaniteStorage;
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public NanoForgeBase(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.machineStorage = createMachineStorage(args);
        this.machineNaniteStorage = createMachineStorage(args);
    }
    protected NotifiableItemStackHandler createMachineStorage(Object... args) {
        var storage = new NotifiableItemStackHandler(this, 2, IO.NONE, IO.NONE, slots -> new ItemStackTransfer(3) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        });
        storage.setFilter(this::isDataStick);
        return storage;
    }
    
    protected NotifiableItemStackHandler createMachineNaniteStorage(Object... args) {
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

    protected boolean isDataStick(ItemStack itemStack) {
        return itemStack.getItem() instanceof DataSaverItem;
    }

    @Override
    public void onDrops(List<ItemStack> drops, Player entity) {
        clearInventory(drops, machineStorage.storage);
        clearInventory(drops, machineNaniteStorage.storage);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            machineStorage.addChangedListener(this::onMachineChanged);
            machineNaniteStorage.addChangedListener(this::onMachineChanged);
        }
    }

    protected void onMachineChanged() {
        for(int i=0;i<3;i++)
        {
            MetaMachine substructre=getMachineFromDataStick(machineStorage.storage.getStackInSlot(i),getLevel());
            if(substructre==null || !(substructre instanceof PCBAttachment))
            {
                continue;
            }
        }
        this.recipeLogic.markLastRecipeDirty();
    }
    //////////////////////////////////////
    //********        Gui       ********//
    //////////////////////////////////////

    public static MetaMachine getMachineFromDataStick(ItemStack datastick,Level level)
    {
        if(!(datastick.getItem() instanceof DataSaverItem))
            return null;
        if(datastick.getTag()==null)
            return null;
        if(datastick.getTag().get("coordinate")!=null)
            return MetaMachine.getMachine(level,new BlockPos(datastick.getTag().getCompound("coordinate").getInt("x"),datastick.getTag().getCompound("coordinate").getInt("y"),datastick.getTag().getCompound("coordinate").getInt("z")));
        return null;
    }
    @Override
    public void addDisplayText(List<Component> textList)
    {
        super.addDisplayText(textList);
        int tier;
        if(ChemicalHelper.getItems(new UnificationEntry(nanites,Carbon)).contains(machineNaniteStorage.storage.getStackInSlot(0)))
        {
            tier=1;
        }
        else if(ChemicalHelper.getItems(new UnificationEntry(nanites,Neutronium)).contains(machineNaniteStorage.storage.getStackInSlot(0)))
        {
            tier=2;
        }
        else if(ChemicalHelper.getItems(new UnificationEntry(nanites,TranscendentMetal)).contains(machineNaniteStorage.storage.getStackInSlot(0)))
        {
            tier=3;
        }
        else
        {
            textList.add(Component.literal("Insert a valid nanites to start."));
            return;
        }
        boolean[] tierenable={false,false};
        for(int i=0;i<2;i++)
        {
            if(machineStorage.storage.getStackInSlot(i).getCount()==0)
            {
                continue;
            }
            var machine = getMachineFromDataStick(machineStorage.storage.getStackInSlot(i),getLevel());
            if(machine==null || !(machine instanceof NanoAttachment))
            {
                continue;
            }
            if(machine instanceof NanoAttachment nano_attachment && nano_attachment.isFormed())
            {
                tierenable[nano_attachment.tier]=true;
            }
        }
        if(tier>1)
        {
            if(tierenable[0])
                textList.add(Component.literal("T2 upgrade linked."));
            else
                textList.add(Component.literal("T2 upgrade missing"));
        }
        if(tier>2 && !tierenable[1])
        {
            if(tierenable[1])
                textList.add(Component.literal("T3 upgrade linked."));
            else
                textList.add(Component.literal("T3 upgrade missing"));
        }
    }

    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @Nonnull GTRecipe original) 
    {
        if(machine instanceof NanoForgeBase nano_forge)
        {
            int tier;
            if(ChemicalHelper.getItems(new UnificationEntry(nanites,Carbon)).contains(nano_forge.machineNaniteStorage.storage.getStackInSlot(0)))
            {
                tier=1;
            }
            else if(ChemicalHelper.getItems(new UnificationEntry(nanites,Neutronium)).contains(nano_forge.machineNaniteStorage.storage.getStackInSlot(0)))
            {
                tier=2;
            }
            else if(ChemicalHelper.getItems(new UnificationEntry(nanites,TranscendentMetal)).contains(nano_forge.machineNaniteStorage.storage.getStackInSlot(0)))
            {
                tier=3;
            }
            else
            {
                return null;
            }
            // init attachment
            boolean tierenable[]={false,false};
            for(int i=0;i<2;i++)
            {
                MetaMachine substructre=getMachineFromDataStick(nano_forge.machineStorage.storage.getStackInSlot(i),machine.getLevel());
                if(substructre==null)
                {
                    continue;
                }
                if(substructre instanceof NanoAttachment nano_attachment && nano_attachment.isFormed())
                    tierenable[nano_attachment.tier]=true;
            }
            if(tier>1 && !tierenable[0])
            {
                return null;
            }
            if(tier>2 && !tierenable[1])
            {
                return null;
            }
            if(original.data.getInt("tier")>tier)
            {
                return null;
            }
            if(original.data.getInt("tier")==tier)
            {
                return RecipeHelper.applyOverclock(OverclockingLogic.NON_PERFECT_OVERCLOCK, original,nano_forge.getMaxVoltage());
            }
            return RecipeHelper.applyOverclock(OverclockingLogic.PERFECT_OVERCLOCK, original ,nano_forge.getMaxVoltage());
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
            group.addWidget(new SlotWidget(machineStorage.storage, 1, size.width - 70, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
            group.addWidget(new SlotWidget(machineNaniteStorage.storage, 0, size.width - 30, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
        }
        return widget;
    }
}
