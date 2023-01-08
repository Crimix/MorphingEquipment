package com.black_dog20.morphingequipment.plugins;

import com.black_dog20.morphingequipment.Config;
import com.black_dog20.morphingequipment.MorphingEquipment;
import com.black_dog20.morphingequipment.client.ClientUtils;
import com.black_dog20.morphingequipment.common.items.MorphingTool;
import com.black_dog20.morphingequipment.common.network.PacketHandler;
import com.black_dog20.morphingequipment.common.network.packets.PacketSwitchMorph;
import com.black_dog20.morphingequipment.common.utils.ModUtil;
import com.black_dog20.morphingequipment.common.utils.Tags;
import com.black_dog20.morphingequipment.common.utils.Translations;
import com.black_dog20.theonekey.api.keybind.IContext;
import com.black_dog20.theonekey.api.keybind.IKeybindRegistration;
import com.black_dog20.theonekey.api.keybind.KeyModifier;
import com.black_dog20.theonekey.api.plugin.IModPlugin;
import com.black_dog20.theonekey.api.plugin.OneKeyPlugin;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;

@OneKeyPlugin(modIds = MorphingEquipment.MOD_ID)
public class TheOneKeyPlugin implements IModPlugin {

    @Override
    public boolean isEnabled() {
        return ModList.get().isLoaded(MorphingEquipment.MOD_ID) && Config.PLUGIN_ACTIVE.get();
    }

    @Override
    public void registerKeybinds(IKeybindRegistration registration) {
        registration.registerKeybind(Tags.TAG_EQUIPMENT_CONTAINER, this::isEnabled, KeyModifier.NONE, Translations.PLUGIN_SWITCH.get(), this::handle);
        registration.registerKeybind(Tags.TAG_EQUIPMENT_CONTAINER, this::isEnabled, this::isMorpedTool, KeyModifier.CTRL, Translations.PLUGIN_MORP_BACK.get(), this::handle);
    }

    private boolean isMorpedTool(ItemStack itemStack) {
        return ModUtil.isMorphedTool(itemStack);
    }

    private void handle(IContext context) {
        if (context.getKeyModifier() == KeyModifier.CTRL) {
            PacketHandler.sendToServer(new PacketSwitchMorph(MorphingTool.MORPH));
        } else {
            PacketHandler.sendToServer(new PacketSwitchMorph(ClientUtils.getActionFromMouseOver()));
        }
    }
}
