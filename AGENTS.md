## Maintained release and test server

- Current release: 2.0.4 build 032, Paper 26.3 build 41 ALPHA, API coordinate
  `io.papermc.paper:paper-api:26.3.build.41-alpha`. The experimental channel is deliberate.
- Build with `./scripts/rebuild.sh`: JDK 25.0.4.1 through `JAVA_HOME` and `PATH`,
  Java toolchain and bytecode target 25. Reuse build 032 for verification retries.
- Run the ignored `servers/Paper-26.3/` instance on JDK 27 using
  `./scripts/start-test-server.sh`. PaperScript uses ALPHA and session
  `plotmarkers-paper-26-3-032`; local ports are 28763 (TCP game), 28764 (UDP query,
  disabled), 28765 (TCP RCON, disabled), and 28766 (TCP BlueMap). Check availability
  before startup. Keep DiscordSRV inactive.
- Preserve `servers/Paper-26.2/` and `servers/jdk-refresh-031/` as rollback material.
  Do not start the shared test server when deploying artifacts there.
- Follow [testing](docs/testing.md) and [the upgrade record](docs/releases/2.0.4-paper-26.3.md).
  Start Paper compatibility reviews at <https://docs.papermc.io/llms.txt>, then
  consult official docs and exact-version Javadocs. Keep exploit protections at
  their safe defaults and keep all server data and JARs out of Git.

## Agent skills

### Issue tracker

Issues and specs are tracked as GitHub issues. See `docs/agents/issue-tracker.md`.

### Domain docs

Domain documentation uses the single-context layout: `CONTEXT.md` and `docs/adr/` at the repository root. See `docs/agents/domain.md`.
