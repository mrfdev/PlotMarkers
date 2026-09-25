# Troubleshooting

## No Markers Appear

Check the server log for:

```text
[PlotMarkers] Created ...
```

If no markers are created:

- Confirm BlueMap is installed and enabled.
- Confirm PlotSquared is installed and enabled.
- Confirm the PlotSquared world exists and is loaded.
- Confirm BlueMap has a map for the plot world.
- Set `worlds.<world>.bluemap-map-id` if the BlueMap map ID differs from the PlotSquared world name.

## No BlueMap Definition For World

This warning means PlotMarkers found a configured PlotSquared world but could not find a matching BlueMap map.

Fixes:

- Create a BlueMap map for that world.
- Check the map ID in BlueMap's map config.
- Set `bluemap-map-id` in `plugins/PlotMarkers/config.yml`.
- Restart the server.

## PlotSquared Says A World Was Not Properly Loaded

This means PlotSquared has an area configuration for a world that was not loaded correctly. Do not ignore the warning if PlotMarkers creates zero markers or PlotSquared logs a scheduled-task exception.

On Paper 26.1 and newer, worlds live under `<level-name>/dimensions/<namespace>/<key>/`. Import the namespaced key into Multiverse-Core. For example:

```text
/mv import minecraft:builders normal --generator PlotSquared
```

After restarting, verify all of the following:

- `/mv info minecraft:builders` reports `World Name: builders` and `Generator: PlotSquared`.
- The log contains `Detected world load for 'builders'`.
- The log contains a load line for `minecraft:builders`.
- PlotMarkers creates nonzero POI and shape markers.
- PlotSquared does not log `PLOT AREA CANNOT BE NULL` during shutdown.

## BlueMap Fallback Dimension Warning

BlueMap may warn that world data does not contain information for a custom dimension such as `minecraft:builders` and then use fallback dimension metadata. Configure the map with the primary save folder and namespaced dimension, for example:

```hocon
world: "spawn"
dimension: "minecraft:builders"
```

If BlueMap loads the expected map and PlotMarkers creates nonzero markers, the fallback warning alone is not a PlotMarkers failure.

## Marker Height Is Wrong

Use:

```yaml
worlds:
  <world>:
    override-y: true
    y: 63
```

Set `y` to one block above the plot surface. If you want PlotMarkers to use the average plot height, set `override-y` to `false`.

## Custom Icon Does Not Show

- Confirm the icon file is inside the PlotMarkers plugin folder.
- Confirm `custom-icon` matches the file name.
- Confirm BlueMap loaded successfully.
- Check the log for an icon-copy warning.

## Config Changes Did Not Apply

Restart the server. PlotMarkers does not provide a reload command.

## Hard Compatibility Errors

If the log shows `NoSuchMethodError`, `NoClassDefFoundError`, `ClassNotFoundException`, `UnsupportedClassVersionError`, or an enable exception involving PlotMarkers, capture the full startup log and the exact Paper, BlueMap, PlotSquared, Java, and PlotMarkers versions.

Run `/plotmarkers debug` to capture the generated plugin version/build, exact compiled Paper API, Java target/runtime, server version, and integration state.
