package com.black_dog20.morphingequipment.common.items;

import com.black_dog20.bml.utils.keybinds.KeybindsUtil;
import com.black_dog20.morphingequipment.client.containers.EquipmentItemContainer;
import com.black_dog20.morphingequipment.client.keybinds.Keybinds;
import com.black_dog20.morphingequipment.common.capabilities.MorphingToolCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

import static com.black_dog20.morphingequipment.common.utils.Translations.*;

public class MorphingTool extends Item {

    public static final ToolAction MORPH = ToolAction.get("morphingequipment:morph");
    public static final ToolAction NONE = ToolAction.get("morphingequipment:none");

    public MorphingTool(Properties builder) {
        super(builder.durability(-1).setNoRepair().stacksTo(1).fireResistant());
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new MorphingToolCapability(stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(TOOLIP_INFO.get());
        tooltip.add(TOOLTIP_OPEN_CONTAINER.get());
        tooltip.add(TOOLTIP_SWITCH.get(ChatFormatting.GRAY, KeybindsUtil.getKeyBindText(Keybinds.keySwitch)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player.isCrouching()) {
            if (!world.isClientSide) {
                player.openMenu(new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return player.getItemInHand(hand).getHoverName();
                    }

                    @Nullable
                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player) {
                        return new EquipmentItemContainer(windowId, playerInventory, player);
                    }
                });
            }
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
        return super.use(world, player, hand);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return toolAction == MORPH;
    }
}
