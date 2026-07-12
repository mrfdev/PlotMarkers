# Configuration

PlotMarkers creates `plugins/PlotMarkers/config.yml` at startup. It detects PlotSquared plot worlds and adds missing per-world defaults without overwriting existing settings.

Configuration is read when BlueMap enables and PlotMarkers sets up marker sets. Restart the server after changing PlotMarkers settings. There is no reload command.

## Top-Level Settings

| Key | Default | Description |
| --- | --- | --- |
| `date-format` | `MM/dd/yy` | Java `SimpleDateFormat` pattern used for first-played and last-played dates in marker details. |

## Per-World Settings

PlotMarkers writes settings under:

```yaml
worlds:
  <world-name>:
```

| Key | Default | Description |
| --- | --- | --- |
| `override-y` | `true` | Uses the configured `y` value for marker height. If `false`, PlotMarkers uses the average plot height. |
| `y` | `63` | Marker height when `override-y` is enabled. Normally set this to one block above the plot surface. |
| `bluemap-map-id` | empty | Optional BlueMap map ID when the BlueMap map ID differs from the PlotSquared world name. Usually the BlueMap map config file name without `.conf`. |
| `custom-icon` | empty | Optional icon file in the PlotMarkers plugin folder for POI markers. |
| `custom-icon-anchor-x` | `0` | X pixel anchor for the custom icon. |
| `custom-icon-anchor-y` | `0` | Y pixel anchor for the custom icon. |
| `fill-color` | `#3388ff` | Shape marker fill color in `#rrggbb` format. |
| `fill-opacity` | `0.1` | Shape marker fill opacity from `0.0` to `1.0`. |
| `line-color` | `#3388ff` | Shape marker line color in `#rrggbb` format. |
| `line-opacity` | `1.0` | Shape marker line opacity from `0.0` to `1.0`. |
| `line-width` | `5` | Shape marker line width. |

## Marker Sets

For each configured world with a matching BlueMap map, PlotMarkers creates:

- `Plots`: POI markers for plots.
- `Shapes`: shape markers for plot borders and merged plot outlines.

## Custom Icons

Set `custom-icon` to a file name inside the PlotMarkers plugin folder. PlotMarkers copies the icon to BlueMap asset storage during marker setup and uses the configured anchor values for POI markers.

## BlueMap Map ID Matching

PlotMarkers first tries to find a BlueMap world matching the PlotSquared world name. It also supports Paper 26 layouts where Bukkit worlds may appear as dimensions under the primary save folder. If needed, set `bluemap-map-id` to the BlueMap map ID for that PlotSquared world.

## Persistent Data

PlotMarkers does not store a database. Marker content is rebuilt from PlotSquared data at startup and updated through PlotSquared events while the server is running.
