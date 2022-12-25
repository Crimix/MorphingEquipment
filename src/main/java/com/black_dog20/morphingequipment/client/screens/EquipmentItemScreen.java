package com.black_dog20.morphingequipment.client.screens;

import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.client.containers.EquipmentItemContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
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
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        this.font.draw(matrixStack, this.inventory.getDisplayName(), 8, 58, 0xffffff);
        this.font.draw(matrixStack, this.getTitle(), 8, 6, 0xffffff);
    }

    @Override
    protected void renderBg(PoseStack matrixStack,float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
