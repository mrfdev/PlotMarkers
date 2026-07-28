// PlotMarkers - Add plot markers to BlueMap map
// Copyright 2026 Bobcat00
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

package com.bobcat00.plotmarkers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public record ReleaseMetadata(
        String pluginVersion,
        String releaseBuild,
        String javaTarget,
        String paperTarget,
        String paperApi,
        String paperChannel,
        String artifactFilename)
{
    private static final String RESOURCE_NAME = "plotmarkers-release.properties";

    public static ReleaseMetadata load(PlotMarkers plugin)
    {
        Properties properties = new Properties();
        try (InputStream input = plugin.getResource(RESOURCE_NAME))
        {
            if (input == null)
            {
                throw new IllegalStateException("Missing generated release metadata: " + RESOURCE_NAME);
            }
            properties.load(input);
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Unable to load generated release metadata", e);
        }

        return new ReleaseMetadata(
                required(properties, "plugin.version"),
                required(properties, "release.build"),
                required(properties, "java.target"),
                required(properties, "paper.target"),
                required(properties, "paper.api"),
                required(properties, "paper.channel"),
                required(properties, "artifact.filename"));
    }

    private static String required(Properties properties, String key)
    {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank())
        {
            throw new IllegalStateException("Generated release metadata is missing " + key);
        }
        return value;
    }
}
