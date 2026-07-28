# Commands

PlotMarkers exposes one command with four diagnostic subcommands.

| Command | Description | Permission |
| --- | --- | --- |
| `/plotmarkers info` | Shows the plugin name, short description, useful starting command, installed version, and canonical docs URL. | None |
| `/plotmarkers version` | Shows the semantic version, shared build number, and release artifact filename. | None |
| `/plotmarkers status` | Shows enable state, configured-world count, and BlueMap, PlotSquared, and Multiverse-Core integration state. | None |
| `/plotmarkers debug` | Shows generated release metadata, compiled Paper API, Paper channel, Java target/runtime, server version, and integrations. | None |

Running `/plotmarkers` without arguments also shows the info output.

Unknown subcommands return:

```text
Usage: /plotmarkers [info|version|status|debug]
```

Plot claiming and plot management commands are provided by PlotSquared, not PlotMarkers.
