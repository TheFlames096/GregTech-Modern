package com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory;

import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_REINFORCED_PHOTOLITHOGRAPHIC_FRAMEWORK;
import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_RADIATION_PROOF_PHOTOLITHOGRAPHIC_FRAMEWORK;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.Block;

public class PCBOverclocker extends PCBAttachment{

    public PCBOverclocker(IMachineBlockEntity holder,Object... args) {
        super(holder,args);
    }
}
