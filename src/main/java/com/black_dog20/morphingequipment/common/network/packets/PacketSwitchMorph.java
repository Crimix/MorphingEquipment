package com.black_dog20.morphingequipment.common.network.packets;

import com.black_dog20.morphingequipment.common.utils.ModUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSwitchMorph {

    private ToolAction action;

    public PacketSwitchMorph(ToolAction action) {
        this.action = action;
    }

    public static void encode(PacketSwitchMorph msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.action.name());

    }

    public static PacketSwitchMorph decode(FriendlyByteBuf buffer) {
        return new PacketSwitchMorph(ToolAction.get(buffer.readUtf()));
    }

    public static class Handler {
        public static void handle(PacketSwitchMorph msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player == null)
                    return;

                ModUtil.switchItem(player, msg.action);
            });

            ctx.get().setPacketHandled(true);
        }
    }
}
