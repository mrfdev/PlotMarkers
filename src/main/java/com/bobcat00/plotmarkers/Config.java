// PlotMarkers - Add plot markers to BlueMap map
// Copyright 2024 Bobcat00
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package com.bobcat00.plotmarkers;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.configuration.ConfigurationSection;

import com.plotsquared.core.plot.PlotArea;

public class Config
{
    private PlotMarkers plugin;
    
    private boolean defaultOverrideY = true;
    private int defaultY = 63;
    
    // Constructor
    
    public Config(PlotMarkers plugin)
    {
        this.plugin = plugin;
        ensureBaseConfig();
        updatePlotWorlds();
    }
    
    // -------------------------------------------------------------------------
    
    private void ensureBaseConfig()
    {
        boolean changed = false;
        
        // Check if config.yml exists
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists())
        {
            plugin.getConfig().set("date-format", "MM/dd/yy");
            plugin.getConfig().setComments("date-format",
                Arrays.asList("The date format in the icon pop-ups. See Java's SimpleDateFormat.",
                              "Examples include MM/dd/yy, dd/MM/yy, and yy/MM/dd."));
            changed = true;
        }
        else
        {
            changed |= setDefault("date-format", "MM/dd/yy");
        }
        
        if (changed)
        {
            plugin.saveConfig();
        }
    }
    
    // -------------------------------------------------------------------------
    
    public boolean updatePlotWorlds()
    {
        boolean changed = false;
        
        // Get list of plot worlds
        PlotArea[] plotAreas = plugin.psAPI.getPlotSquared().getPlotAreaManager().getAllPlotAreas();
        Set<String> worlds = new TreeSet<String>();
        for (PlotArea area : plotAreas)
        {
            worlds.add(area.getWorldName());
        }
        
        // Write any missing world configs without overwriting existing settings
        boolean firstWorld = plugin.getConfig().getConfigurationSection("worlds") == null;
        for (String world : worlds)
        {
            changed |= setDefault("worlds." + world + ".override-y", defaultOverrideY);
            changed |= setDefault("worlds." + world + ".y", defaultY);
            changed |= setDefault("worlds." + world + ".bluemap-map-id", "");
            changed |= setDefault("worlds." + world + ".custom-icon", "");
            changed |= setDefault("worlds." + world + ".custom-icon-anchor-x", 0);
            changed |= setDefault("worlds." + world + ".custom-icon-anchor-y", 0);
            changed |= setDefault("worlds." + world + ".fill-color", "#3388ff");
            changed |= setDefault("worlds." + world + ".fill-opacity", 0.1f);
            changed |= setDefault("worlds." + world + ".line-color", "#3388ff");
            changed |= setDefault("worlds." + world + ".line-opacity", 1.0f);
            changed |= setDefault("worlds." + world + ".line-width", 5);
            
            if (firstWorld)
            {
                plugin.getConfig().setComments("worlds",
                        Arrays.asList("Markers will be created for each world listed below."));
                plugin.getConfig().setComments("worlds." + world + ".override-y",
                    Arrays.asList("override-y causes the marker to be placed at the specified y coordinate.",
                                  "Normally leave this true and set y to one above the ground level of your plots.",
                                  "If set to false, the average height (y value) of each plot will be used."));
                plugin.getConfig().setComments("worlds." + world + ".bluemap-map-id",
                    Arrays.asList("Optional BlueMap map id if it differs from the PlotSquared world name.",
                                  "This is normally the BlueMap map config filename without .conf."));
                plugin.getConfig().setComments("worlds." + world + ".custom-icon",
                    Arrays.asList("Specify a custom icon in the plugin folder if you don't want the default icon.",
                                  "The anchor is which pixel on the marker-image will be placed at the marker's position."));
                plugin.getConfig().setComments("worlds." + world + ".fill-color",
                    Arrays.asList("Set the color and opacity for the fill and line areas, and the line width.",
                                  "Color is '#rrggbb'. Opacity is 0.0 - 1.0"));
                firstWorld = false;
            }
        }
        
        if (changed)
        {
            plugin.saveConfig();
        }
        return changed;
    }
    
    // -------------------------------------------------------------------------
    
    private boolean setDefault(String path, Object value)
    {
        if (plugin.getConfig().isSet(path))
        {
            return false;
        }
        plugin.getConfig().set(path, value);
        return true;
    }
    
    // -------------------------------------------------------------------------
    
    // Get the date format
    
    public String getDateFormat()
    {
        return plugin.getConfig().getString("date-format", "MM/dd/yy");
    }
    
    // -------------------------------------------------------------------------
    
    // Get list of worlds from config file
    
    public Set<String> getWorlds()
    {
        ConfigurationSection confSect = plugin.getConfig().getConfigurationSection("worlds");
        if (confSect != null)
        {
            return confSect.getKeys(false);
        }
        return Collections.emptySet();
    }
    
    // -------------------------------------------------------------------------
    
    // Get y coordinate for this world. This returns either the defined value or
    // null, depending on override-y.
    
    public Integer getY(String world)
    {
        if (plugin.getConfig().getBoolean("worlds." + world + ".override-y", defaultOverrideY))
        {
            return plugin.getConfig().getInt("worlds." + world + ".y", defaultY);
        }
        else
        {
            return null;
        }
    }
    
    // -------------------------------------------------------------------------
    
    // Get the BlueMap map id for this world, if explicitly configured.
    
    public String getBlueMapMapId(String world)
    {
        return plugin.getConfig().getString("worlds." + world + ".bluemap-map-id", "");
    }
    
    // -------------------------------------------------------------------------
    
    // Get the custom icon string for this world. May be an empty String.
    
    public String getCustomIcon(String world)
    {
        return plugin.getConfig().getString("worlds." + world + ".custom-icon", "");
    }
    
    // -------------------------------------------------------------------------
    
    // Get the custom icon string for this world. May be an empty String.
    
    public int getCustomIconAnchorX(String world)
    {
        return plugin.getConfig().getInt("worlds." + world + ".custom-icon-anchor-x", 0);
    }
    
    // -------------------------------------------------------------------------
    
    // Get the custom icon string for this world. May be an empty String.
    
    public int getCustomIconAnchorY(String world)
    {
        return plugin.getConfig().getInt("worlds." + world + ".custom-icon-anchor-y", 0);
    }
    
    // -------------------------------------------------------------------------
    
    // Get the fill color for this world
    
    public int getFillColor(String world)
    {
        int color = 0x3388ff;
        String colorStr = plugin.getConfig().getString("worlds." + world + ".fill-color", "#3388ff");
        try
        {
            if (colorStr.charAt(0) != '#')
            {
                throw new NumberFormatException();
            }
            color = Integer.parseUnsignedInt(colorStr.substring(1), 16);
        }
        catch (NumberFormatException e)
        {
            plugin.getLogger().warning("Invalid color for " + world + ".fill-color: " + e.getMessage());
        }
        return color;
    }
    
    // -------------------------------------------------------------------------
    
    // Get the fill opacity for this world
    
    public float getFillOpacity(String world)
    {
        return (float)plugin.getConfig().getDouble("worlds." + world + ".fill-opacity", 0.1);
    }
    
    // -------------------------------------------------------------------------
    
    // Get the line color for this world
    
    public int getLineColor(String world)
    {
        int color = 0x3388ff;
        String colorStr = plugin.getConfig().getString("worlds." + world + ".line-color", "#3388ff");
        try
        {
            if (colorStr.charAt(0) != '#')
            {
                throw new NumberFormatException();
            }
            color = Integer.parseUnsignedInt(colorStr.substring(1), 16);
        }
        catch (NumberFormatException e)
        {
            plugin.getLogger().warning("Invalid color for " + world + ".line-color: " + e.getMessage());
        }
        return color;
    }
    
    // -------------------------------------------------------------------------
    
    // Get the line opacity for this world
    
    public float getLineOpacity(String world)
    {
        return (float)plugin.getConfig().getDouble("worlds." + world + ".line-opacity", 1.0);
    }
    
    // -------------------------------------------------------------------------
    
    // Get the line width for this world
    
    public int getLineWidth(String world)
    {
        return plugin.getConfig().getInt("worlds." + world + ".line-width", 5);
    }
    
    // -------------------------------------------------------------------------
    
    // Reload config file
    
    public void reloadConfig()
    {
        plugin.reloadConfig();
    }

}
