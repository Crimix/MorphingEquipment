package com.black_dog20.morphingequipment.client;

import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.client.containers.ModContainers;
import com.black_dog20.morphingequipment.client.keybinds.Keybinds;
import com.black_dog20.morphingequipment.client.screens.EquipmentItemScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MorphingEquipment.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientStartup {

    @SubscribeEvent
    public static void registerKeyBinding(RegisterKeyMappingsEvent event) {
        event.register(Keybinds.keySwitch);
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        MenuScreens.register(ModContainers.EQUIPMENT_ITEM_CONTAINER.get(), EquipmentItemScreen::new);
    }
}