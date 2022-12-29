package com.black_dog20.morphingequipment.common.utils;

import com.black_dog20.morphingequipment.common.items.ModItems;
import com.black_dog20.morphingequipment.common.items.MorphingTool;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolAction;

public class ModUtil {

    public static boolean isMorphingOrMorphedTool(ItemStack stack) {
        return stack.copy().getOrCreateTag().contains(Tags.TAG_EQUIPMENT_CONTAINER);
    }

    public static boolean isMorphedTool(ItemStack stack) {
        return ModUtil.isMorphingOrMorphedTool(stack) && !stack.is(ModItems.MORPHING_TOOL.get());
    }

    public static void switchItem(Player player, ToolAction action) {
        if (action == MorphingTool.NONE) {
            return;
        }
        ItemStack stack = player.getMainHandItem();

        if (!stack.isEmpty() && isMorphingOrMorphedTool(stack)) {
            EquipmentItemHandler handler = new EquipmentItemHandler(stack);
            ItemStack newTool = handler.switchTool(stack, action);

            player.setItemInHand(InteractionHand.MAIN_HAND, newTool);
        }
    }

    public static void switchBecauseBreakingItem(Player player, InteractionHand hand, ItemStack breakingItem) {
        EquipmentItemHandler handler = new EquipmentItemHandler(breakingItem);
        ItemStack newTool = handler.switchTool(ItemStack.EMPTY, MorphingTool.MORPH);
        player.drop(newTool, true);
    }
}
