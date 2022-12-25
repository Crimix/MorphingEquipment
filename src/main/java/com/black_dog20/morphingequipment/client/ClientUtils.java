package com.black_dog20.morphingequipment.client;

import com.black_dog20.morphingequipment.common.items.MorphingTool;
import net.minecraft.client.Minecraft;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.Set;

public class ClientUtils {

    private static final Set<Block> SPECIAL_SWORD_BLOCKS = Set.of(Blocks.COBWEB, Blocks.BAMBOO, Blocks.BAMBOO_SAPLING);

    public static ToolAction getActionFromMouseOver() {
        Player player = Minecraft.getInstance().player;
        HitResult result = Minecraft.getInstance().hitResult;
        if (result != null && result.getType() == HitResult.Type.BLOCK) {
            return getActionFromBlockState(player.level.getBlockState(((BlockHitResult)result).getBlockPos()));
        } else {
            return ToolActions.SWORD_DIG;
        }
    }

    private static ToolAction getActionFromBlockState(BlockState blockState) {
        if (blockState.is(BlockTags.MINEABLE_WITH_AXE)) {
            return ToolActions.AXE_DIG;
        } else if (blockState.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
            return ToolActions.PICKAXE_DIG;
        } else if (blockState.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            return ToolActions.SHOVEL_DIG;
        } else if (SPECIAL_SWORD_BLOCKS.contains(blockState.getBlock())) {
            return ToolActions.SWORD_DIG;
        } else {
            return MorphingTool.NONE;
        }
    }

    public static HitResult raycast(Player e, double len) {
        Vec3 vec = new Vec3(e.getX(), e.getY(), e.getZ());
        vec = vec.add(new Vec3(0, e.getEyeHeight(), 0));

        Vec3 look = e.getLookAngle();
        if (look == null) {
            return null;
        }

        return raycast(e, vec, look, len);
    }

    public static HitResult raycast(Player e, Vec3 origin, Vec3 ray, double len) {
        Vec3 end = origin.add(ray.normalize().scale(len));
        return e.getCommandSenderWorld().clip(new ClipContext(origin, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, e));
    }
}
