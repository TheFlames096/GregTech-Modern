package com.gregtechceu.gtceu.common.machine.multiblock.electric.NanoForge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.item.DataSaverItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory.PCBAttachment;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory.PCBBasePart;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import lombok.Getter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class NanoAttachment extends WorkableElectricMultiblockMachine implements IMachineModifyDrops{
    @Persisted @DescSynced
    public final NotifiableItemStackHandler machineStorage;
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NanoAttachment.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Getter
    public final int tier;
    public NanoAttachment(IMachineBlockEntity holder,int tier, Object... args) {
        super(holder,args);
        this.machineStorage = createMachineStorage(args);
        this.tier=tier;
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

}
