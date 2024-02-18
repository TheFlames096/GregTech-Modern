package com.gregtechceu.gtceu.api.machine.multiblock;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.capability.IParallelHatch;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.gregtechceu.gtceu.api.gui.fancy.TooltipsPanel;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.IOverclockMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.*;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author KilaBash
 * @date 2023/3/6
 * @implNote WorkableElectricMachine
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WorkableElectricMultiblockMachine extends WorkableMultiblockMachine implements IFancyUIMachine, IDisplayUIMachine, ITieredMachine, IOverclockMachine {
    public WorkableElectricMultiblockMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }
    //runtime
    private long maxHatchVoltage = -1;

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////
    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        maxHatchVoltage = -1;
    }

    @Override
    public void onStructureFormed() {
        maxHatchVoltage = -1;
        super.onStructureFormed();
    }

    @Override
    public void onPartUnload() {
        super.onPartUnload();
        maxHatchVoltage = -1;
    }


    //////////////////////////////////////
    //**********     GUI     ***********//
    //////////////////////////////////////

    @Override
    public void addDisplayText(List<Component> textList) {
        IDisplayUIMachine.super.addDisplayText(textList);
        if (isFormed()) {
            var maxVoltage = getMaxVoltage();
            if (maxVoltage > 0) {
                String voltageName = GTValues.VNF[GTUtil.getFloorTierByVoltage(maxVoltage)];
                textList.add(Component.translatable("gtceu.multiblock.max_energy_per_tick", maxVoltage, voltageName));
            }

//            if (canBeDistinct() && inputInventory.getSlots() > 0) {
//                var buttonText = Component.translatable("gtceu.multiblock.universal.distinct");
//                buttonText.appendText(" ");
//                var button = AdvancedTextWidget.withButton(isDistinct() ?
//                        Component.translatable("gtceu.multiblock.universal.distinct.yes").setStyle(Style.EMPTY.setColor(TextFormatting.GREEN)) :
//                        Component.translatable("gtceu.multiblock.universal.distinct.no").setStyle(Style.EMPTY.setColor(TextFormatting.RED)), "distinct");
//                AdvancedTextWidget.withHoverTextTranslate(button, "gtceu.multiblock.universal.distinct.info");
//                buttonText.appendSibling(button);
//                textList.add(buttonText);
//            }

            textList.add(Component.translatable("gtceu.gui.machinemode", Component.translatable(getRecipeType().registryName.toLanguageKey()))
                    .withStyle(ChatFormatting.AQUA));

            if (!isWorkingEnabled()) {
                textList.add(Component.translatable("gtceu.multiblock.work_paused"));

            } else if (isActive()) {
                textList.add(Component.translatable("gtceu.multiblock.running"));
                int currentProgress = (int) (recipeLogic.getProgressPercent() * 100);
                Optional<IParallelHatch> optional = this.getParts().stream().filter(IParallelHatch.class::isInstance).map(IParallelHatch.class::cast).findAny();
                if (optional.isPresent()) {
                    IParallelHatch parallelHatch = optional.get();
                    if (parallelHatch.getCurrentParallel() != 1) {
                        textList.add(Component.translatable("gtceu.multiblock.parallel", parallelHatch.getCurrentParallel()));
                    }
                }
                if(recipeLogic.getLastRecipe()!=null)
                {
                    for(var a : recipeLogic.getLastRecipe().getTickInputContents(EURecipeCapability.CAP))
                        textList.add(Component.translatable("gtceu.multiblock.eu_usage",a.content.toString()));
                    for(var a : recipeLogic.getLastRecipe().getOutputContents(ItemRecipeCapability.CAP))
                        for(var b : ((Ingredient)a.content).getItems())
                            if(a.chance==1.0F)
                                textList.add(Component.translatable("gtceu.multiblock.item_output",b.getDisplayName().getString(),b.getCount()));
                            else
                                textList.add(Component.translatable("gtceu.multiblock.chance_item_output",b.getDisplayName().getString(),b.getCount(),String.format("%.2f",a.chance*100).toString()));
                    for(var a : recipeLogic.getLastRecipe().getOutputContents(FluidRecipeCapability.CAP))
                        for(var b : ((FluidIngredient)a.content).getStacks())
                            if(a.chance==1.0F)
                                textList.add(Component.translatable("gtceu.multiblock.fluid_output",b.getDisplayName().getString(),b.getAmount()));
                            else
                                textList.add(Component.translatable("gtceu.multiblock.chance_fluid_output",b.getDisplayName().getString(),b.getAmount(),String.format("%.2f",a.chance*100).toString()));
                }
                textList.add(Component.translatable("gtceu.multiblock.progress", currentProgress, String.format("%.2f",recipeLogic.getProgress()/20.0F).toString(),String.format("%.2f",recipeLogic.getMaxProgress()/20.0F).toString()));
                
            } else {
                textList.add(Component.translatable("gtceu.multiblock.idling"));
            }

            if (recipeLogic.isWaiting()) {
                textList.add(Component.translatable("gtceu.multiblock.waiting").setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
            }
        }
        getDefinition().getAdditionalDisplay().accept(this, textList);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        group.addWidget(new DraggableScrollableWidgetGroup(4, 4, 182, 117).setBackground(getScreenTexture())
                .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()))
                .addWidget(new ComponentPanelWidget(4, 17, this::addDisplayText)
                        .setMaxWidthLimit(150)
                        .clickHandler(this::handleDisplayClick)));
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(198, 208, this, entityPlayer).widget(new FancyMachineUIWidget(this, 198, 208));
    }

    @Override
    public List<IFancyUIProvider> getSubTabs() {
        return getParts().stream().filter(IFancyUIProvider.class::isInstance).map(IFancyUIProvider.class::cast).toList();
    }

    @Override
    public void attachTooltips(TooltipsPanel tooltipsPanel) {
        for (IMultiPart part : getParts()) {
            part.attachFancyTooltipsToController(this, tooltipsPanel);
        }
    }


    //////////////////////////////////////
    //********     OVERCLOCK   *********//
    //////////////////////////////////////
    @Override
    public int getOverclockTier() {
        return getTier();
    }

    @Override
    public int getMaxOverclockTier() {
        return getTier();
    }

    @Override
    public int getMinOverclockTier() {
        return getTier();
    }

    @Override
    public void setOverclockTier(int tier) {

    }

    @Override
    public long getOverclockVoltage() {
        return getMaxVoltage();
    }

    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    /**
     * Get energy tier.
     */
    @Override
    public int getTier() {
        return GTUtil.getFloorTierByVoltage(getMaxVoltage());
    }

    public long getMaxHatchVoltage() {
        if (maxHatchVoltage < 0)  {
            maxHatchVoltage = 0L;
            var capabilities = capabilitiesProxy.get(IO.IN, EURecipeCapability.CAP);
            if (capabilities != null) {
                for (IRecipeHandler<?> handler : capabilities) {
                    if (handler instanceof IEnergyContainer container) {
                        maxHatchVoltage += container.getInputVoltage() * container.getInputAmperage();
                    }
                }
            } else {
                capabilities = capabilitiesProxy.get(IO.OUT, EURecipeCapability.CAP);
                if (capabilities != null) {
                    for (IRecipeHandler<?> handler : capabilities) {
                        if (handler instanceof IEnergyContainer container) {
                            maxHatchVoltage += container.getOutputVoltage() * container.getOutputAmperage();
                        }
                    }
                }
            }
        }
        return maxHatchVoltage;
    }

    public long getMaxVoltage() {
        return GTValues.V[GTUtil.getFloorTierByVoltage(getMaxHatchVoltage())];
    }
}
