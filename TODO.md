# TODO — Manual Tasks

## Sound Files

Place `.wav` files in this directory:

```
desktop-client/src/main/resources/sounds/
```

Required files:

| File | Description |
|------|-------------|
| `click.wav` | Button click |
| `win.wav` | Win a round |
| `lose.wav` | Lose a round |
| `achievement.wav` | Achievement unlock |

> **Note:** The app safely skips missing files with a log warning — won't crash if absent.

## Docker / Backend

```bash
docker compose -f docker/docker-compose.yml up -d    # Start PostgreSQL
./gradlew :backend-service:bootRun                    # Start backend
```

## Build & Run Desktop

```bash
./gradlew :desktop-client:run                         # Launch app
```

## Verify Full Build

```bash
./gradlew clean build                                 # All modules + tests
```
