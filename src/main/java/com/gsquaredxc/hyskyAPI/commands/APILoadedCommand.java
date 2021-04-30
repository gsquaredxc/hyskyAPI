package com.gsquaredxc.hyskyAPI.commands;

import com.google.common.collect.Lists;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class APILoadedCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "apiloaded";
    }

    @Override
    public List<String> getCommandAliases() {
        return Lists.newArrayList("apiload");
    }

    @Override
    public String getCommandUsage(final ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(final ICommandSender sender, final String[] args, final BlockPos pos) {
        return null;
    }

    @Override
    public void processCommand(final ICommandSender sender, final String[] args) throws CommandException {
        final EntityPlayerSP player = (EntityPlayerSP) sender;
        player.addChatMessage(new ChatComponentText("§a§l[SUCCESS] §8» §aThe API has been loaded."));
    }
}
