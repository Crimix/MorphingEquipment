package com.black_dog20.morphingequipment.client.containers;

import com.black_dog20.morphingequipment.MorphingEquipment;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MorphingEquipment.MOD_ID);

    public static final RegistryObject<MenuType<EquipmentItemContainer>> EQUIPMENT_ITEM_CONTAINER = CONTAINERS.register("equipment_item_container", () -> IForgeMenuType.create((windowId, inv, data) -> new EquipmentItemContainer(windowId, inv, inv.player)));
}
