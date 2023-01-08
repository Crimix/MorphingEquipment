package com.black_dog20.morphingequipment.common.utils;

import com.black_dog20.bml.utils.translate.ITranslation;
import com.black_dog20.morphingequipment.MorphingEquipment;

public enum Translations implements ITranslation {
    TOOLIP_INFO("tooltip.item.info"),
    TOOLTIP_SWITCH("tooltip.item.switch"),
    TOOLTIP_OPEN_CONTAINER("tooltip.item.open"),
    TOOLTIP_MORP_BACK("tooltip.item.morph.back"),
    KEY_SWITCH("key.switch"),
    KEY_CATEGORY("key.category"),
    PLUGIN_SWITCH("tooltip..plugin.item.switch"),
    PLUGIN_MORP_BACK("tooltip..plugin.item.morph.back");

    private final String modId;
    private final String key;

    Translations(String key) {
        this.modId = MorphingEquipment.MOD_ID;
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getModId() {
        return modId;
    }
}
