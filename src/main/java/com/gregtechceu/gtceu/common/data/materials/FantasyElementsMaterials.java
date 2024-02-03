package com.gregtechceu.gtceu.common.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.Custom;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTElements;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
public class FantasyElementsMaterials {
        public static void register()
    {
        Naquadah = new Material.Builder(GTCEu.id("naquadah"))
                .ingot(4)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().customStill())
                .ore()
                .color(0x323232, false).secondaryColor(0x1e251b).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FOIL, GENERATE_SPRING, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .element(GTElements.Nq)
                .rotorStats(6.0f, 4.0f, 1280)
                .cableProperties(GTValues.V[ZPM], 2, 2)
                .fluidPipeProperties(3776, 200, true, false, true, true)
                .blastTemp(5000, GasTier.HIGH, GTValues.VA[GTValues.IV], 5400)
                .buildAndRegister();

        NaquadahEnriched = new Material.Builder(GTCEu.id("enriched_naquadah"))
                .ingot(4)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().customStill())
                .color(0x3C3C3C, false).secondaryColor(0x122f06).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FOIL)
                .element(GTElements.Nq1)
                .blastTemp(5350, GasTier.HIGH, GTValues.VA[IV], 3600)
                .buildAndRegister();

        Naquadria = new Material.Builder(GTCEu.id("naquadria"))
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().customStill())
                .color(0x1E1E1E, false).secondaryColor(0x59b3ff).iconSet(RADIOACTIVE)
                .appendFlags(EXT_METAL, GENERATE_FOIL, GENERATE_GEAR, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .element(GTElements.Nq2)
                .blastTemp(9000, GasTier.MID, GTValues.VA[ZPM], 5400)
                .buildAndRegister();

        Neutronium = new Material.Builder(GTCEu.id("neutronium"))
                .ingot(6)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(100_000))
                .color(0xFFFFFF).secondaryColor(0x000000)
                .appendFlags(EXT_METAL, GENERATE_BOLT_SCREW, GENERATE_FRAME,GENERATE_FOIL)
                .element(GTElements.Nt)
                .toolStats(ToolProperty.Builder.of(180.0F, 100.0F, 65535, 6)
                        .attackSpeed(0.5F).enchantability(33).magnetic().unbreakable().build())
                .rotorStats(24.0f, 12.0f, 655360)
                .fluidPipeProperties(100_000, 5000, true, true, true, true)
                .buildAndRegister();

        Tritanium = new Material.Builder(GTCEu.id("tritanium"))
                .ingot(6)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(25_000))
                .color(0xc35769).secondaryColor(0x210840).iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_FRAME, GENERATE_RING, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR)
                .element(GTElements.Tn)
                .blastTemp(9900, GasTier.LOW, GTValues.VA[GTValues.ZPM], 6428)
                .rotorStats(20.0f, 6.0f, 10240).cableProperties(GTValues.V[ZPM], 4, 4)
                .buildAndRegister();

        Duranium = new Material.Builder(GTCEu.id("duranium"))
                .ingot(5)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(7500))
                .color(0xf3e7a9).secondaryColor(0x9c9487).iconSet(BRIGHT)
                .appendFlags(EXT_METAL, GENERATE_FOIL, GENERATE_GEAR)
                .element(GTElements.Dr)
                .toolStats(ToolProperty.Builder.of(14.0F, 12.0F, 8192, 5)
                    .attackSpeed(0.3F).enchantability(33).magnetic().build())
                .cableProperties(GTValues.V[UV], 1, 16)
                .fluidPipeProperties(9625, 500, true, true, true, true)
                .buildAndRegister();

        Trinium = new Material.Builder(GTCEu.id("trinium"))
                .ingot(7).fluid()
                .color(0x81808a).secondaryColor(0x351d4b).iconSet(SHINY)
                .flags(GENERATE_FOIL, GENERATE_BOLT_SCREW, GENERATE_GEAR)
                .element(GTElements.Ke)
                .cableProperties(GTValues.V[ZPM], 6, 4).ore()
                .blastTemp(7200, GasTier.MID, GTValues.VA[GTValues.LuV], 1260)
                .buildAndRegister();

        Tiberium = new Material.Builder(GTCEu.id("tiberium"))
                .gem(7)
                .color(0x3aff4f).secondaryColor(0x3aff4f).iconSet(SHINY)
                .flags(GENERATE_PLATE,GENERATE_ROD,GENERATE_LONG_ROD,GENERATE_LENS)
                .element(GTElements.Tr)
                .buildAndRegister();

        Sunnarium = new Material.Builder(GTCEu.id("sunnarium"))
                .ingot(7).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(2500))
                .color(0xff8533).iconSet(SHINY)
                .flags(GENERATE_PLATE,GENERATE_ROD)
                .buildAndRegister();

        Bedrockium= new Material.Builder(GTCEu.id("bedrockium"))
                .ingot().fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(2500))
                .color(0xffffff).iconSet(SHINY).cableProperties(GTValues.V[UHV], 2, 32)
                .flags(GENERATE_PLATE,GENERATE_ROD)
                .buildAndRegister();
        
        Tengam = new Material.Builder(GTCEu.id("tengam"))
                .ingot().fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(2500))
                .color(0x9AFF9A).iconSet(METALLIC)
                .flags(GENERATE_PLATE,GENERATE_ROD,GENERATE_LONG_ROD)
                .buildAndRegister();

        AttunedTengam = new Material.Builder(GTCEu.id("attuned_tengam"))
                .ingot()
                .color(0x9AFF9A).iconSet(MAGNETIC)
                .flags(GENERATE_PLATE,GENERATE_ROD,GENERATE_LONG_ROD)
                .buildAndRegister();

        Tartarite = new Material.Builder(GTCEu.id("tartarite"))
                .ingot().fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(10400))
                .color(0xcd2626).iconSet(DULL).ore()
                .blastTemp(10400,  GasTier.LOWEST, VA[UHV], 2300)
                .buildAndRegister();
        Magic = new Material.Builder(GTCEu.id("magic")).element(GTElements.Ma)
                .buildAndRegister();
        Space = new Material.Builder(GTCEu.id("space")).element(GTElements.Sp)
                .buildAndRegister();

        SpaceTime= new Material.Builder(GTCEu.id("spacetime"))
                .ingot(7).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(2500).customStill())
                .iconSet(Custom.SPACETIME).cableProperties(GTValues.V[MAX], 0, 1000000,true)
                .fluidPipeProperties(2147483647,833320,true,true,true,true)
                .appendFlags(EXT2_METAL)
                .buildAndRegister();

        TranscendentMetal= new Material.Builder(GTCEu.id("transcendentmetal"))
                .ingot(7).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(2500).customStill())
                .iconSet(SHINY)
                .fluidPipeProperties(2147483647,733320,true,true,true,true)
                .appendFlags(EXT2_METAL)
                .buildAndRegister();
        
        MagnetohydrodynamicallyConstrainedStarMatter= new Material.Builder(GTCEu.id("magnetohydrodynamically_constrained_star_matter"))
                .ingot(7).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1).customStill())
                .iconSet(SHINY)
                .appendFlags(EXT2_METAL)
                .buildAndRegister();
        Eternity= new Material.Builder(GTCEu.id("eternity"))
                .ingot(7).fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1).customStill())
                .color(0xffffff).iconSet(SHINY)
                .appendFlags(EXT2_METAL)
                .buildAndRegister();
        //GoodGenerator
        Orundum = new Material.Builder(GTCEu.id("orundum"))
                .gem(7)
                .color(0xcd2626).secondaryColor(0xcd2626).iconSet(SHINY)
                .flags(GENERATE_PLATE,GENERATE_ROD,GENERATE_LONG_ROD,GENERATE_LENS)
                .element(GTElements.Or)
                .buildAndRegister();

        Shirabon = new Material.Builder(GTCEu.id("shirabon"))
                .ingot(7)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(13000))
                .color( 0xe0156d).secondaryColor(0xe0156d).iconSet(SHINY)
                .appendFlags(EXT2_METAL, GENERATE_FRAME, GENERATE_RING, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR)
                .element(GTElements.Sh)
                .blastTemp(13000, GasTier.MID, GTValues.VA[GTValues.UIV], 6000)
                .rotorStats(20.0f, 6.0f, 10240)
                .buildAndRegister();

        MetastableOganesson = new Material.Builder(GTCEu.id("metastable_oganesson"))
                .ingot(7)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(11000))
                .color( 0x14397f).secondaryColor(0x14397f).iconSet(SHINY)
                .appendFlags(EXT2_METAL, GENERATE_FRAME, GENERATE_RING, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR)
                .element(GTElements.Og2)
                .blastTemp(12000, GasTier.LOW, GTValues.VA[GTValues.UEV], 6000)
                .rotorStats(20.0f, 6.0f, 10240)
                .buildAndRegister();
                
        //GT++
        Runite = new Material.Builder(GTCEu.id("runtie"))
                .plasma()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(11000))
                .color( 0x3cc8be).secondaryColor(0x3cc8be).iconSet(FINE)
                .element(GTElements.Runite)
                .buildAndRegister();

        Force = new Material.Builder(GTCEu.id("force"))
                .plasma()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(11000))
                .color( 0xe4ff00).secondaryColor(0xe4ff00).iconSet(FINE)
                .element(GTElements.Force)
                .buildAndRegister();

        DragonBlood = new Material.Builder(GTCEu.id("dragonblood"))
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(10000))
                .color( 0xdc2814).secondaryColor(0xdc2814).iconSet(SHINY)
                .element(GTElements.DragonBlood)
                .buildAndRegister();
        
        Rhugnor = new Material.Builder(GTCEu.id("rhugnor"))
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(11000))
                .color( 0xbe00ff).secondaryColor(0xbe00ff).iconSet(RADIOACTIVE)
                .element(GTElements.Rhugnor)
                .buildAndRegister();

        Hypogen = new Material.Builder(GTCEu.id("hypogen"))
                .ingot(5)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(11530))
                .color( 0xdc784b).secondaryColor(0xdc784b).iconSet(RADIOACTIVE)
                .element(GTElements.Hypogen).cableProperties(V[UIV], 8, 0,true)
                .appendFlags(EXT2_METAL, GENERATE_FRAME, GENERATE_RING, GENERATE_ROUND, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .blastTemp(11530, GasTier.LOW, GTValues.VA[GTValues.UIV], 12000)
                .buildAndRegister();
        
        EnergyCrystal = new Material.Builder(GTCEu.id("energy_crystal"))
                .ingot(4)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(4935))
                .color(0xe4ff00).secondaryColor(0xe4ff00).iconSet(METALLIC)
                .element(GTElements.EnergyCrystal)
                .appendFlags(EXT2_METAL, GENERATE_FRAME, GENERATE_RING, GENERATE_ROUND, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .blastTemp(4935, GasTier.LOW, GTValues.VA[GTValues.EV], 1000)
                .buildAndRegister();
     
        AdvancedNitinol = new Material.Builder(GTCEu.id("advaced_nitinol"))
        .ingot(4).plasma()
        .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(8675))
        .color(0xdca0f0).secondaryColor(0xdca0f0).iconSet(RADIOACTIVE)
        .element(GTElements.AdvancedNitinol)
        .appendFlags(EXT2_METAL)
        .blastTemp(8675, GasTier.LOW, GTValues.VA[GTValues.UV], 12000)
        .buildAndRegister();

        ChromaticGlass = new Material.Builder(GTCEu.id("chromatic_glass")).gem()
        .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9475))
        .color(0xffffff).secondaryColor(0xffffff).iconSet(SHINY)
        .element(GTElements.ChromaticGlass)
        .appendFlags(EXT2_METAL,GENERATE_LENS,GENERATE_FOIL)
        .buildAndRegister();

        CelestialTungsten = new Material.Builder(GTCEu.id("celestial_tungsten"))
        .ingot(4).plasma()
        .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(10470))
        .color(0x323232).secondaryColor(0xffffff).iconSet(METALLIC)
        .element(GTElements.CelestialTungsten)
        .appendFlags(EXT2_METAL)
        .blastTemp(10470, GasTier.LOW, GTValues.VA[GTValues.UEV], 12000)
        .buildAndRegister();

        AstralTitanium = new Material.Builder(GTCEu.id("astral_titanium"))
        .ingot(4).plasma()
        .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9715))
        .color(0xdca0f0).secondaryColor(0xffffff).iconSet(METALLIC)
        .element(GTElements.AstralTitanium)
        .appendFlags(EXT2_METAL)
        .blastTemp(9715, GasTier.LOW, GTValues.VA[GTValues.UHV], 11000)
        .buildAndRegister();
    }
}
