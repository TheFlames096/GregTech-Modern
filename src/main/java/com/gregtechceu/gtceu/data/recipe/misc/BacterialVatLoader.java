package com.gregtechceu.gtceu.data.recipe.misc;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.integration.jei.recipe.GTRecipeTypeCategory;

import mezz.jei.api.registration.IRecipeCatalystRegistration;
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
        static public final String generalPurposeFermentingBacteria="Saccharomyces escherichia";
        static public final String anaerobicOil="Saccharomyces escherichia";
        static public final String eColi="Saccharomyces escherichia";
        static public final String ovaEvolutionis="Ova Evolutionis";
        static public final String binni="Binni Bacteria";
        static public final String derivanturCellulaEvolutionis="Derivantur Cellula Evolutionis";
        static public final String corynebateriumSludgeMarsensis="Corynebaterium Sludge Marsensis";
        static public final String binniGrowthMedium="Binni Growth Medium";
        static public final String mutagen="";
        static public final String cellulaBiologic="";
    }


    public static void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(PETRI_DISH_Saccharomyces_escherichia.asStack(), GTRecipeTypeCategory.TYPES.apply(PETRI_DISH_Saccharomyces_escherichia.get().getRecipe()));
    }

    public static void init(Consumer<FinishedRecipe> provider) {

        PETRI_DISH_Saccharomyces_escherichia.get().initname(Bacteria.generalPurposeFermentingBacteria);

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
        .addData("bacteria",Bacteria.generalPurposeFermentingBacteria)
        .addData("min_sievert", 0)
        .addData("is_exact", false)
        .save(provider);
    }
}
