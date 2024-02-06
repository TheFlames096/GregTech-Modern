package com.gregtechceu.gtceu.api.machine.fancyconfigurator;

import com.gregtechceu.gtceu.api.machine.feature.IOverclockMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.PCBFactory.PCBBasePart;
import com.lowdragmc.lowdraglib.gui.editor.Icons;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.widget.ButtonWidget;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.TextFieldWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.network.FriendlyByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfigurator;
import com.gregtechceu.gtceu.api.gui.widget.PredicatedButtonWidget;

public class PCBAccuracyConfigurator implements IFancyConfigurator {
    protected PCBBasePart overclockMachine;

    public PCBAccuracyConfigurator(PCBBasePart overclockMachine) {
        this.overclockMachine = overclockMachine;
    }

    @Override
    public String getTitle() {
        return "gtceu.gui.pcbacc.title";
    }

    @Override
    public IGuiTexture getIcon() { 
        return new ResourceTexture("gtceu:textures/item/mv_robot_arm.png");
    }

    @Override
    public Widget createConfigurator() {
        return new WidgetGroup(0, 0, 120, 40) {
            Supplier<String> textSupplier = () -> String.valueOf(overclockMachine.accuracy);
            Consumer<String> textResponder = text -> {
                overclockMachine.accuracy=Integer.valueOf(text);
            };
            @Override
            public void initWidget() {
                super.initWidget();
                setBackground(GuiTextures.BACKGROUND_INVERSE);
                addWidget(new TextFieldWidget(10, 10, 100, 20,textSupplier,textResponder).setNumbersOnly(50, 200));
            }
        };
    }
}
