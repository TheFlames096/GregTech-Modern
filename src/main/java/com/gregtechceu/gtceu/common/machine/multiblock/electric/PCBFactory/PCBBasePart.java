package com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory;

import java.util.List;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.item.DataSaverItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PCBBasePart extends WorkableElectricMultiblockMachine implements IMachineModifyDrops{

    @Persisted @DescSynced
    public final NotifiableItemStackHandler machineStorage;
    PCBEnhancer enhancer=null;
    PCBOverclocker overclocker=null;
    PCBBiochamber biochamber=null;
    public PCBBasePart(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.machineStorage = createMachineStorage(args);
    }
    protected NotifiableItemStackHandler createMachineStorage(Object... args) {
        var storage = new NotifiableItemStackHandler(this, 3, IO.NONE, IO.NONE, slots -> new ItemStackTransfer(1) {
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
    //////////////////////////////////////
    //********        Gui       ********//
    //////////////////////////////////////

    private MetaMachine getMachineFromDataStick(ItemStack datastick)
    {
        if(datastick.getTag()==null)
            return null;
        if(datastick.getTag().get("coordinate")!=null)
            return MetaMachine.getMachine(getLevel(),new BlockPos(datastick.getTag().getCompound("coordinate").getInt("x"),datastick.getTag().getCompound("coordinate").getInt("y"),datastick.getTag().getCompound("coordinate").getInt("z")));
        return null;
    }
    @Override
    public void addDisplayText(List<Component> textList)
    {
        super.addDisplayText(textList);
        if(recipeLogic.getLastRecipe()==null)
        {
            boolean bioenable=false;
            boolean tierenable=false;
            boolean coolingenable=false;
            for(int i=0;i<3;i++)
            {
                if(machineStorage.storage.getStackInSlot(i).getCount()==0)
                {
                    textList.add(Component.literal("Slot "+i+": Empty"));
                    continue;
                }
                if(getMachineFromDataStick(machineStorage.storage.getStackInSlot(i))==null || !(getMachineFromDataStick(machineStorage.storage.getStackInSlot(i)) instanceof PCBAttachment))
                {
                    textList.add(Component.literal("Slot "+i+": Invalid"));
                    continue;
                }
                if(getMachineFromDataStick(machineStorage.storage.getStackInSlot(i)) instanceof PCBBiochamber bio)
                {
                    if(bioenable)
                    {
                        textList.add(Component.literal("Slot "+i+": Biochamber  DUPLICATE"));
                        continue;
                    }
                    if(!bio.isActive())
                    {
                        textList.add(Component.literal("Slot "+i+": Biochamber  SHUTDOWN"));
                    }
                    else
                    {
                        textList.add(Component.literal("Slot "+i+": Biochamber  RUNNING FINE"));
                        bioenable=true;
                    }
                }
                if(getMachineFromDataStick(machineStorage.storage.getStackInSlot(i)) instanceof PCBEnhancer enhancer)
                {
                    if(tierenable)
                    {
                        textList.add(Component.literal("Slot "+i+": Upgrade T"+enhancer.getTier()+"  DUPLICATE"));
                        continue;
                    }
                    if(!enhancer.isActive())
                    {
                        textList.add(Component.literal("Slot "+i+": Upgrade T"+enhancer.getTier()+"  SHUTDOWN"));
                    }
                    else
                    {
                        textList.add(Component.literal("Slot "+i+": Upgrade T"+enhancer.getTier()+"  RUNNING FINE"));
                        tierenable=true;
                    }
                }
                if(getMachineFromDataStick(machineStorage.storage.getStackInSlot(i)) instanceof PCBOverclocker overclocker)
                {
                    if(coolingenable)
                    {
                        textList.add(Component.literal("Slot "+i+": "+(overclocker.getTier()==0?"Cooling Tower":"Thermosink")+"  DUPLICATE"));
                        continue;
                    }
                    if(!overclocker.isActive())
                    {
                        textList.add(Component.literal("Slot "+i+": "+(overclocker.getTier()==0?"Cooling Tower":"Thermosink")+"  SHUTDOWN"));
                    }
                    else
                    {
                        textList.add(Component.literal("Slot "+i+": "+(overclocker.getTier()==0?"Cooling Tower":"Thermosink")+"  RUNNING FINE"));
                        coolingenable=true;
                    }
                }
            }
        }
    }

    @Override
    public Widget createUIWidget() {
        var widget =  super.createUIWidget();
        if (widget instanceof WidgetGroup group) {
            var size = group.getSize();
            group.addWidget(new SlotWidget(machineStorage.storage, 0, size.width - 30, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
            group.addWidget(new SlotWidget(machineStorage.storage, 1, size.width - 50, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
            group.addWidget(new SlotWidget(machineStorage.storage, 2, size.width - 70, size.height - 30, true, true)
                .setBackground(GuiTextures.SLOT));
        }
        return widget;
    }
}
