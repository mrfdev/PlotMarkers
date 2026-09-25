# Integrations

## BlueMap

BlueMap is required. PlotMarkers creates BlueMap marker sets and stores marker data in BlueMap's runtime marker API.

Marker sets:

- `Plots`
- `Shapes`

The BlueMap map or world must match the PlotSquared world, or `bluemap-map-id` must be set in PlotMarkers config.

## PlotSquared

PlotSquared is required. PlotMarkers reads PlotSquared plot areas, plots, owners, plot IDs, and plot size information.

PlotMarkers listens for PlotSquared events including:

- Plot claim
- Plot owner change
- Plot merge
- Plot unlink
- Plot delete

## Multiverse-Core

Multiverse-Core is a soft dependency. It is useful on multi-world servers because world managers can ensure plot worlds are loaded before plugins inspect them.

PlotMarkers does not call the Multiverse-Core API directly.

On Paper 26.1 and newer, Multiverse must persist custom worlds by their namespaced dimension key. A world stored at `<level-name>/dimensions/minecraft/builders/` should be imported as:

```text
/mv import minecraft:builders normal --generator PlotSquared
```

The resulting `plugins/Multiverse-Core/worlds.yml` entry must be named `minecraft:builders`, while PlotSquared and Bukkit continue to expose the Bukkit world name as `builders`.

## WorldEdit and WorldGuard

WorldEdit and WorldGuard are common dependencies in PlotSquared server stacks, but PlotMarkers does not call their APIs directly.

## PlaceholderAPI

PlotMarkers does not register PlaceholderAPI placeholders.

## bStats

PlotMarkers uses bStats metrics if metrics are enabled in `plugins/bStats/config.yml`.
