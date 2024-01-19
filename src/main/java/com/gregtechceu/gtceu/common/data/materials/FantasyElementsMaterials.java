package com.gregtechceu.gtceu.common.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
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
    static void register()
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
                .appendFlags(EXT_METAL, GENERATE_BOLT_SCREW, GENERATE_FRAME)
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
                .rotorStats(20.0f, 6.0f, 10240)
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
                .cableProperties(GTValues.V[ZPM], 6, 4)
                .blastTemp(7200, GasTier.MID, GTValues.VA[GTValues.LuV], 1260)
                .buildAndRegister();

        Tiberium = new Material.Builder(GTCEu.id("tiberium"))
                .gem(12)
                .color(0x3aff4f).secondaryColor(0x3aff4f).iconSet(SHINY)
                .flags(GENERATE_PLATE,GENERATE_ROD,GENERATE_LONG_ROD,GENERATE_LENS,GENERATE_FOIL)
                .element(GTElements.Tr)
                .buildAndRegister();
    }
}
