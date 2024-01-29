package com.gregtechceu.gtceu.common.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;

import static com.gregtechceu.gtceu.api.GTValues.UV;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class ForthDegreeMaterials {
    public static void register() {
        BlueAlloy = new Material.Builder(GTCEu.id("blue_alloy"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1400))
                .color(0x64B4FF).iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_BOLT_SCREW, DISABLE_DECOMPOSITION)
                .components(Electrotine, 4, Silver, 1)
                .cableProperties(GTValues.V[GTValues.HV], 2, 1)
                .buildAndRegister();

        RedAlloy = new Material.Builder(GTCEu.id("red_alloy"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1400))
                .color(0xc55252).secondaryColor(0xC80000).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW, DISABLE_DECOMPOSITION)
                .components(Copper, 1, Redstone, 4)
                .cableProperties(GTValues.V[0], 1, 0)
                .buildAndRegister();
                

        BasalticMineralSand = new Material.Builder(GTCEu.id("basaltic_mineral_sand"))
                .dust(1).ore()
                .color(0x5c5c5c).secondaryColor(0x283228).iconSet(SAND)
                .components(Magnetite, 1, Basalt, 1)
                .flags(BLAST_FURNACE_CALCITE_DOUBLE)
                .buildAndRegister();

        HSSE = new Material.Builder(GTCEu.id("hsse"))
                .ingot(4).fluid()
                .color(0x9d9cbe).secondaryColor(0x2b0350).iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_FRAME, GENERATE_RING)
                .components(HSSG, 6, Cobalt, 1, Manganese, 1, Silicon, 1)
                .toolStats(ToolProperty.Builder.of(5.0F, 10.0F, 3072, 4)
                        .attackSpeed(0.3F).enchantability(33).build())
                .rotorStats(10.0f, 8.0f, 5120)
                .blastTemp(5000, GasTier.HIGH, GTValues.VA[GTValues.EV], 1400)
                .buildAndRegister();

        HSSS = new Material.Builder(GTCEu.id("hsss"))
                .ingot(4).fluid()
                .color(0xae9abe).secondaryColor(0x66000e).iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_SMALL_GEAR, GENERATE_RING, GENERATE_FRAME, GENERATE_ROTOR, GENERATE_ROUND, GENERATE_FOIL, GENERATE_GEAR)
                .components(HSSG, 6, Iridium, 2, Osmium, 1)
                .rotorStats(15.0f, 7.0f, 3000)
                .blastTemp(5000, GasTier.HIGH, GTValues.VA[GTValues.EV], 1500)
                .buildAndRegister();

        Mithril= new Material.Builder(GTCEu.id("mithril"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(6600))
                .color(0xFFF8DC).iconSet(METALLIC)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Platinum, 2, Thaumium, 1)
                .buildAndRegister();
        
        AstralSilver = new Material.Builder(GTCEu.id("astral_silver"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1400))
                .color(0xE6E6FA).iconSet(METALLIC)
                .flags(GENERATE_PLATE, DECOMPOSITION_BY_CENTRIFUGING)
                .components(Silver, 2, Thaumium, 1)
                .buildAndRegister();

        FluxedElectrum = new Material.Builder(GTCEu.id("fluxed_electrum"))
                .ingot()
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1400))
                .color(0xFFD700).secondaryColor(0xffff78).iconSet(METALLIC)
                .appendFlags(EXT2_METAL,DECOMPOSITION_BY_CENTRIFUGING,GENERATE_SINGULARITY)
                .cableProperties(GTValues.V[UV], 3, 1)
                .itemPipeProperties(8192, 4)
                .components(RoseGold, 2, InfusedGold, 1,AstralSilver,1,SterlingSilver,1,SolderingAlloy,1,RedSteel,1,BlueSteel,1,Naquadah,1)
                .buildAndRegister();
    }
}
