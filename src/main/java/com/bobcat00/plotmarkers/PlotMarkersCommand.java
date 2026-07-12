// PlotMarkers - Add plot markers to BlueMap map
// Copyright 2026 Bobcat00
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

package com.bobcat00.plotmarkers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public final class PlotMarkersCommand implements CommandExecutor
{
    private static final String DOCS_URL = "https://docs.1moreblock.com/custom-server-plugins/plotmarkers/";
    
    private final PlotMarkers plugin;
    
    public PlotMarkersCommand(PlotMarkers plugin)
    {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("info")))
        {
            sendInfo(sender, label);
            return true;
        }
        
        sender.sendMessage(Component.text("Usage: /" + label + " info", NamedTextColor.RED));
        return true;
    }
    
    private void sendInfo(CommandSender sender, String label)
    {
        sender.sendMessage(Component.text("PlotMarkers", NamedTextColor.AQUA)
                .append(Component.text(" v" + plugin.getPluginMeta().getVersion(), NamedTextColor.GRAY)));
        sender.sendMessage(Component.text("Shows PlotSquared plot owners and borders on BlueMap.", NamedTextColor.WHITE));
        sender.sendMessage(Component.text("Start with /" + label + " info, then open BlueMap to view plot markers.", NamedTextColor.WHITE));
        sender.sendMessage(Component.text("Docs: ", NamedTextColor.GRAY)
                .append(Component.text(DOCS_URL, NamedTextColor.GREEN)
                        .clickEvent(ClickEvent.openUrl(DOCS_URL))));
    }
}
