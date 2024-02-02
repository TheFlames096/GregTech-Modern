package com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory;

import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_REINFORCED_PHOTOLITHOGRAPHIC_FRAMEWORK;
import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_RADIATION_PROOF_PHOTOLITHOGRAPHIC_FRAMEWORK;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.Block;

public class PCBEnhancer extends PCBAttachment{

    public PCBEnhancer(IMachineBlockEntity holder,Object... args) {
        super(holder,args);
    }
    public static BlockEntry<Block> getCasingType(int tier)
    {
        if(tier==2)
        {
            return CASING_REINFORCED_PHOTOLITHOGRAPHIC_FRAMEWORK;
        }
        return CASING_RADIATION_PROOF_PHOTOLITHOGRAPHIC_FRAMEWORK;
    }
}
