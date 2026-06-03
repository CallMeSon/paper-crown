# PRODUCT.md

## Product Overview

Build a local-first desktop roguelike game based on Rock-Paper-Scissors (Batu, Gunting, Kertas).

The game is PvE only. The player fights against a purely random bot opponent through multiple runs while collecting buffs, surviving through HP-based progression, unlocking achievements, and building persistent statistics.

The application consists of:

- [x] JavaFX desktop client
- [x] Spring Boot backend service
- [x] PostgreSQL database
- [x] REST communication between desktop and backend
- [x] Dockerized local development environment

This project prioritizes:

- [x] maintainability
- [x] modular architecture
- [x] OOP principles
- [x] testability
- [ ] polish & quality assurance

The application language must be English only.

- [x] English only

---

# Core Gameplay

## Rules

Supported moves:

- [x] Rock
- [x] Paper
- [x] Scissors

Rules:

- [x] Rock beats Scissors
- [x] Scissors beats Paper
- [x] Paper beats Rock
- [x] Draw remains draw

Track:

- [x] Wins
- [x] Draws
- [x] Losses

---

## Game Loop

A run consists of:

1. [x] Start run
2. [x] Enter round
3. [x] Choose move
4. [x] Bot chooses random move
5. [x] Resolve round
6. [x] Apply damage/result
7. [x] Receive reward/buff periodically
8. [x] Continue until HP reaches zero
9. [ ] Save run results (backend persists, frontend not wired)
10. [ ] Return to dashboard (not wired)

---

## HP System

Player starts every run with:

```text
3 HP
```

- [x] Loss = lose 1 HP
- [x] Draw = no HP loss
- [x] Win = survive round
- [x] 0 HP = run ends

---

## Buff / Modifier System

Buff system must remain intentionally simple.

- [x] Buffs are temporary and only apply within the current run
- [x] Player periodically receives a choice between multiple buffs

Initial buff pool:

### Survival Buffs

- [x] +1 Max HP
- [x] Heal 1 HP
- [x] Gain shield for next loss

### Scoring Buffs

- [x] Double reward for next win
- [x] Bonus points on streaks

### Utility Buffs

- [x] One reroll token
- [x] Draw counts as win once
- [x] Ignore one future loss

Rules:

- [x] Buff effects must be encapsulated (strategy pattern)
- [x] Avoid giant conditional statements
- [ ] Design for extensibility (partial — more buff types need effect implementations)

Use OOP patterns where appropriate.

- [x] Strategy-based effect resolution

---

## Progression

Persist between runs:

- [x] statistics
- [x] achievements
- [x] unlocked milestones
- [x] completed runs
- [x] historical data

- [x] Do NOT persist active buffs between runs

---

# Achievement System

Implement milestone achievements.

- [x] 10 wins
- [x] 20 wins
- [x] 30 wins
- [x] 50 wins
- [x] 100 wins
- [x] First Run
- [x] First Victory
- [x] Survive 10 rounds
- [x] Win streak milestones (3, 5)

- [x] Achievements are persistent

---

# Persistence

Single local user only.

- [x] No authentication

Persist:

- [x] full run history
- [x] rounds
- [x] chosen moves
- [x] buffs used
- [x] achievements
- [x] statistics
- [x] settings
- [x] unfinished runs

The application must support:

```text
Close app → reopen app → resume unfinished run
```

- [x] Backend supports this (IN_PROGRESS status)
- [ ] Frontend wired to detect and offer resume

---

# Dashboard / Homepage

Homepage should feel like a real application dashboard.

- [ ] total wins (not wired)
- [ ] total losses (not wired)
- [ ] total draws (not wired)
- [ ] win rate (not wired)
- [ ] recent runs (skeleton only)
- [ ] achievement overview (skeleton only)
- [ ] streak information (not wired)
- [ ] current unfinished run (not wired)
- [ ] charts/statistics (not wired — JFreeChart dependency added)
- [x] play button (skeleton)
- [ ] resume button (skeleton, not functional)

Dashboard should provide insights.

- [ ] Dashboard needs end-to-end wiring

---

# Technical Architecture

Use modular monorepo architecture.

```text
root/
├── desktop-client/
├── backend-service/
├── shared/
├── infra/
├── docker/
```

Responsibilities:

desktop-client:

- [x] JavaFX UI
- [x] MVVM (view/viewmodel structure)
- [x] REST communication

backend-service:

- [x] Spring Boot
- [x] business logic
- [x] persistence
- [x] REST APIs

shared:

- [x] DTOs
- [x] shared contracts

infra:

- [x] docker
- [x] scripts
- [x] setup files

---

# Communication

Architecture:

```text
JavaFX Desktop → REST → Spring Backend → PostgreSQL
```

- [x] localhost:8080

Desktop requirements:

- [x] perform backend health checks
- [ ] display useful error if backend unavailable
- [x] do not auto-start backend

---

# JavaFX Requirements

Use MVVM.

- [x] MVVM structure in place

Encourage:

- [ ] reusable views (skeleton only)
- [ ] reusable components (SidebarItem built, others pending)
- [ ] observable properties (not wired yet)
- [x] separation of UI and business logic

Use:

- [ ] tables
- [x] cards (CSS)
- [ ] charts (dependency added)
- [ ] split panes
- [ ] tabs

---

# Assets Strategy

- [x] No custom graphical work required
- [x] Icon libraries (Ikonli — FontAwesome, Material)
- [ ] Animations (pending)
- [x] SVG assets (strategy documented)

If assets are required but missing: Instruct user to manually download assets.

```text
/assets
```

- [x] Avoid requiring custom design work

---

# Audio

- [ ] sound effects (dependency available, not implemented)
- [ ] toggle audio on/off (not implemented)

Examples pending:

- [ ] button click
- [ ] win sound
- [ ] lose sound
- [ ] achievement unlock

---

# Window Support

- [ ] fullscreen mode (setting exists, not wired)
- [x] windowed mode (default)
- [ ] persist preference (setting exists)

---

# Database

- [x] PostgreSQL
- [x] Flyway migrations
- [x] Persist all required game history
- [x] Extensible schema design

---

# Docker

- [x] Local development only
- [x] Docker Compose with PostgreSQL
- [x] Optional DB admin tool (pgAdmin)
- [x] Run backend and desktop locally from IDE
- [x] Avoid containerizing JavaFX desktop

---

# Testing

Mandatory unit tests:

- [x] game engine
- [x] round resolution
- [x] progression logic
- [x] buff logic
- [x] service layer

- [ ] Testing UI is optional (not done)

Prefer:

- [x] unit tests first
- [x] isolated tests
- [x] deterministic tests

---

# OOP Principles

- [x] encapsulation
- [x] composition over inheritance
- [x] SRP
- [x] interface-driven design

- [x] No god classes
- [ ] No giant switch statements (buff switch exists but is scoped)
- [x] No giant service classes

---

# Out of Scope

- [x] Not implemented (correctly excluded)

---

# Success Criteria

- [ ] player can play runs repeatedly (not wired end-to-end)
- [x] history persists
- [ ] dashboard feels informative (skeleton only)
- [x] architecture remains modular
- [x] local development is easy
- [x] tests exist
- [ ] desktop experience feels polished without custom art
- [x] codebase remains maintainable
