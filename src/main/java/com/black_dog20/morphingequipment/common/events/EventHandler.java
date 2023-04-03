package com.black_dog20.morphingequipment.common.events;

import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.common.utils.ModUtil;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onItemFrameInsert(PlayerInteractEvent.EntityInteract event) {
        ItemStack itemstack = event.getEntity().getItemInHand(event.getHand());
        ItemStack copy = itemstack.copy();
        if (ModUtil.isMorphedTool(itemstack) && EntityType.ITEM_FRAME.equals(event.getTarget().getType())) {
            InteractionResult interactionresult = event.getTarget().interact(event.getEntity(), event.getHand());
            if (interactionresult.consumesAction()) {
                if (event.getEntity().getAbilities().instabuild && itemstack == event.getEntity().getItemInHand(event.getHand()) && itemstack.getCount() < copy.getCount()) {
                    itemstack.setCount(copy.getCount());
                }
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
}
