# PlotMarkers Player Guide

## Introduction

PlotMarkers shows plot information on BlueMap. When you open the server map, you can see plot markers and plot outlines for supported PlotSquared worlds.

## How Players Use It

Open BlueMap and look for the marker sets named `Plots` and `Shapes`.

- `Plots` shows clickable markers near the center of plots.
- `Shapes` shows plot borders and merged plot outlines.

Clicking a marker can show the plot owner, plot ID, and player date information.

## Available Features

- See which plots are claimed.
- See plot owner names where known.
- See plot IDs in `x;z` format.
- See plot border outlines, including merged plots.
- See marker updates after normal PlotSquared changes such as claiming, deleting, merging, unlinking, and ownership changes.

## Quick Start

1. Join the server and use the normal PlotSquared commands to find or manage plots.
2. Open BlueMap in your browser.
3. Enable the `Plots` and `Shapes` marker sets if they are hidden.
4. Use `/plotmarkers info` in game for the documentation link.

## Commands

| Command | Description |
| --- | --- |
| `/plotmarkers info` | Shows what PlotMarkers does, the installed version, and a clickable documentation link. |

## Permissions or Rank Requirements

PlotMarkers does not define custom permissions. The map markers are generated automatically from PlotSquared data.

Access to claim, merge, delete, or manage plots is controlled by PlotSquared and the server's normal rank permissions.

## Rewards, Costs, Limits, and Cooldowns

PlotMarkers does not add rewards, costs, cooldowns, or repeatable reward claims. It only displays plot information on BlueMap.

## Placeholders

PlotMarkers does not provide PlaceholderAPI placeholders.

## Important Notes

- Marker data comes from PlotSquared.
- The web map must be available through BlueMap.
- Marker changes may not be instant during heavy startup or world-loading work.
- If a plot owner name is not known to the server, the marker may show a UUID instead.

## Related Features

- PlotSquared handles plot claiming, merging, deletion, and ownership.
- BlueMap provides the web map and marker display.

## Technical Documentation

Technical documentation is available in the [project README](../README.md).
