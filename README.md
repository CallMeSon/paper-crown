# Paper Crown

A local-first desktop roguelike game based on Rock-Paper-Scissors (Batu, Gunting, Kertas). Fight a purely random bot opponent through multiple runs, collect buffs, survive HP-based progression, unlock achievements, and build persistent statistics.

## Architecture

```
JavaFX Desktop  ──REST──>  Spring Boot Backend  ──>  PostgreSQL
    (MVVM)                     (Service Layer)         (via Flyway)
```

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Desktop Client | Java 21, JavaFX 23, Ikonli, JFreeChart |
| Backend Service | Spring Boot 3.4.3, JPA/Hibernate |
| Database | PostgreSQL 16 (via Docker) |
| Shared Contracts | Multi-module Gradle project |
| Testing | JUnit 5, Mockito |

## Quick Start

### 1. Start Database

```bash
docker compose -f docker/docker-compose.yml up -d
```

### 2. Start Backend

```bash
./gradlew :backend-service:bootRun
```

The backend starts on `http://localhost:8080`. Flyway migrations create all tables and seed default data (buffs, achievements, settings).

### 3. Launch Desktop Client

```bash
./gradlew :desktop-client:run
```

## Game Rules

- You choose **Rock**, **Paper**, or **Scissors** each round
- Bot chooses randomly
- **Win** → survive the round
- **Loss** → lose 1 HP
- **Draw** → no HP loss
- **0 HP** → run ends

### Buffs

Every few rounds, choose from 3 random buffs:

| Type | Examples |
|------|---------|
| Survival | +1 Max HP, Heal 1 HP, Shield |
| Scoring | Double reward, Bonus streak points |
| Utility | Reroll token, Draw counts as win, Ignore loss |

### Achievements

11 achievements across 5 criteria types — milestones auto-unlock as you play.

## Project Structure

```
├── desktop-client/        # JavaFX desktop application
│   └── src/main/java/.../
│       ├── component/     # Reusable UI components
│       ├── service/       # Backend HTTP client
│       ├── util/          # Audio manager
│       ├── view/          # JavaFX views (MVVM)
│       ├── viewmodel/     # ViewModel layer
│       └── PaperCrownApp.java
├── backend-service/       # Spring Boot REST API
│   └── src/main/java/.../
│       ├── controller/    # REST controllers
│       ├── service/       # Business logic
│       ├── repository/    # JPA repositories
│       ├── entity/        # JPA entities
│       └── config/        # CORS, exception handling
├── shared/                # DTOs and enums
├── docker/                # Docker Compose for PostgreSQL
└── infra/                 # Setup scripts
```

## Build & Test

```bash
./gradlew build              # Compile all modules + run tests
./gradlew :desktop-client:run  # Launch desktop client
./gradlew :backend-service:bootRun  # Start backend
```

## Settings

- Fullscreen, volume, sound effects, and animations are configurable in-app
- Settings persist across restarts via the backend API
