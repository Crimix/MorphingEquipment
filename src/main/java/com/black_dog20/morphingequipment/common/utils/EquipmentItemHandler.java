package com.black_dog20.morphingequipment.common.utils;

import com.black_dog20.morphingequipment.common.items.MorphingTool;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.black_dog20.morphingequipment.common.utils.Tags.TAG_EQUIPMENT_CONTAINER;

public class EquipmentItemHandler extends ItemStackHandler {
    private static final int SIZE = 5;
    private ItemStack container;

    private static final List<Predicate<ItemStack>> SLOT_VALIDATOR_ACTION = List.of(
            canPerformAction(ToolActions.SWORD_DIG),
            canPerformAction(ToolActions.PICKAXE_DIG),
            canPerformAction(ToolActions.AXE_DIG),
            canPerformAction(ToolActions.SHOVEL_DIG),
            canPerformAction(MorphingTool.MORPH)
    );

    private static final List<Predicate<ItemStack>> SLOT_VALIDATOR_TYPE = List.of(
            matchType(SwordItem.class),
            matchType(PickaxeItem.class),
            matchType(AxeItem.class),
            matchType(ShovelItem.class),
            matchType(MorphingTool.class)
    );

    private static final Map<ToolAction, Class<? extends Item>> MAPPING = Map.of(
            ToolActions.SWORD_DIG, SwordItem.class,
            ToolActions.PICKAXE_DIG, PickaxeItem.class,
            ToolActions.AXE_DIG, AxeItem.class,
            ToolActions.SHOVEL_DIG, ShovelItem.class,
            MorphingTool.MORPH, MorphingTool.class
    );

    public EquipmentItemHandler(ItemStack container) {
        super(SIZE);
        this.container = container;
    }

    @Override
    protected void onContentsChanged(int slot) {
        this.serializeNBT();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        boolean valid = super.isItemValid(slot, stack);
        if (!valid) {
            return false;
        }
        if (stack.getOrCreateTag().contains(TAG_EQUIPMENT_CONTAINER)) {
            return false;
        }

        return SLOT_VALIDATOR_TYPE.get(slot).test(stack) && SLOT_VALIDATOR_ACTION.get(slot).test(stack);
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        this.load();
        super.setStackInSlot(slot, stack);
        this.save();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        this.load();
        return super.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        this.load();
        ItemStack ret = super.insertItem(slot, stack, simulate);
        this.save();
        return ret;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        this.load();
        return super.extractItem(slot, amount, simulate);
    }

    @Nonnull
    private ItemStack insertItemNoLoadOrSave(@Nonnull ItemStack stack) {
        ItemStack ret = stack;
        stack.getOrCreateTag().remove(TAG_EQUIPMENT_CONTAINER);
        for (int slot = 0; slot < stacks.size(); slot++) {
            ret = super.insertItem(slot, stack, false);
            if (ret.isEmpty()) {
                break;
            }
        }
        return ret;
    }

    @Nonnull
    private ItemStack extractItemNoLoadOrSave(@Nonnull ToolAction toolAction) {
        Class<? extends Item> toolClass = MAPPING.get(toolAction);
        OptionalInt slot = IntStream.range(0, SIZE)
                .filter(i -> matchType(toolClass).test(getStackInSlot(i)))
                .findFirst();

        if (slot.isEmpty()) {
            slot = IntStream.range(0, SIZE)
                    .filter(i -> canPerformAction(toolAction).test(getStackInSlot(i)))
                    .findFirst();
        }

        ItemStack ret;
        if (slot.isEmpty()) {
            ret = extractItemNoLoadOrSave(MorphingTool.MORPH);
        } else {
            ret = super.extractItem(slot.getAsInt(), 1, false);
        }
        return ret;
    }

    public ItemStack switchTool(ItemStack oldTool, ToolAction newToolAction) {
        this.load();
        if (newToolAction == MorphingTool.NONE) {
            return oldTool;
        }

        ItemStack result = ItemStack.EMPTY;
        if (!oldTool.isEmpty()) {
            result = insertItemNoLoadOrSave(oldTool);
        }

        if (result.isEmpty()) {
            result = extractItemNoLoadOrSave(newToolAction);
        }

        if (result.isEmpty()) {
            result = oldTool;
        }
        container = result;
        oldTool.getOrCreateTag().remove(TAG_EQUIPMENT_CONTAINER);
        this.save();
        return result;
    }

    private void load() {
        CompoundTag compoundNBT = container.getOrCreateTag();
        if (compoundNBT.contains(TAG_EQUIPMENT_CONTAINER)) {
            super.deserializeNBT(compoundNBT.getCompound(TAG_EQUIPMENT_CONTAINER));
        }
    }

    private void save() {
        serializeNBT();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = super.serializeNBT();
        container.getOrCreateTag().put(TAG_EQUIPMENT_CONTAINER, nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        CompoundTag compoundNBT = container.getOrCreateTag();
        if (compoundNBT.contains(TAG_EQUIPMENT_CONTAINER)) {
            super.deserializeNBT(compoundNBT.getCompound(TAG_EQUIPMENT_CONTAINER));
        }
    }

    private static Predicate<ItemStack> canPerformAction(ToolAction action) {
        return stack -> stack.canPerformAction(action);
    }

    private static Predicate<ItemStack> matchType(Class<? extends Item> clazz) {
        return stack -> clazz.isInstance(stack.getItem());
    }
}
