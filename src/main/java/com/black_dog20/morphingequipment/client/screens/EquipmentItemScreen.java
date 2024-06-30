package com.black_dog20.morphingequipment.client.screens;

import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.client.containers.EquipmentItemContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class EquipmentItemScreen extends AbstractContainerScreen<EquipmentItemContainer> {

    private final ResourceLocation GUI = new ResourceLocation(MorphingEquipment.MOD_ID, "textures/gui/container_gui.png");
    private final Inventory inventory;

    public EquipmentItemScreen(EquipmentItemContainer container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
        this.inventory = playerInventory;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(pGuiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int mouseX, int mouseY) {
        pGuiGraphics.drawString(this.font, this.getTitle(), this.titleLabelX, this.titleLabelY, 4210752, false);
        pGuiGraphics.drawString(this.font, this.inventory.getDisplayName(), this.inventoryLabelX, 58, 4210752, false);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics,float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
