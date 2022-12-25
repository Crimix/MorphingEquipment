package com.black_dog20.morphingequipment.client.containers;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotEquipment extends SlotItemHandler {

    public SlotEquipment(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
