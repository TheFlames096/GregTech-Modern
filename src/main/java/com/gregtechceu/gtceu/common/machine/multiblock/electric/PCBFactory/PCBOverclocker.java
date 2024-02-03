package com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory;

import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_REINFORCED_PHOTOLITHOGRAPHIC_FRAMEWORK;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.toolHeadScrewdriver;
import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_RADIATION_PROOF_PHOTOLITHOGRAPHIC_FRAMEWORK;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.item.PetriDishItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.tterrag.registrate.util.entry.BlockEntry;

import lombok.Getter;
import net.minecraft.world.level.block.Block;

public class PCBOverclocker extends PCBAttachment{

    @Getter
    public final int tier;
    public PCBOverclocker(IMachineBlockEntity holder,int tier) {
        super(holder);
        this.tier = tier;
    }

    @Override
    @Nonnull
    public GTRecipeType[] getRecipeTypes() {
        return new GTRecipeType[]{this.getTier()==0?GTRecipeTypes.COOLING_RECIPES:GTRecipeTypes.SUPER_COOLING_RECIPES};
    }

    @NotNull
    @Override
    public GTRecipeType getRecipeType() {
        return getRecipeTypes()[0];
    }

    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @Nonnull GTRecipe original) 
    {
        if(machine instanceof PCBOverclocker overclocker)
        {
            if(overclocker.attachedMachine.isEmpty())
                return null;
            int parallel=0;
            overclocker.attachedMachine.removeIf(Objects::isNull);
            for(PCBBasePart PCBmachine : overclocker.attachedMachine)
            {
                if(PCBmachine.isActive() && PCBmachine.overclocker==overclocker)
                {
                    parallel++;
                }
            }
            if(parallel==0)
                return null;
            var parallel_recipe = Objects.requireNonNull(GTRecipeModifiers.accurateParallel(
                machine, original ,parallel, false));
            if(parallel_recipe.getB()<parallel)
            {
                overclocker.recipeLogic.resetRecipeLogic();
                overclocker.setWorkingEnabled(false);
                return null;
            }
            return parallel_recipe.getA();
        }
        return null;
    }
}
