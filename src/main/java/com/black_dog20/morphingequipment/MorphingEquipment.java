package com.black_dog20.morphingequipment;

import com.black_dog20.morphingequipment.client.containers.ModContainers;
import com.black_dog20.morphingequipment.common.items.ModItems;
import com.black_dog20.morphingequipment.common.network.PacketHandler;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MorphingEquipment.MOD_ID)
public class MorphingEquipment {

    public static final String MOD_ID = "morphingequipment";

    public static CreativeModeTab itemGroup = new CreativeModeTab(MorphingEquipment.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MORPHING_TOOL.get());
        }
    };

    public MorphingEquipment() {
        IEventBus event = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(event);
        ModContainers.CONTAINERS.register(event);

        event.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.register();
    }
}
