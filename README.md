# PlotMarkers

PlotMarkers is a standalone 1MoreBlock plugin that adds PlotSquared plot markers to BlueMap. It creates clickable point-of-interest markers and plot-border shape markers so players can see plot ownership and plot boundaries on the web map.

The plugin is based on PlotMarkers 2.x and is maintained here for Paper 26 compatibility testing.

## Features

- Creates BlueMap marker sets for configured PlotSquared worlds.
- Adds a POI marker for each plot.
- Adds a shape marker for each base plot, including merged plot outlines.
- Shows owner name, plot ID, first played date, and last played date in marker details.
- Updates markers when plots are claimed, deleted, merged, unlinked, or transferred.
- Supports per-world marker height, colors, opacity, line width, BlueMap map ID override, and custom POI icons.
- Provides `/plotmarkers info` with a canonical documentation link.

## Compatibility

- Java: 25
- Paper API target: 26.1.2
- Verified test targets: Paper 26.1.2 and Paper 26.2
- Required plugins: BlueMap and PlotSquared
- Soft dependency: Multiverse-Core

The same 26.1.2-targeted PlotMarkers jar has been tested successfully on Paper 26.2. Do not retarget the project to 26.2 unless a real compatibility issue appears.

## Documentation

- [Player guide](docs/player-guide.md)
- [Commands](docs/commands.md)
- [Configuration](docs/configuration.md)
- [Installation](docs/installation.md)
- [Integrations](docs/integrations.md)
- [Troubleshooting](docs/troubleshooting.md)

Canonical public URL:

https://docs.1moreblock.com/custom-server-plugins/plotmarkers/

## Build

Use the Gradle wrapper:

```bash
./gradlew build
```

The shaded plugin jar is written to:

```text
build/libs/PlotMarkers-2.01-SNAPSHOT.jar
```

Gradle also creates a thin jar with the `thin` classifier; install the unclassified shaded jar on the server.

## Installation

1. Stop the server.
2. Install BlueMap and PlotSquared.
3. Put `PlotMarkers-2.01-SNAPSHOT.jar` in the server `plugins/` folder.
4. Start the server once so PlotMarkers can detect PlotSquared plot worlds and generate `plugins/PlotMarkers/config.yml`.
5. Configure BlueMap maps and any per-world PlotMarkers settings.
6. Restart the server so markers are rebuilt with the final configuration.

## Persistent Data

PlotMarkers stores only its Bukkit configuration file and any custom icon files placed in the plugin folder. It does not maintain a database. BlueMap markers are rebuilt from PlotSquared data at startup and updated from PlotSquared events while the server is running.

## License

PlotMarkers is distributed under the GNU General Public License, version 3 or later. See [license.txt](src/main/resources/license.txt).
