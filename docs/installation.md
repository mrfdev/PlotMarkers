# Installation

## Requirements

- Java 25
- Paper 26.2 build 84 STABLE
- BlueMap
- PlotSquared
- Optional: Multiverse-Core for world management in multi-world server setups

The plugin compiles against `io.papermc.paper:paper-api:26.2.build.84-stable` and targets Java 25 bytecode.

## Build From Source

```bash
./gradlew build
```

Install the shaded jar:

```text
build/libs/1MB-PlotMarkers-v2.0.3-030-j25-26.2.jar
```

Do not install the `thin` jar unless you are intentionally managing the shaded dependencies yourself.

## Server Install

1. Stop the server.
2. Install or update BlueMap and PlotSquared.
3. Copy `1MB-PlotMarkers-v2.0.3-030-j25-26.2.jar` into `plugins/`.
4. Remove older PlotMarkers jars from the top-level `plugins/` folder.
5. Start the server.
6. Confirm the log contains marker creation lines such as:

```text
[PlotMarkers] Created 1 POI marker for plotsq.
[PlotMarkers] Created 1 shape marker for plotsq.
```

7. Run `/plotmarkers status` and `/plotmarkers debug` to confirm integrations and compiled release metadata.
8. Open BlueMap and check the `Plots` and `Shapes` marker sets.

## Updating

1. Stop the server.
2. Back up `plugins/PlotMarkers/config.yml` if you have custom settings.
3. Replace the old PlotMarkers jar with the new jar.
4. Start the server.
5. Review the startup log for PlotMarkers warnings.

PlotMarkers adds missing config defaults without overwriting existing per-world settings.
