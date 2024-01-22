package com.gregtechceu.gtceu.common.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;

public class CrossModMaterials {
    public static void register()
    {
        // EnderIO
        RedstoneAlloy = new Material.Builder(GTCEu.id("redstone_alloy"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1200))
                .color(0xc55252).iconSet(METALLIC)
                .appendFlags(EXT_METAL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .components(Redstone, 1, Silicon, 1,Coal,1)
                .cableProperties(GTValues.V[1], 1, 1)
                .buildAndRegister();

        //Thaumcraft
        Thaumium = new Material.Builder(GTCEu.id("thaumium"))
                .ingot(0)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(3405))
                .color(0x9370DB).iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE)
                .components(Redstone, 1, Magic,1)
                .buildAndRegister();

        //Twilight Forest
        Steeleaf = new Material.Builder(GTCEu.id("steeleaf"))
        .ingot()
        .color(0x008B00).iconSet(METALLIC)
        .flags(GENERATE_PLATE)
        .buildAndRegister();

        Ironwood = new Material.Builder(GTCEu.id("ironwood"))
        .ingot()
        .color(0x556B2F).iconSet(METALLIC)
        .flags(GENERATE_PLATE)
        .buildAndRegister();

        FieryIngot = new Material.Builder(GTCEu.id("fieryingot"))
        .ingot()
        .color(0x8B1A1A).iconSet(METALLIC)
        .flags(GENERATE_PLATE)
        .buildAndRegister();
        
        Knightmetal = new Material.Builder(GTCEu.id("knightmetal"))
        .ingot()
        .color(0xF5F5DC).iconSet(METALLIC)
        .flags(GENERATE_PLATE)
        .buildAndRegister();;

        // They are here due to the needs of components
        Mithril= new Material.Builder(GTCEu.id("mithril"))
        .ingot()
        .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(6600))
        .color(0xFFF8DC).iconSet(METALLIC)
        .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
        .components(Platinum, 2, Thaumium, 1)
        .buildAndRegister();
        
        AstralSilver = new Material.Builder(GTCEu.id("astralsilver"))
        .ingot()
        .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1400))
        .color(0xE6E6FA).iconSet(METALLIC)
        .flags(GENERATE_PLATE, DECOMPOSITION_BY_CENTRIFUGING)
        .components(Silver, 2, Thaumium, 1)
        .buildAndRegister();
    }
}
