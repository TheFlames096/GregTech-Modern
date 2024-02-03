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
public class DataSaverItem extends Item{

    public DataSaverItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced)
    {
        if(stack.getTag()!=null)
        {
            if(stack.getTag().getCompound("coordinate")!=null)
            tooltipComponents.add(Component.translatable("gtceu.display.coordinate",stack.getTag().getCompound("coordinate").getInt("x"),stack.getTag().getCompound("coordinate").getInt("y"),stack.getTag().getCompound("coordinate").getInt("z")));

        }
    }
}
