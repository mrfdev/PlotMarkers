// PlotMarkers - Add plot markers to BlueMap map
// Copyright 2026 Bobcat00
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

package com.bobcat00.plotmarkers;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public final class PlotMarkersCommand implements TabExecutor
{
    private static final String DOCS_URL = "https://docs.1moreblock.com/custom-server-plugins/plotmarkers/";
    private static final List<String> SUBCOMMANDS = List.of("info", "version", "status", "debug");

    private final PlotMarkers plugin;

    public PlotMarkersCommand(PlotMarkers plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 0)
        {
            sendInfo(sender, label);
            return true;
        }

        if (args.length != 1)
        {
            sendUsage(sender, label);
            return true;
        }

        switch (args[0].toLowerCase(Locale.ROOT))
        {
            case "info" -> sendInfo(sender, label);
            case "version" -> sendVersion(sender);
            case "status" -> sendStatus(sender);
            case "debug" -> sendDebug(sender);
            default -> sendUsage(sender, label);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        if (args.length != 1)
        {
            return Collections.emptyList();
        }

        String prefix = args[0].toLowerCase(Locale.ROOT);
        return SUBCOMMANDS.stream()
                .filter(subcommand -> subcommand.startsWith(prefix))
                .toList();
    }

    private void sendInfo(CommandSender sender, String label)
    {
        sendVersionHeader(sender);
        sender.sendMessage(Component.text("Shows PlotSquared plot owners and borders on BlueMap.", NamedTextColor.WHITE));
        sender.sendMessage(Component.text("Target: Paper " + metadata().paperTarget()
                + " / Java " + metadata().javaTarget(), NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Use /" + label + " status for integration health or /"
                + label + " debug for release details.", NamedTextColor.WHITE));
        sender.sendMessage(Component.text("Docs: ", NamedTextColor.GRAY)
                .append(Component.text(DOCS_URL, NamedTextColor.GREEN)
                        .clickEvent(ClickEvent.openUrl(DOCS_URL))));
    }

    private void sendVersion(CommandSender sender)
    {
        sendVersionHeader(sender);
        sender.sendMessage(Component.text("Artifact: " + metadata().artifactFilename(), NamedTextColor.GRAY));
    }

    private void sendStatus(CommandSender sender)
    {
        sendVersionHeader(sender);
        sender.sendMessage(Component.text("Status: enabled", NamedTextColor.GREEN));
        sender.sendMessage(Component.text("Configured worlds: " + plugin.config.getWorlds().size(), NamedTextColor.WHITE));
        sender.sendMessage(Component.text("BlueMap: " + pluginStatus("BlueMap"), NamedTextColor.WHITE));
        sender.sendMessage(Component.text("PlotSquared: " + pluginStatus("PlotSquared"), NamedTextColor.WHITE));
        sender.sendMessage(Component.text("Multiverse-Core: " + pluginStatus("Multiverse-Core"), NamedTextColor.WHITE));
    }

    private void sendDebug(CommandSender sender)
    {
        sendVersionHeader(sender);
        sender.sendMessage(Component.text("Artifact: " + metadata().artifactFilename(), NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Compiled Paper API: io.papermc.paper:paper-api:"
                + metadata().paperApi(), NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Paper channel: " + metadata().paperChannel(), NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Java target: " + metadata().javaTarget()
                + "; runtime: " + System.getProperty("java.version"), NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Server: " + plugin.getServer().getVersion(), NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Configured worlds: " + plugin.config.getWorlds().size(), NamedTextColor.GRAY));
        sender.sendMessage(Component.text("BlueMap: " + pluginStatus("BlueMap")
                + "; PlotSquared: " + pluginStatus("PlotSquared")
                + "; Multiverse-Core: " + pluginStatus("Multiverse-Core"), NamedTextColor.GRAY));
    }

    private void sendVersionHeader(CommandSender sender)
    {
        sender.sendMessage(Component.text("PlotMarkers", NamedTextColor.AQUA)
                .append(Component.text(" v" + metadata().pluginVersion()
                        + " build " + metadata().releaseBuild(), NamedTextColor.GRAY)));
    }

    private void sendUsage(CommandSender sender, String label)
    {
        sender.sendMessage(Component.text("Usage: /" + label + " [info|version|status|debug]", NamedTextColor.RED));
    }

    private String pluginStatus(String pluginName)
    {
        return plugin.getServer().getPluginManager().isPluginEnabled(pluginName) ? "enabled" : "not enabled";
    }

    private ReleaseMetadata metadata()
    {
        return plugin.getReleaseMetadata();
    }
}
