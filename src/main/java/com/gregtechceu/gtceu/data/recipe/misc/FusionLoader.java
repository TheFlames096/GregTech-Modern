package com.gregtechceu.gtceu.data.recipe.misc;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.FUSION_RECIPES;

public class FusionLoader {

    public static void init(Consumer<FinishedRecipe> provider) {

        FUSION_RECIPES.recipeBuilder("deuterium_and_helium_3_to_helium_plasma")
                .inputFluids(GTMaterials.Deuterium.getFluid(125))
                .inputFluids(GTMaterials.Helium3.getFluid(125))
                .outputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.PLASMA,125))
                .duration(16)
                .EUt(1024)
                .fusionStartEU(60_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("deuterium_and_tritium_to_helium_plasma")
                .inputFluids(GTMaterials.Deuterium.getFluid(125))
                .inputFluids(GTMaterials.Tritium.getFluid(125))
                .outputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.PLASMA,125))
                .duration(16)
                .EUt(4096)
                .fusionStartEU(40_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("glowstone_and_helium_plasma_to_sunnarium")
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.PLASMA,4))
                .inputFluids(GTMaterials.Glowstone.getFluid(16))
                .outputFluids(GTMaterials.Sunnarium.getFluid(16))
                .duration(32)
                .EUt(VA[IV])
                .fusionStartEU(40_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("manganese_and_hydrogen_to_iron")
                .inputFluids(GTMaterials.Manganese.getFluid(16))
                .inputFluids(GTMaterials.Hydrogen.getFluid(16))
                .outputFluids(GTMaterials.Iron.getFluid(16))
                .duration(64)
                .EUt(VA[IV])
                .fusionStartEU(120_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("oxygen_and_magnesium_to_calcium_plasma")
                .inputFluids(GTMaterials.Oxygen.getFluid(128))
                .inputFluids(GTMaterials.Magnesium.getFluid(128))
                .outputFluids(GTMaterials.Calcium.getFluid(FluidStorageKeys.PLASMA,16))
                .duration(128)
                .EUt(VA[IV])
                .fusionStartEU(120_000_000)
                .save(provider);


        FUSION_RECIPES.recipeBuilder("helium_plasma_and_lithium_to_boron_plasma")
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.PLASMA,144))
                .inputFluids(GTMaterials.Lithium.getFluid(144))
                .outputFluids(GTMaterials.Boron.getFluid(FluidStorageKeys.PLASMA,144))
                .duration(240)
                .EUt(10240)
                .fusionStartEU(50_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("gallium_and_radon_to_duranium_plasma")
                .inputFluids(GTMaterials.Gallium.getFluid(16))
                .inputFluids(GTMaterials.Radon.getFluid(125))
                .outputFluids(GTMaterials.Duranium.getFluid(16))
                .duration(64)
                .EUt(16384)
                .fusionStartEU(140_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("neodymium_and_hydrogen_to_europium_plasma")
                .inputFluids(GTMaterials.Neodymium.getFluid(16))
                .inputFluids(GTMaterials.Hydrogen.getFluid(48))
                .outputFluids(GTMaterials.Europium.getFluid(16))
                .duration(64)
                .EUt(24576)
                .fusionStartEU(150_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("hydrogen_and_vanadium_to_chromium_plasma")
                .inputFluids(GTMaterials.Hydrogen.getFluid(125))
                .inputFluids(GTMaterials.Vanadium.getFluid(16))
                .outputFluids(GTMaterials.Chromium.getFluid(16))
                .duration(64)
                .EUt(24576)
                .fusionStartEU(140_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("helium_and_tungsten_to_osmium")
                .inputFluids(GTMaterials.Helium.getFluid(16))
                .inputFluids(GTMaterials.Tungsten.getFluid(16))
                .outputFluids(GTMaterials.Osmium.getFluid(16))
                .duration(256)
                .EUt(24578)
                .fusionStartEU(150_000_000)
                .save(provider);
        
        FUSION_RECIPES.recipeBuilder("beryllium_and_tungsten_to_platium")
                .inputFluids(GTMaterials.Beryllium.getFluid(16))
                .inputFluids(GTMaterials.Tungsten.getFluid(16))
                .outputFluids(GTMaterials.Platinum.getFluid(16))
                .duration(32)
                .EUt(VA[LuV])
                .fusionStartEU(150_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("calcium_plasma_and_boron_plasma_to_neon_plasma")
                .inputFluids(GTMaterials.Boron.getFluid(FluidStorageKeys.PLASMA,144))
                .inputFluids(GTMaterials.Calcium.getFluid(FluidStorageKeys.PLASMA,16))
                .outputFluids(GTMaterials.Neon.getFluid(FluidStorageKeys.PLASMA,1000))
                .duration(32)
                .EUt(VA[LuV])
                .fusionStartEU(100_000_000)
                .save(provider);
        
        FUSION_RECIPES.recipeBuilder("neon_plasma_and_bedrockium_to_force_plasma")
                .inputFluids(GTMaterials.Neon.getFluid(FluidStorageKeys.PLASMA,144))
                .inputFluids(GTMaterials.Bedrockium.getFluid(144))
                .outputFluids(GTMaterials.Force.getFluid(FluidStorageKeys.PLASMA,1000))
                .duration(64)
                .EUt(VA[LuV])
                .fusionStartEU(100_000_000)
                .save(provider);
         
        FUSION_RECIPES.recipeBuilder("aluminium_and_lithium_to_sulfur_plasma")
                .inputFluids(GTMaterials.Aluminium.getFluid(16))
                .inputFluids(GTMaterials.Lithium.getFluid(16))
                .outputFluids(GTMaterials.Sulfur.getFluid(FluidStorageKeys.PLASMA,144))
                .duration(32)
                .EUt(10240)
                .fusionStartEU(240_000_000)
                .save(provider);       

        FUSION_RECIPES.recipeBuilder("beryllium_and_deuterium_to_nitrogen_plasma")
                .inputFluids(GTMaterials.Beryllium.getFluid(16))
                .inputFluids(GTMaterials.Deuterium.getFluid(375))
                .outputFluids(GTMaterials.Nitrogen.getFluid(FluidStorageKeys.PLASMA,125))
                .duration(16)
                .EUt(16384)
                .fusionStartEU(180_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("tantalum_and_tritium_to_tungsten")
                .inputFluids(GTMaterials.Tantalum.getFluid(16))
                .inputFluids(GTMaterials.Tritium.getFluid(16))
                .outputFluids(GTMaterials.Tungsten.getFluid(16))
                .duration(16)
                .EUt(24576)
                .fusionStartEU(200_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("titanium_and_duranium_to_tritanium_plasma")
                .inputFluids(GTMaterials.Titanium.getFluid(48))
                .inputFluids(GTMaterials.Duranium.getFluid(32))
                .outputFluids(GTMaterials.Tritanium.getFluid(16))
                .duration(64)
                .EUt(VA[LuV])
                .fusionStartEU(200_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("lithium_and_tungsten_to_iridium")
                .inputFluids(GTMaterials.Lithium.getFluid(16))
                .inputFluids(GTMaterials.Tungsten.getFluid(16))
                .outputFluids(GTMaterials.Iridium.getFluid(16))
                .duration(64)
                .EUt(VA[LuV])
                .fusionStartEU(300_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("plutonium_239_and_thorium_to_naquadah")
                .inputFluids(GTMaterials.Plutonium239.getFluid(16))
                .inputFluids(GTMaterials.Thorium.getFluid(16))
                .outputFluids(GTMaterials.Naquadah.getFluid(16))
                .duration(64)
                .EUt(VA[LuV])
                .fusionStartEU(300_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("copper_and_tritium_to_zinc_plasma")
                .inputFluids(GTMaterials.Copper.getFluid(72))
                .inputFluids(GTMaterials.Tritium.getFluid(250))
                .outputFluids(GTMaterials.Zinc.getFluid(FluidStorageKeys.PLASMA,72))
                .duration(16)
                .EUt(49152)
                .fusionStartEU(180_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("cobalt_and_silicon_to_niobium_plasma")
                .inputFluids(GTMaterials.Cobalt.getFluid(144))
                .inputFluids(GTMaterials.Silicon.getFluid(144))
                .outputFluids(GTMaterials.Niobium.getFluid(FluidStorageKeys.PLASMA,144))
                .duration(16)
                .EUt(49152)
                .fusionStartEU(200_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("cobalt_and_silicon_to_niobium_plasma")
                .inputFluids(GTMaterials.Silver.getFluid(144))
                .inputFluids(GTMaterials.Helium3.getFluid(375))
                .outputFluids(GTMaterials.Tin.getFluid(FluidStorageKeys.PLASMA,288))
                .duration(16)
                .EUt(49152)
                .fusionStartEU(200_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("mercury_and_magnesium_to_uranium_235")
                .inputFluids(GTMaterials.Mercury.getFluid(125))
                .inputFluids(GTMaterials.Magnesium.getFluid(16))
                .outputFluids(GTMaterials.Uranium235.getFluid(16))
                .duration(64)
                .EUt(49152)
                .fusionStartEU(240_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("gold_and_aluminium_to_uranium_238")
                .inputFluids(GTMaterials.Gold.getFluid(16))
                .inputFluids(GTMaterials.Aluminium.getFluid(16))
                .outputFluids(GTMaterials.Uranium238.getFluid(16))
                .duration(64)
                .EUt(49152)
                .fusionStartEU(240_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("lutenium_and_chromium_to_americium")
                .inputFluids(GTMaterials.Lutetium.getFluid(32))
                .inputFluids(GTMaterials.Chromium.getFluid(32))
                .outputFluids(GTMaterials.Americium.getFluid(32))
                .duration(96)
                .EUt(49152)
                .fusionStartEU(200_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("aluminium_and_fluorine_to_titanium_plasma")
                .inputFluids(GTMaterials.Aluminium.getFluid(144))
                .inputFluids(GTMaterials.Fluorine.getFluid(144))
                .outputFluids(GTMaterials.Titanium.getFluid(FluidStorageKeys.PLASMA,144))
                .duration(160)
                .EUt(49152)
                .fusionStartEU(100_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("boron_plasma_and_lithium_to_oxygen_plasma")
                .inputFluids(GTMaterials.Boron.getFluid(FluidStorageKeys.PLASMA,144))
                .inputFluids(GTMaterials.Lithium.getFluid(144))
                .outputFluids(GTMaterials.Oxygen.getFluid(FluidStorageKeys.PLASMA,144))
                .duration(240)
                .EUt(49152)
                .fusionStartEU(100_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("niobium_plasma_and_zinc_plasma_to_krypton_plasma")
                .inputFluids(GTMaterials.Niobium.getFluid(FluidStorageKeys.PLASMA,144))
                .inputFluids(GTMaterials.Zinc.getFluid(FluidStorageKeys.PLASMA,144))
                .outputFluids(GTMaterials.Krypton.getFluid(FluidStorageKeys.PLASMA,144))
                .duration(32)
                .EUt(VA[ZPM])
                .fusionStartEU(300_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("force_plasma_and_krypton_plasma_to_astral_titanium_plasma")
                .inputFluids(GTMaterials.Force.getFluid(FluidStorageKeys.PLASMA,144))
                .inputFluids(GTMaterials.Krypton.getFluid(FluidStorageKeys.PLASMA,144))
                .outputFluids(GTMaterials.AstralTitanium.getFluid(FluidStorageKeys.PLASMA,144))
                .duration(32)
                .EUt(VA[ZPM])
                .fusionStartEU(300_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("titansteel_plasma_and_astral_titanium_plasma_to_runite_plasma")
                .inputFluids(GTMaterials.Titansteel.getFluid(8))
                .inputFluids(GTMaterials.AstralTitanium.getFluid(FluidStorageKeys.PLASMA,144))
                .outputFluids(GTMaterials.Runite.getFluid(FluidStorageKeys.PLASMA,1000))
                .duration(32)
                .EUt(VA[ZPM])
                .fusionStartEU(300_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("silicon_and_magnesium_to_iron_plasma")
                .inputFluids(GTMaterials.Silicon.getFluid(16))
                .inputFluids(GTMaterials.Magnesium.getFluid(16))
                .outputFluids(GTMaterials.Iron.getFluid(FluidStorageKeys.PLASMA,144))
                .duration(32)
                .EUt(VA[IV])
                .fusionStartEU(360_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("silver_and_lithium_to_indium_plasma")
                .inputFluids(GTMaterials.Silver.getFluid(144))
                .inputFluids(GTMaterials.Lithium.getFluid(144))
                .outputFluids(GTMaterials.Indium.getFluid(144))
                .duration(32)
                .EUt(24576)
                .fusionStartEU(380_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("potassium_and_fluorine_to_nickel_plasma")
                .inputFluids(GTMaterials.Potassium.getFluid(16))
                .inputFluids(GTMaterials.Fluorine.getFluid(125))
                .outputFluids(GTMaterials.Nickel.getFluid(FluidStorageKeys.PLASMA,16))
                .duration(16)
                .EUt(VA[LuV])
                .fusionStartEU(480_000_000)
                .save(provider);








                





        FUSION_RECIPES.recipeBuilder("americium_and_naquadria_to_neutronium_plasma")
                .inputFluids(GTMaterials.Americium.getFluid(128))
                .inputFluids(GTMaterials.Naquadria.getFluid(128))
                .outputFluids(GTMaterials.Neutronium.getFluid(32))
                .duration(200)
                .EUt(98304)
                .fusionStartEU(600_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("silver_and_copper_to_osmium_plasma")
                .inputFluids(GTMaterials.Silver.getFluid(16))
                .inputFluids(GTMaterials.Copper.getFluid(16))
                .outputFluids(GTMaterials.Osmium.getFluid(16))
                .duration(64)
                .EUt(24578)
                .fusionStartEU(150_000_000)
                .save(provider);

        

        FUSION_RECIPES.recipeBuilder("xenon_and_zinc_to_plutonium_239_plasma")
                .inputFluids(GTMaterials.Xenon.getFluid(125))
                .inputFluids(GTMaterials.Zinc.getFluid(16))
                .outputFluids(GTMaterials.Plutonium239.getFluid(16))
                .duration(128)
                .EUt(49152)
                .fusionStartEU(120_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("krypton_and_cerium_to_plutonium_241_plasma")
                .inputFluids(GTMaterials.Krypton.getFluid(125))
                .inputFluids(GTMaterials.Cerium.getFluid(16))
                .outputFluids(GTMaterials.Plutonium241.getFluid(16))
                .duration(128)
                .EUt(49152)
                .fusionStartEU(240_000_000)
                .save(provider);

        


        

        FUSION_RECIPES.recipeBuilder("gold_and_mercury_to_radon_plasma")
                .inputFluids(GTMaterials.Gold.getFluid(16))
                .inputFluids(GTMaterials.Mercury.getFluid(16))
                .outputFluids(GTMaterials.Radon.getFluid(125))
                .duration(64)
                .EUt(VA[LuV])
                .fusionStartEU(200_000_000)
                .save(provider);

        

        FUSION_RECIPES.recipeBuilder("enriched_naquadah_and_radon_to_naquadria_plasma")
                .inputFluids(GTMaterials.NaquadahEnriched.getFluid(16))
                .inputFluids(GTMaterials.Radon.getFluid(125))
                .outputFluids(GTMaterials.Naquadria.getFluid(4))
                .duration(64)
                .EUt(49152)
                .fusionStartEU(400_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("lantanum_and_silicon_to_lutetium_plasma")
                .inputFluids(GTMaterials.Lanthanum.getFluid(16))
                .inputFluids(GTMaterials.Silicon.getFluid(16))
                .outputFluids(GTMaterials.Lutetium.getFluid(16))
                .duration(16)
                .EUt(VA[IV])
                .fusionStartEU(80_000_000)
                .save(provider);

        FUSION_RECIPES.recipeBuilder("arsenic_and_ruthenium_to_darmstadtium_plasma")
                .inputFluids(GTMaterials.Arsenic.getFluid(32))
                .inputFluids(GTMaterials.Ruthenium.getFluid(16))
                .outputFluids(GTMaterials.Darmstadtium.getFluid(16))
                .duration(32)
                .EUt(VA[LuV])
                .fusionStartEU(200_000_000)
                .save(provider);

    }
}
