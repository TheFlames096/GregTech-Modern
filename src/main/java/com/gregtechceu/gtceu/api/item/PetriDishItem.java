package com.gregtechceu.gtceu.api.item;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PetriDishItem extends Item{

    public PetriDishItem(Properties properties) {
        super(properties);
    }
    static public Map<String,GTRecipeType> recipe =new HashMap<String,GTRecipeType>();
    public String containedCulture="none";

    public void initname(String culture)
    {
        containedCulture = culture;
    }
    @Nullable
    public GTRecipeType getRecipe()
    {
        if(recipe.containsKey(containedCulture))
        {
            return recipe.get(containedCulture);
        }
        return null;
    }
    public static void initrecipe(String culture)
    {
        PetriDishItem.recipe.put(culture,register("bacterial_vat_"+culture.toLowerCase().replace(' ', '_'), MULTIBLOCK).setMaxIOSize(6, 2, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.petri_dish", data.getString("bacteria")))
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.min_sievert", data.getInt("min_sievert")))
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.require_exactle_sievert", String.valueOf(data.getBoolean("is_exact"))))
            .setSound(GTSoundEntries.CHEMICAL)
            .onRecipeBuild((recipeBuilder, provider) -> GTRecipeTypes.BACTERIAL_VAT_RECIPES.copyFrom(recipeBuilder).save(provider)));
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced)
    {
        if(stack.getItem() instanceof PetriDishItem petri_dish)
        {
            tooltipComponents.add(Component.translatable("gtceu.petri_dish.culture",petri_dish.containedCulture));
        }
    }
}
