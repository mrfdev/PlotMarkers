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

In small test servers, this can happen when a PlotSquared world is configured but no world manager such as Multiverse-Core preloads it. PlotSquared may then load the world itself.

If PlotMarkers creates markers afterward, this warning is usually test-server noise. On a live server, confirm your world manager and PlotSquared world configuration are correct.

## BlueMap Fallback Dimension Warning

BlueMap may warn that world data does not contain information for a dimension such as `minecraft:plotsq`. If BlueMap still loads the map and PlotMarkers creates markers, this is not a PlotMarkers failure.

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
