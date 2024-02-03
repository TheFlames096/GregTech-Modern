package com.gregtechceu.gtceu.data.recipe.misc;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.mojang.datafixers.types.templates.List;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;

import java.util.ArrayList;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.foil;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.nanites;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;
import static com.gregtechceu.gtceu.common.data.GTItems.ADVANCED_CIRCUIT_BOARD;
import static com.gregtechceu.gtceu.common.data.GTItems.BIO_CIRCUIT_BOARD;
import static com.gregtechceu.gtceu.common.data.GTItems.ELITE_CIRCUIT_BOARD;
import static com.gregtechceu.gtceu.common.data.GTItems.EXTREME_CIRCUIT_BOARD;
import static com.gregtechceu.gtceu.common.data.GTItems.OPTICAL_CIRCUIT_BOARD;
import static com.gregtechceu.gtceu.common.data.GTItems.PLASTIC_CIRCUIT_BOARD;
import static com.gregtechceu.gtceu.common.data.GTItems.WETWARE_CIRCUIT_BOARD;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class PCBLoader {
        public static void init(Consumer<FinishedRecipe> provider) {

                COOLING_RECIPES.recipeBuilder("water_cooling")
                        .inputFluids(GTMaterials.DistilledWater.getFluid(10))
                        .duration(20)
                        .addData("liquid", "Distilled Water")
                        .save(provider);
                SUPER_COOLING_RECIPES.recipeBuilder("coolent_cooling")
                        .inputFluids(GTMaterials.PCBCoolant.getFluid(10))
                        .duration(20)
                        .addData("liquid", "PCB Coolent")
                        .save(provider);
                recipemaker(provider,500,250,0,PLASTIC_CIRCUIT_BOARD.get(),new UnificationEntry(foil,AnnealedCopper),new UnificationEntry(foil,Copper),null,null,0,false,"plastic_board",665.0D,22.5D);
                recipemaker(provider,500,500,0,ADVANCED_CIRCUIT_BOARD.get(),new UnificationEntry(foil,Gold),new UnificationEntry(foil,Electrum),null,null,1,false,"advanced_board",665.0D,90.0D);
                recipemaker(provider,1000,500,0,EXTREME_CIRCUIT_BOARD.get(),new UnificationEntry(foil,Aluminium),new UnificationEntry(foil,EnergeticAlloy),null,null,2,false,"extremely_board",665.0D,360.0D);
                recipemaker(provider,2000,500,0,ELITE_CIRCUIT_BOARD.get(),new UnificationEntry(foil,Palladium),new UnificationEntry(foil,Platinum),null,null,3,false,"elite_board",665.0D,1440.0D);
                recipemaker(provider,5000,500,2000,WETWARE_CIRCUIT_BOARD.get(),new UnificationEntry(foil,EnrichedHolmium),new UnificationEntry(foil,NiobiumTitanium),null,RawGrowthMedium.getFluid(),4,true,"wetware_board",665.0D,5760.0D);
                recipemaker(provider,7500,500,4000,BIO_CIRCUIT_BOARD.get(),new UnificationEntry(foil,SuperconductorBaseUV),new UnificationEntry(foil,Neutronium),null,SterileGrowthMedium.getFluid(),5,true,"bio_board",543.0D,23040.0D);
                recipemaker(provider,12000,500,2880,OPTICAL_CIRCUIT_BOARD.get(),new UnificationEntry(foil,Tairitsu),new UnificationEntry(foil,InfinityCatalyst),new UnificationEntry(foil,ChromaticGlass),MysteriousCrystal.getFluid(),6,false,"optical_board",443.0D,92160.0D);
        }
        public static void recipemaker(Consumer<FinishedRecipe> provider,int fluid1Amount,int fluid2Amount,int fluid3Amount,Item output,UnificationEntry input1,UnificationEntry input2,UnificationEntry input3,Fluid fluid3,int materialTier,boolean bio,String name,double basetime,double baseEUUsage)
        {
                int[] inputAmount={16,22,27,32,35,39,42,45};
                int[] outputAmount={8,12,16,23,32,46,64,91,128};
                Material[] material= {Polyethylene,PolyvinylChloride,Polytetrafluoroethylene,Epoxy,FiberReinforcedEpoxyResin,Polybenzimidazole,Kevlar,RadoxPolymer};
                double currentTime=basetime;
                double currentUsage=baseEUUsage;
                for(int i=0;i<8-materialTier;i++)
                {
                        var temp=PCB_RECIPES.recipeBuilder("pcb_"+name+"_"+i+"_1")
                        .circuitMeta(1)
                        .inputFluids(SulfuricAcid.getFluid(fluid1Amount*inputAmount[i]/16))
                        .inputFluids(Iron3Chloride.getFluid(fluid2Amount*inputAmount[i]/16))
                        .inputItems(plate,material[i+materialTier])
                        .inputItems(input1,inputAmount[i])
                        .inputItems(input2,inputAmount[i])
                        .outputItems(output,outputAmount[i])
                        .duration((int)currentTime)
                        .EUt((int)currentUsage)
                        .addData("tier", 1)
                        .addData("require_bio", bio);
                        if(fluid3!=null)
                                temp.inputFluids(FluidStack.create(fluid3,fluid3Amount*inputAmount[i]/16));
                        if(input3!=null)
                                temp.inputItems(input3,inputAmount[i]);
                        temp.save(provider);
                        currentTime=currentTime*9.0D/11.0D;
                        currentUsage=currentUsage*4.0D;
                }
                currentTime=basetime*5.0D/6.0D;
                currentUsage=baseEUUsage*4.0D;
                for(int i=0;i<8-materialTier;i++)
                {
                        var temp=PCB_RECIPES.recipeBuilder("pcb_"+name+"_"+i+"_2")
                        .circuitMeta(2)
                        .notConsumable(nanites,Silver)
                        .inputFluids(SulfuricAcid.getFluid(fluid1Amount*inputAmount[i]/16))
                        .inputFluids(Iron3Chloride.getFluid(fluid2Amount*inputAmount[i]/16))
                        .inputItems(plate,material[i+materialTier])
                        .inputItems(input1,inputAmount[i])
                        .inputItems(input2,inputAmount[i])
                        .outputItems(output,(outputAmount[i]+outputAmount[i+1])/2)
                        .duration((int)currentTime)
                        .EUt((int)currentUsage)
                        .addData("tier", 2)
                        .addData("require_bio", bio);
                        if(fluid3!=null)
                                temp.inputFluids(FluidStack.create(fluid3,fluid3Amount*inputAmount[i]/16));
                        if(input3!=null)
                                temp.inputItems(input3,inputAmount[i]);
                        temp.save(provider);
                        currentTime=currentTime*9.0D/11.0D;
                        currentUsage=currentUsage*4.0D;
                }
                currentTime=basetime*2.0D/3.0D;
                currentUsage=baseEUUsage*4.0D;
                for(int i=0;i<8-materialTier;i++)
                {
                        var temp=PCB_RECIPES.recipeBuilder("pcb_"+name+"_"+i+"_3")
                        .circuitMeta(3)
                        .notConsumable(nanites,Gold)
                        .inputFluids(SulfuricAcid.getFluid(fluid1Amount*inputAmount[i]/16))
                        .inputFluids(Iron3Chloride.getFluid(fluid2Amount*inputAmount[i]/16))
                        .inputItems(plate,material[i+materialTier])
                        .inputItems(input1,inputAmount[i])
                        .inputItems(input2,inputAmount[i])
                        .outputItems(output,(outputAmount[i+1]))
                        .duration((int)currentTime)
                        .EUt((int)currentUsage)
                        .addData("tier", 3)
                        .addData("require_bio", bio);
                        if(fluid3!=null)
                                temp.inputFluids(FluidStack.create(fluid3,fluid3Amount*inputAmount[i]/16));
                        if(input3!=null)
                                temp.inputItems(input3,inputAmount[i]);
                        temp.save(provider);
                        currentTime=currentTime*9.0D/11.0D;
                        currentUsage=currentUsage*4.0D;
                }
        }
}
