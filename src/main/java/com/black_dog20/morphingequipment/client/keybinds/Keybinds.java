package com.black_dog20.morphingequipment.client.keybinds;

import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.client.ClientUtils;
import com.black_dog20.morphingequipment.common.items.MorphingTool;
import com.black_dog20.morphingequipment.common.network.PacketHandler;
import com.black_dog20.morphingequipment.common.network.packets.PacketSwitchMorph;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static com.black_dog20.morphingequipment.common.utils.Translations.KEY_CATEGORY;
import static com.black_dog20.morphingequipment.common.utils.Translations.KEY_SWITCH;

@Mod.EventBusSubscriber(modid = MorphingEquipment.MOD_ID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class Keybinds {

    public static final KeyMapping keySwitch = new KeyMapping(KEY_SWITCH.getDescription(), KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY.getDescription());

    @SubscribeEvent
    public static void onEvent(TickEvent.ClientTickEvent event) {
        Level level = Minecraft.getInstance().level;
        Player player = Minecraft.getInstance().player;
        if (event.phase != TickEvent.Phase.START || level == null || player == null || Minecraft.getInstance().screen != null) { return; }
        if (Keybinds.keySwitch.consumeClick()) {
            if (Screen.hasControlDown()) {
                PacketHandler.sendToServer(new PacketSwitchMorph(MorphingTool.MORPH));
            } else {
                PacketHandler.sendToServer(new PacketSwitchMorph(ClientUtils.getActionFromMouseOver()));
            }
        }
    }

}
