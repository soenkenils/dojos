# dojos

A collection of independent coding-kata challenges, each as its own Gradle subproject. Used for practice and TDD training.

## Layout

Multi-module Gradle build. Root `build.gradle` configures all subprojects (Java toolchain, repositories, test platform); `settings.gradle` lists the modules. Each module is a self-contained challenge with its own `build.gradle`, source, and tests.

| Module | Main language | Test framework |
|--------|---------------|----------------|
| `abacus` | Java | Spock (Groovy) |
| `anagram` | Groovy | Spock (Groovy) |
| `ataleofthreecities` | Java | Spock (Groovy) |
| `business-tasks` | Java | Spock (Groovy) |
| `ellys-chocolates` | Kotlin | Kotest |
| `little-elephant` | Java | Spock (Groovy) |
| `tall-people` | Java | Spock (Groovy) |
| `wolf-delaymaster` | Java | Spock (Groovy) |

## Build & Test

Use the Gradle wrapper. Java 21 toolchain required (auto-provisioned by Gradle if missing).

```bash
# Run all tests
./gradlew test

# Build everything
./gradlew build

# Test a single module
./gradlew :abacus:test

# Clean build
./gradlew clean build
```

## Toolchain & Versions

| Tool / Library | Version |
|----------------|---------|
| Gradle | 9.5.1 (wrapper) |
| Java toolchain | 21 |
| Kotlin | 2.2.0 |
| Groovy | 4.0.23 |
| Spock | 2.4-M6-groovy-4.0 |
| JUnit Platform | 5.11.3 |
| Kotest | 5.9.1 |
| Guava | 33.3.1-jre |
| Logback | 1.5.12 |
| SLF4J | 2.0.16 |

Shared library versions live in the `ext` block of the root `build.gradle`. The Kotlin plugin and Kotest versions are declared inline in `ellys-chocolates/build.gradle`.

## Conventions

- Each module solves one kata; main source under `src/main/{java,groovy,kotlin}`, tests under `src/test/{groovy,kotlin}`.
- Java/Groovy modules test with Spock specs (`*Spec.groovy`); the Kotlin module tests with Kotest (`*Test.kt`).
- TDD-friendly: prefer adding a failing test before changing solution code.

## Known Improvement Areas

- No CI pipeline yet (`.github/workflows`). A workflow running `./gradlew build` on push/PR would catch regressions.
- Spock is pinned to a milestone release (`2.4-M6`); the stable `2.3-groovy-4.0` is more reliable.
- Versions are split between the root `ext` block and inline module config; a `gradle/libs.versions.toml` version catalog would centralize them.
