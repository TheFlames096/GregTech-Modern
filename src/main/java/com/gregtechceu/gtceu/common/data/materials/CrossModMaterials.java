package com.gregtechceu.gtceu.common.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTElements;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;

public class CrossModMaterials {
    public static void register()
    {
        //Thaumcraft
        Thaumium = new Material.Builder(GTCEu.id("thaumium"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(3405))
                .color(0x9370DB).iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE)
                .components(Iron, 1, Magic,1)
                .buildAndRegister();
        // EnderIO
        RedstoneAlloy = new Material.Builder(GTCEu.id("redstone_alloy"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1200))
                .color(0xb53333).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .components(Redstone, 1, Silicon, 1,Coal,1)
                .cableProperties(GTValues.V[1], 1, 0)
                .blastTemp(1200, GasTier.LOWEST, GTValues.VA[GTValues.MV], 1600)
                .buildAndRegister();

        ConductiveIron = new Material.Builder(GTCEu.id("conductive_alloy"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1200))
                .color(0xd9b2ab).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .components(RedstoneAlloy, 1, Iron, 1,Silver,1)
                .blastTemp(1200, GasTier.LOWEST, GTValues.VA[GTValues.MV], 1600)
                .buildAndRegister();
        EnergeticAlloy = new Material.Builder(GTCEu.id("energetic_alloy"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1200))
                .color(0xFF8C00).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .components(Gold, 1, ConductiveIron, 1,BlackSteel,1)
                .blastTemp(2200, GasTier.LOWEST, GTValues.VA[GTValues.MV], 1600)
                .buildAndRegister();
        VibrantAlloy = new Material.Builder(GTCEu.id("vibrant_alloy"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1200))
                .color(0x008B00).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW,GENERATE_FRAME)
                .components(EnergeticAlloy, 1, EnderEye, 1,Chromium,1)
                .blastTemp(3000, GasTier.LOWEST, GTValues.VA[GTValues.MV], 6000)
                .buildAndRegister();
        Enderium = new Material.Builder(GTCEu.id("enderium"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1200))
                .color(0xd9b2ab).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .fluidPipeProperties(15000,6000,true,true,true,true)
                .components(Tin, 4,Silver,2,Platinum,2,Thaumium,1,EnderPearl,1)
                .blastTemp(1200, GasTier.LOWEST, GTValues.VA[GTValues.MV], 1600)
                .buildAndRegister();



        //Twilight Forest
        Steeleaf = new Material.Builder(GTCEu.id("steeleaf"))
        .ingot()
        .color(0x008B00).iconSet(METALLIC)
        .flags(GENERATE_PLATE)
        .buildAndRegister();

        Ironwood = new Material.Builder(GTCEu.id("iron_wood"))
        .ingot()
        .color(0x556B2F).iconSet(METALLIC)
        .flags(GENERATE_PLATE)
        .buildAndRegister();

        Fiery = new Material.Builder(GTCEu.id("fiery"))
        .ingot()
        .color(0x8B1A1A).iconSet(METALLIC)
        .flags(GENERATE_PLATE)
        .buildAndRegister();
        
        Knightmetal = new Material.Builder(GTCEu.id("knightmetal"))
        .ingot()
        .color(0xF5F5DC).iconSet(METALLIC)
        .flags(GENERATE_PLATE,GENERATE_ROD,GENERATE_RING)
        .buildAndRegister();;

        // Draconic Evolution
        Draconium = new Material.Builder(GTCEu.id("draconium"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(5000))
                .color(0x9370DB).iconSet(SHINY)
                .appendFlags(STD_METAL, GENERATE_LONG_ROD, GENERATE_FINE_WIRE, GENERATE_SPRING, GENERATE_FOIL, GENERATE_FRAME)
                .element(GTElements.Draconium)
                .blastTemp(7200, GasTier.MID, GTValues.VA[GTValues.LuV], 3600)
                .cableProperties(GTValues.V[UEV], 8, 4)
                .buildAndRegister();
        AwakenedDraconium = new Material.Builder(GTCEu.id("awakened_draconium"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9900))
                .color(0xFF8C00).iconSet(SHINY)
                .appendFlags(STD_METAL, GENERATE_LONG_ROD, GENERATE_FINE_WIRE, GENERATE_SPRING, GENERATE_FOIL, GENERATE_FRAME)
                .element(GTElements.AwakenedDraconium)
                .fluidPipeProperties(10000000,150000,true,true,true,true)
                .blastTemp(9900, GasTier.MID, GTValues.VA[GTValues.ZPM], 4500)
                .cableProperties(GTValues.V[MAX], 1, 2)
                .buildAndRegister();

        // Avaritia
        Infinity = new Material.Builder(GTCEu.id("infinity"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9900).customStill())
                .color(0xFFFFFF).iconSet(SHINY)
                .appendFlags(STD_METAL, GENERATE_LONG_ROD, GENERATE_FINE_WIRE, GENERATE_SPRING, GENERATE_FOIL, GENERATE_FRAME)
                .element(GTElements.If2)
                .fluidPipeProperties(2147483647,200000,true,true,true,true)
                .blastTemp(10800, GasTier.LOW, GTValues.VA[GTValues.UHV], 32142)
                .cableProperties(GTValues.V[MAX], 8192, 0,true)
                .buildAndRegister();
        CosmicNeutronium = new Material.Builder(GTCEu.id("cosmic_neutronium"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9900))
                .color(0x000000).iconSet(SHINY)
                .appendFlags(STD_METAL, GENERATE_LONG_ROD, GENERATE_FINE_WIRE, GENERATE_SPRING, GENERATE_FOIL, GENERATE_FRAME)
                .components(Space,1,Neutronium,1)
                .blastTemp(9900, GasTier.LOW, GTValues.VA[GTValues.ZPM], 12857)
                .buildAndRegister();
        InfinityCatalyst = new Material.Builder(GTCEu.id("infinity_catalyst"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9900))
                .color(0xFFFFFF).iconSet(METALLIC)
                .appendFlags(STD_METAL, GENERATE_LONG_ROD, GENERATE_FINE_WIRE, GENERATE_SPRING, GENERATE_FOIL, GENERATE_FRAME)
                .element(GTElements.If)
                .blastTemp(10800, GasTier.LOW, GTValues.VA[GTValues.UV], 15428)
                .buildAndRegister();

        // Galaxy Space
        MysteriousCrystal = new Material.Builder(GTCEu.id("mysterious_crystal"))
                .gem()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9900))
                .color(0x008B00).iconSet(DULL)
                .fluidPipeProperties(1000000,80000,true,true,true,true)
                .flags(GENERATE_PLATE,GENERATE_LENS)
                .buildAndRegister();

        BlackPlutonium = new Material.Builder(GTCEu.id("black_plutonium"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9000))
                .color(0x000000).iconSet(DULL)
                .appendFlags(EXT2_METAL)
                .cableProperties(GTValues.V[OpV], 1, 16)
                .itemPipeProperties(4096, 8).blastTemp(9000, GasTier.LOWEST, GTValues.VA[GTValues.ZPM], 6000)
                .components(Space,1,Plutonium241,1)
                .buildAndRegister();
        
        Quantium= new Material.Builder(GTCEu.id("quantium"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(9000))
                .color(0x000000).iconSet(DULL)
                .appendFlags(EXT2_METAL).blastTemp(9000, GasTier.LOW, GTValues.VA[GTValues.ZPM], 4000)
                .cableProperties(GTValues.V[UXV], 2, 4)
                .itemPipeProperties(512, 32)
                .buildAndRegister();
    }
}
