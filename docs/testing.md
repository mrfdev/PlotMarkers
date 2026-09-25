# Build and runtime verification

## Toolchain and release

Build with JDK 25.0.4.1 at `/Library/Java/JavaVirtualMachines/jdk-25.0.4.1.jdk/Contents/Home`.
Keep the Gradle toolchain and `--release` at 25 (class major 69). Run the maintained
Paper 26.3 test instance with `/Library/Java/JavaVirtualMachines/jdk-27.jdk/Contents/Home`.
The scripts select both `JAVA_HOME` and `PATH`; automatic toolchain downloads stay disabled.

Release properties in `gradle.properties` reserve version 2.0.4 and build 032.
Increment each once for this release; reuse 032 after failed or repeated checks.
The exact API is `io.papermc.paper:paper-api:26.3.build.41-alpha`. The ALPHA channel
is deliberate. Never fall back to Paper 26.2 when a 26.3 build or test fails.

## Full rebuild

```bash
./scripts/rebuild.sh
```

This runs `./gradlew --no-daemon clean build docsCheck` with the complete `check`
lifecycle. Verification covers generated metadata, documentation and launcher
drift, both JAR descriptors, the mappings namespace, and every packaged class.
Plugin classes must target Java 25; bundled library classes may target older Java
versions but must not require newer or preview bytecode. The repository has no
unit-test sources, so `test NO-SOURCE` is expected and is not a unit-test pass.

The shaded JAR is the deployment artifact. The thin JAR is also built and checked,
but requires externally supplied BMUtils and bStats and is never installed alongside
the shaded JAR.

## Local Paper smoke tests

Keep `servers/Paper-26.2/` and `servers/jdk-refresh-031/` intact as rollback material.
The independent clone is `servers/Paper-26.3/`. All server data, downloads, artifacts,
and raw evidence stay under ignored `servers/`.

The new instance uses session `plotmarkers-paper-26-3-032` and loopback ports 28763
(TCP game), 28764 (UDP query, disabled), 28765 (TCP RCON, disabled), and 28766
(TCP BlueMap). Check availability before each run. DiscordSRV is inactive. Keep
Paper's duplication, unsafe portal, tripwire, and oversized-component protections
at their safe defaults.

The copied `1MB-minecraft.sh` selects Paper 26.3 and Java 27. PaperScript uses ALPHA
for both `default_channel` and `check_latest_channel_only`; copied 26.2 state,
cache, download, and launcher records were archived outside the active instance.
Stage only the reviewed exact build:

```bash
cd servers/Paper-26.3
./paperscript.sh --yes --no-metadata-cache download --version 26.3 --build 41 --channel ALPHA
./paperscript.sh verify
```

Install the shaded build as the only PlotMarkers JAR, then start from the repo root:

```bash
./scripts/start-test-server.sh
```

This selects Java 27 and checks the pinned server JAR's SHA-256 before startup.
It does not invoke the shared server. After Paper readiness and marker creation,
run these console commands:

```text
version
plugins
version BlueMap
version PlotSquared
version WorldEdit
version PlotMarkers
plotmarkers
plotmarkers info
plotmarkers version
plotmarkers status
plotmarkers debug
plotmarkers invalid
plotmarkers info extra
mv info minecraft:builders
bluemap
bluemap reload
stop
```

Wait for both marker-creation messages and reload completion before `stop`.
Verify diagnostics against release properties, enabled integrations, usage
responses, the `builders` world generator, and the HTTP marker data at
`http://127.0.0.1:28766/maps/builders/live/markers.json`. This populated fixture
must produce 361 POIs and 114 shapes, unchanged by reload. Require clean plugin
disable, saved worlds, exit 0, and no linkage/class-version or scheduled-task errors.

For the thin JAR, use the retained test-only `PlotMarkersTestLibraries` fixture
from `servers/jdk-refresh-031/thin-libraries/`. It supplies BMUtils 5.0.1 and
bStats Bukkit/Base 3.1.0. Only that test process uses
`JAVA_TOOL_OPTIONS=-Dbstats.relocatecheck=false`, because the fixture intentionally
provides unrelocated dependencies. Restore the shaded JAR and remove the fixture
afterward. Normal shaded operation keeps bStats relocation checking enabled.

The ignored `servers/upgrade-032/smoke.py` automates the diagnostics, HTTP checks,
reload and shutdown for `shaded` or `thin`, after installing the selected JAR.
See [the release record](releases/2.0.4-paper-26.3.md) for exact results and remaining
manual gameplay checks. The [build 031 record](releases/2.0.3-build-031-tests.md)
contains the historical Paper 26.2 procedure and evidence.
