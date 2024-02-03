package com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.stringtemplate.v4.compiler.STParser.argExprList_return;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.item.DataSaverItem;
import com.gregtechceu.gtceu.api.item.PetriDishItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.BacterialVat;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PCBAttachment extends WorkableElectricMultiblockMachine implements IMachineModifyDrops{

    @Persisted @DescSynced
    public final NotifiableItemStackHandler machineStorage;
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(PCBAttachment.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    protected Set<PCBBasePart> attachedMachine = new HashSet<PCBBasePart>();

    public PCBAttachment(IMachineBlockEntity holder, Object... args) {
        super(holder,args);
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
        storage.setFilter(this::isDataStick);
        return storage;
    }

    protected boolean isDataStick(ItemStack itemStack) {
        return itemStack.getItem() instanceof DataSaverItem;
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
            if(machineStorage.storage.getStackInSlot(0).getCount()>0)
            {
                CompoundTag coordinate = new CompoundTag();
                coordinate.putInt("x", getPos().getX());
                coordinate.putInt("y", getPos().getY());
                coordinate.putInt("z", getPos().getZ());
                machineStorage.storage.getStackInSlot(0).addTagElement("coordinate", coordinate);
            }
        }
    }

    @Override
    public void onDrops(List<ItemStack> drops, Player entity) {
        clearInventory(drops, machineStorage.storage);
    }

    //////////////////////////////////////
    //********        Gui       ********//
    //////////////////////////////////////

    @Override
    public void addDisplayText(List<Component> textList)
    {
        super.addDisplayText(textList);
        for(PCBBasePart machine : attachedMachine)
        {
            textList.add(Component.translatable("gtceu.multiblock.pcb.attached_to",machine.getPos().toShortString()));
        }
        if(machineStorage.storage.getStackInSlot(0).getCount()>0 && isFormed())
            textList.add(Component.translatable("gtceu.multiblock.pcb.insert_stick_success"));
        else if(isFormed())
            textList.add(Component.translatable("gtceu.multiblock.pcb.insert_stick"));
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
}
