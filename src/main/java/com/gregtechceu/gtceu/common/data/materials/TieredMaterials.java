package com.gregtechceu.gtceu.common.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class TieredMaterials {
    public static void register() {

        SuperconductorBaseMV =new Material.Builder(GTCEu.id("superconductor_base_mv"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(2500))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseHV =new Material.Builder(GTCEu.id("superconductor_base_hv"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(3300))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[HV], 2, 8)
            .appendFlags(STD_METAL)
            .components(Titanium, 1, Barium, 9,Copper,10,Oxygen,20)
            .blastTemp(3300, GasTier.LOWEST, GTValues.VA[HV], 2300)
            .buildAndRegister();
        SuperconductorBaseEV =new Material.Builder(GTCEu.id("superconductor_base_ev"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(4400))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[EV], 3, 16)
            .appendFlags(STD_METAL)
            .components(Uranium238, 1, Platinum, 3)
            .blastTemp(4400, GasTier.LOWEST, GTValues.VA[EV], 2800)
            .buildAndRegister();
        SuperconductorBaseIV =new Material.Builder(GTCEu.id("superconductor_base_iv"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(5200))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[IV], 4, 64)
            .appendFlags(STD_METAL)
            .components(Vanadium, 1, Indium, 3)
            .blastTemp(5200, GasTier.LOW, GTValues.VA[EV], 3400)
            .buildAndRegister();
        SuperconductorBaseLuV =new Material.Builder(GTCEu.id("superconductor_base_luv"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(6000))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[LuV], 6, 256)
            .appendFlags(STD_METAL)
            .components(Indium, 4, Tin, 2,Barium,2,Titanium,1,Copper,7,Oxygen,14)
            .blastTemp(6000, GasTier.LOW, GTValues.VA[IV], 3780)
            .buildAndRegister();
        SuperconductorBaseZPM =new Material.Builder(GTCEu.id("superconductor_base_zpm"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9000))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[ZPM], 8, 1024)
            .appendFlags(STD_METAL)
            .components(Naquadah,4,Indium,2,Palladium,6,Osmium,1)
            .blastTemp(9000, GasTier.LOW, GTValues.VA[IV], 4320)
            .buildAndRegister();
        SuperconductorBaseUV =new Material.Builder(GTCEu.id("superconductor_base_uv"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9900))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[UV], 12, 4096)
            .appendFlags(STD_METAL,GENERATE_FOIL)
            .components(Naquadria,4,Osmiridium,3,Europium,1,Samarium,1)
            .blastTemp(9900, GasTier.LOW, GTValues.VA[LuV], 7714)
            .buildAndRegister();
        SuperconductorBaseUHV =new Material.Builder(GTCEu.id("superconductor_base_uhv"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(10500))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[UHV], 16, 16384)
            .appendFlags(STD_METAL)
            .components(Draconium,6,CosmicNeutronium,7,Tritanium,5,Americium,6)
            .blastTemp(10500, GasTier.LOW, GTValues.VA[ZPM], 9642)
            .buildAndRegister();
        SuperconductorBaseUEV =new Material.Builder(GTCEu.id("superconductor_base_uev"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(11800))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[UEV], 24, 65536)
            .appendFlags(STD_METAL)
            .components(AwakenedDraconium,5,Infinity,5,CelestialTungsten,1,AdvancedNitinol,1)
            .blastTemp(11800, GasTier.LOW, GTValues.VA[UV], 11374)
            .buildAndRegister();
            //TODO:Unfinished Superconduct
            /*
        SuperconductorBaseUIV =new Material.Builder(GTCEu.id("superconductorbaseuiv"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature())
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components()
            .blastTemp(2500, GasTier.LOW, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseUXV =new Material.Builder(GTCEu.id("superconductorbaseuxv"))
            .ingot(1).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature())
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components()
            .blastTemp(2500, GasTier.LOW, GTValues.VA[HV], 600)
            .buildAndRegister();
         */
        SuperconductorMV = new Material.Builder(GTCEu.id("superconductor_mv"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[MV], 4, 0,true)
            .components(SuperconductorBaseMV, 1)
            .buildAndRegister();
        SuperconductorHV = new Material.Builder(GTCEu.id("superconductor_hv"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[HV], 6, 0,true)
            .components(SuperconductorBaseHV, 1)
            .buildAndRegister();
        SuperconductorEV = new Material.Builder(GTCEu.id("superconductor_ev"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[EV], 8, 0,true)
            .components(SuperconductorBaseEV, 1)
            .buildAndRegister();
        SuperconductorIV = new Material.Builder(GTCEu.id("superconductor_iv"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[IV], 12, 0,true)
            .components(SuperconductorBaseIV, 1)
            .buildAndRegister();
        SuperconductorLuV = new Material.Builder(GTCEu.id("superconductorluv"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[LuV], 16, 0,true)
            .components(SuperconductorBaseLuV, 1)
            .buildAndRegister();
        SuperconductorZPM = new Material.Builder(GTCEu.id("superconductor_zpm"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[ZPM], 24, 0,true)
            .components(SuperconductorBaseZPM, 1)
            .buildAndRegister();
        SuperconductorUV = new Material.Builder(GTCEu.id("superconductor_uv"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[UV], 32, 0,true)
            .components(SuperconductorBaseUV, 1)
            .buildAndRegister();
        SuperconductorUHV = new Material.Builder(GTCEu.id("superconductor_uhv"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[UHV], 48, 0,true)
            .components(SuperconductorBaseUHV, 1)
            .buildAndRegister();
        SuperconductorUEV = new Material.Builder(GTCEu.id("superconductor_uev"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[UEV], 64, 0,true)
            .components(SuperconductorBaseUEV, 1)
            .buildAndRegister();
            /*
        SuperconductorUIV = new Material.Builder(GTCEu.id("superconductor_uiv"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[UIV], 64, 0,true)
            .components(SuperconductorBaseUIV, 1)
            .buildAndRegister();
        SuperconductorUXV = new Material.Builder(GTCEu.id("superconductor_uxv"))
            .colorAverage().iconSet(SHINY).cableProperties(GTValues.V[UXV], 64, 0,true)
            .components(SuperconductorBaseUXV, 1)
            .buildAndRegister();
            */
        
    }
}
