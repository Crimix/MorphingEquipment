package com.black_dog20.morphingequipment.common.datagen;

import com.black_dog20.bml.datagen.BaseLanguageProvider;
import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.common.items.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;

import static com.black_dog20.morphingequipment.common.utils.Translations.*;

public class GeneratorLanguageEnglish extends BaseLanguageProvider {

    public GeneratorLanguageEnglish(DataGenerator gen) {
        super(gen, MorphingEquipment.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.morphingequipment", "Morphing Equipment");

        addPrefixed(TOOLIP_INFO, "Can morph into other contained tools", ChatFormatting.GRAY);
        addPrefixed(TOOLTIP_SWITCH, "Use %s to morph tool");
        addPrefixed(TOOLTIP_OPEN_CONTAINER, "Sneak + Right click to open tool", ChatFormatting.GRAY);
        addPrefixed(TOOLTIP_MORP_BACK, "Ctrl + %s to morph tool to original form");

        add(ModItems.MORPHING_TOOL.get(), "Morphing Tool");

        addPrefixed(KEY_CATEGORY, "Morphing Equipment");
        addPrefixed(KEY_SWITCH, "Morph tool to match requirements");
    }

    @Override
    public String getName() {
        return "Morphing Equipment: Languages: en_us";
    }
}
