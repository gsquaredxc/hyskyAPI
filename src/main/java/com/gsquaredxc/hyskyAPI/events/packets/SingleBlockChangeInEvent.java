package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;

public class SingleBlockChangeInEvent extends PacketReceiveEvent{
    public final BlockPos pos;
    public final IBlockState state;

    public SingleBlockChangeInEvent(final S23PacketBlockChange originalPacket) {
        super(originalPacket);
        pos = originalPacket.getBlockPosition();
        state = originalPacket.getBlockState();
    }
}
