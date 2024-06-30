package com.black_dog20.morphingequipment.common.items;

import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.common.utils.Translations;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final Item.Properties ITEM_GROUP = new Item.Properties();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MorphingEquipment.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MorphingEquipment.MOD_ID);

    public static final RegistryObject<Item> MORPHING_TOOL = ITEMS.register("morphing_tool", () -> new MorphingTool(ITEM_GROUP));

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("forcefieldgenerator_tab", () -> CreativeModeTab.builder()
            .icon(() -> MORPHING_TOOL.get().getDefaultInstance())
            .title(Translations.ITEM_CATEGORY.get(ChatFormatting.RESET))
            .displayItems((parameters, output) -> {
                for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                    output.accept(item.get());
                }
            }).build());
}
