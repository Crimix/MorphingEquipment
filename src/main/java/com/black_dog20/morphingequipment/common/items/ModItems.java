package com.black_dog20.morphingequipment.common.items;

import com.black_dog20.morphingequipment.MorphingEquipment;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final Item.Properties ITEM_GROUP = new Item.Properties().tab(MorphingEquipment.itemGroup);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MorphingEquipment.MOD_ID);

    public static final RegistryObject<Item> MORPHING_TOOL = ITEMS.register("morphing_tool", () -> new MorphingTool(ITEM_GROUP));
}
