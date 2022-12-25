package com.black_dog20.morphingequipment.common.events;

import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.common.utils.ModUtil;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MorphingEquipment.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onItemBroken(PlayerDestroyItemEvent event) {
        if (event.getHand() != null && ModUtil.isMorphedTool(event.getOriginal()) && !event.getEntity().level.isClientSide) {
            ModUtil.switchBecauseBreakingItem(event.getEntity(), event.getHand(), event.getOriginal());
        }
    }
}
