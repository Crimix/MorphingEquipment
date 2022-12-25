package com.black_dog20.morphingequipment.common.capabilities;

import com.black_dog20.morphingequipment.common.utils.EquipmentItemHandler;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MorphingToolCapability implements ICapabilityProvider {

    private ItemStack container;
    private LazyOptional<IItemHandler> capability = LazyOptional.of(() -> new EquipmentItemHandler(container));

    public MorphingToolCapability(ItemStack stack) {
        container = stack;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return capability.cast();
        else
            return LazyOptional.empty();
    }
}
