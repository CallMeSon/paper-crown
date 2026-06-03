# Repository Guidelines

## Project Structure & Module Organization

Java 21 multi-module Gradle project for a local-first Rock-Paper-Scissors roguelike.

- `backend-service/` contains the Spring Boot REST API, JPA entities, repositories, services, controllers, Flyway migrations, and tests.
- `desktop-client/` contains the JavaFX MVVM app: `view/`, `viewmodel/`, reusable `component/`, backend `service/`, and `util/`.
- `shared/` contains DTOs and enums shared by the backend and desktop client.
- `docker/docker-compose.yml` starts PostgreSQL for local development.
- `DESIGN.md`, `PRODUCT.md`, `TODO.md`, and `README.md` document product context.

## Build, Test, and Development Commands

Use the Gradle wrapper from the repository root:

```bash
docker compose -f docker/docker-compose.yml up -d
./gradlew build
./gradlew test
./gradlew :backend-service:bootRun
./gradlew :desktop-client:run
```

- `docker compose ... up -d` starts PostgreSQL 16.
- `./gradlew build` compiles all modules and runs tests.
- `./gradlew test` runs JUnit tests.
- `:backend-service:bootRun` starts the API on `http://localhost:8080`.
- `:desktop-client:run` launches the JavaFX client.

## Coding Style & Naming Conventions

Use standard Java formatting with 4-space indentation. Keep packages under `com.papercrown`. Name classes by role, such as `RunService`, `StatsController`, `RunRepository`, `RunDTO`, and `PlayViewModel`. Keep business rules in backend `service/`, REST concerns in `controller/`, persistence in `entity/` and `repository/`, and UI state in desktop `viewmodel/`.

Prefer explicit, small methods over deeply nested logic. Keep shared DTOs stable because both modules depend on them.

## Testing Guidelines

Tests use JUnit 5 and Mockito. Place tests under `src/test/java`, mirroring the package under test. Name test classes with the `*Test` suffix, for example `GameEngineTest`.

Run `./gradlew test` before committing. Add focused unit tests for backend game rules, service behavior, stats calculations, and buff effects. For persistence or API changes, cover migrations, entity mappings, or controllers where practical.

## Commit & Pull Request Guidelines

Recent history uses Conventional Commit style:

```text
feat(backend): implement spring boot backend
feat(desktop): add reusable UI components
docs: add project README
```

Use `type(scope): summary` where `type` is commonly `feat`, `fix`, `docs`, `test`, `refactor`, or `chore`. Keep summaries imperative and concise.

Pull requests should include a clear description, affected modules, testing performed, and screenshots or recordings for JavaFX UI changes. Link related issues when available and call out database migration or configuration changes.

## Security & Configuration Tips

Do not commit secrets, non-local database credentials, or generated build outputs. Keep deployable settings in Spring profiles or environment variables. Treat `shared/` API changes as compatibility-sensitive because they affect both backend and desktop client.
