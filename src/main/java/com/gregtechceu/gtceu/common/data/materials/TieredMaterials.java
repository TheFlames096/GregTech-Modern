package com.gregtechceu.gtceu.common.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class TieredMaterials {
    public static void register() {

        SuperconductorBaseMV =new Material.Builder(GTCEu.id("superconductorbasemv"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseHV =new Material.Builder(GTCEu.id("superconductorbasehv"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseEV =new Material.Builder(GTCEu.id("superconductorbaseev"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseIV =new Material.Builder(GTCEu.id("superconductorbaseiv"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseLuV =new Material.Builder(GTCEu.id("superconductorbaseluv"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseZPM =new Material.Builder(GTCEu.id("superconductorbasezpm"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseUV =new Material.Builder(GTCEu.id("superconductorbaseuv"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseUHV =new Material.Builder(GTCEu.id("superconductorbaseuhv"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseUEV =new Material.Builder(GTCEu.id("superconductorbaseuev"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseUIV =new Material.Builder(GTCEu.id("superconductorbaseuiv"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        SuperconductorBaseUXV =new Material.Builder(GTCEu.id("superconductorbaseuxv"))
            .ingot(1).fluid()
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 1, 2)
            .appendFlags(STD_METAL)
            .components(Cadmium, 5, Magnesium, 1,Oxygen,6)
            .blastTemp(2500, GasTier.LOWEST, GTValues.VA[HV], 600)
            .buildAndRegister();
        
        SuperconductorMV = new Material.Builder(GTCEu.id("superconductormv"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseMV, 1)
            .buildAndRegister();
        SuperconductorHV = new Material.Builder(GTCEu.id("superconductorhv"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseHV, 1)
            .buildAndRegister();
        SuperconductorEV = new Material.Builder(GTCEu.id("superconductorev"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseEV, 1)
            .buildAndRegister();
        SuperconductorIV = new Material.Builder(GTCEu.id("superconductoriv"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseIV, 1)
            .buildAndRegister();
        SuperconductorLuV = new Material.Builder(GTCEu.id("superconductorluv"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseLuV, 1)
            .buildAndRegister();
        SuperconductorZPM = new Material.Builder(GTCEu.id("superconductorzpm"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseZPM, 1)
            .buildAndRegister();
        SuperconductorUV = new Material.Builder(GTCEu.id("superconductoruv"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseUV, 1)
            .buildAndRegister();
        SuperconductorUHV = new Material.Builder(GTCEu.id("superconductoruhv"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseUHV, 1)
            .buildAndRegister();
        SuperconductorUEV = new Material.Builder(GTCEu.id("superconductoruev"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseUEV, 1)
            .buildAndRegister();
        SuperconductorUIV = new Material.Builder(GTCEu.id("superconductoruiv"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseUIV, 1)
            .buildAndRegister();
        SuperconductorUXV = new Material.Builder(GTCEu.id("superconductoruxv"))
            .color(0x999900).iconSet(METALLIC).cableProperties(GTValues.V[MV], 4, 0,true)
            .appendFlags(STD_METAL,NO_SMASHING, NO_SMELTING,DISABLE_DECOMPOSITION)
            .components(SuperconductorBaseUXV, 1)
            .buildAndRegister();
        
    }
}
