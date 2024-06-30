package com.black_dog20.morphingequipment;

import com.black_dog20.morphingequipment.client.containers.ModContainers;
import com.black_dog20.morphingequipment.common.items.ModItems;
import com.black_dog20.morphingequipment.common.network.PacketHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(MorphingEquipment.MOD_ID)
public class MorphingEquipment {

    public static final String MOD_ID = "morphingequipment";

    public MorphingEquipment() {
        IEventBus event = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml"));

        ModItems.ITEMS.register(event);
        ModItems.CREATIVE_MODE_TABS.register(event);
        ModContainers.CONTAINERS.register(event);

        event.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.register();
    }
}
