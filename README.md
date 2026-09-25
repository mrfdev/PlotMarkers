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
- Provides `/plotmarkers info`, `version`, `status`, and `debug` diagnostics generated from release metadata.

## Compatibility

- Build JDK: 25.0.4.1
- Java bytecode target: 25; tested server runtime: Java 27
- Paper API target: 26.3
- Compiled Paper API: `io.papermc.paper:paper-api:26.3.build.41-alpha`
- PlotSquared API target: `com.intellectualsites.plotsquared:plotsquared-core:7.6.0`
- Release: `2.0.4` build `032`
- Required plugins: BlueMap and PlotSquared
- Soft dependency: Multiverse-Core

PlotMarkers targets Paper 26.3 build 41 ALPHA while retaining Java 25 bytecode. This experimental Paper build is an explicit compatibility target; see the [verification record](docs/releases/2.0.4-paper-26.3.md) for runtime results and remaining manual checks.

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

Use JDK 25.0.4.1 through `JAVA_HOME` and `PATH`, then run the Gradle wrapper:

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.0.4.1.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
./gradlew --no-daemon clean build docsCheck
```

The shaded plugin jar is written to:

```text
build/libs/1MB-PlotMarkers-v2.0.4-032-j25-26.3.jar
```

Release version, build number, Java target, Paper target, API build, channel, and artifact naming are defined in `gradle.properties`. The `check` task fails when generated metadata or documentation drifts from those values.

`./scripts/rebuild.sh` runs this full rebuild with the pinned JDK. Increment `releaseBuild` for a new release build; keep the same number when repeating verification of that build. The Paper upgrade reserves version `2.0.4` and build `032` once; failed or repeated checks reuse that number.

See [build and runtime verification](docs/testing.md) for the Java 27 smoke-test procedure and results. The Paper 26.2 rollback snapshot is tagged `v2.0.3-paper-26.2`.

Gradle also creates a thin jar with the `thin` classifier; install the unclassified shaded jar on the server.

## Installation

1. Stop the server.
2. Install BlueMap and PlotSquared.
3. Put `1MB-PlotMarkers-v2.0.4-032-j25-26.3.jar` in the server `plugins/` folder.
4. Start the server once so PlotMarkers can detect PlotSquared plot worlds and generate `plugins/PlotMarkers/config.yml`.
5. Configure BlueMap maps and any per-world PlotMarkers settings.
6. Restart the server so markers are rebuilt with the final configuration.

## Persistent Data

PlotMarkers stores only its Bukkit configuration file and any custom icon files placed in the plugin folder. It does not maintain a database. BlueMap markers are rebuilt from PlotSquared data at startup and updated from PlotSquared events while the server is running.

## License

PlotMarkers is distributed under the GNU General Public License, version 3 or later. See [license.txt](src/main/resources/license.txt).
