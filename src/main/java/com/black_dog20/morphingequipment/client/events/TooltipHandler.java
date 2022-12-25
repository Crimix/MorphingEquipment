package com.black_dog20.morphingequipment.client.events;

import com.black_dog20.bml.utils.keybinds.KeybindsUtil;
import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.client.keybinds.Keybinds;
import com.black_dog20.morphingequipment.common.utils.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.black_dog20.morphingequipment.common.utils.Translations.TOOLTIP_MORP_BACK;
import static com.black_dog20.morphingequipment.common.utils.Translations.TOOLTIP_SWITCH;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MorphingEquipment.MOD_ID, value = Dist.CLIENT)
public class TooltipHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (!itemStack.isEmpty() && ModUtil.isMorphedTool(itemStack)) {
            event.getToolTip().add(TOOLTIP_SWITCH.get(ChatFormatting.GRAY, KeybindsUtil.getKeyBindText(Keybinds.keySwitch)));
            event.getToolTip().add(TOOLTIP_MORP_BACK.get(ChatFormatting.GRAY, KeybindsUtil.getKeyBindText(Keybinds.keySwitch)));

        }
    }
}
