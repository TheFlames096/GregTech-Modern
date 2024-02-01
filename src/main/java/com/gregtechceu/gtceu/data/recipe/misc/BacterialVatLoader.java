package com.gregtechceu.gtceu.data.recipe.misc;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTItems.PETRI_DISH_Saccharomyces_escherichia;
import static com.gregtechceu.gtceu.common.data.GTItems.STERILIZED_PETRI_DISH;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.AUTOCLAVE_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.BACTERIAL_VAT_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.FUSION_RECIPES;

public class BacterialVatLoader {

    static public class Bacteria {
        static public final String Saccharomyces_escherichia="Saccharomyces escherichia";
    }

    public static void init(Consumer<FinishedRecipe> provider) {

        PETRI_DISH_Saccharomyces_escherichia.get().initname(Bacteria.Saccharomyces_escherichia);

        AUTOCLAVE_RECIPES.recipeBuilder("saccharomyces_escherichia")
        .inputFluids(GTMaterials.Biomass.getFluid(20))
        .outputItems(PETRI_DISH_Saccharomyces_escherichia)
        .duration(30)
        .EUt(2);





        PETRI_DISH_Saccharomyces_escherichia.get().getRecipe().recipeBuilder("bacterial_ferment_biomass")
        .inputFluids(GTMaterials.Biomass.getFluid(20))
        .outputFluids(GTMaterials.FermentedBiomass.getFluid(20))
        .duration(30)
        .EUt(2)
        .addData("bacteria",Bacteria.Saccharomyces_escherichia)
        .addData("min_sievert", 0)
        .addData("is_exact", false)
        .save(provider);
    }
}
