# Dependency Updates & Modernization

## Overview

This document describes the dependency updates performed on 2025-11-23 to bring this coding dojo repository up to date with the latest stable versions of all dependencies.

## Changes Made

### Build System & Tools

| Dependency | Old Version | New Version | Change |
|------------|-------------|-------------|--------|
| Gradle | 4.10.2 | 9.1 | Major upgrade spanning 4 major versions |
| Kotlin | 1.2.71 | 2.1.0 | Major upgrade with modern language features |
| Kotlin JVM Target | 1.8 | 17 | Updated to modern LTS Java version |

### Libraries

| Dependency | Old Version | New Version | Notes |
|------------|-------------|-------------|-------|
| Logback | 1.2.3 | 1.5.12 | Logging framework upgrade |
| SLF4J | 1.7.5 | 2.0.16 | Major version upgrade for logging API |
| Guava | 23.5-jre | 33.3.1-jre | Google's core libraries |
| JUnit | 4.12 | 5.11.3 | Major upgrade to JUnit 5 |
| Groovy | 2.5.0 | 4.0.23 | Major version upgrade |
| Spock | 1.1-groovy-2.4 | 2.3-groovy-4.0 | Testing framework upgrade |

### Configuration Modernization

1. **Dependency Configurations**: Replaced deprecated `compile` and `testCompile` with `implementation` and `testImplementation`
2. **Repository Configuration**: Modernized from explicit Maven Central URL to `mavenCentral()` shorthand
3. **Gradle Wrapper**: Updated wrapper properties to point to Gradle 9.1

## Files Modified

```
build.gradle                              # Root build configuration
gradle/wrapper/gradle-wrapper.properties  # Gradle wrapper version
abacus/build.gradle                       # Subproject dependencies
anagram/build.gradle                      # Subproject dependencies
ataleofthreecities/build.gradle          # Subproject dependencies
business-tasks/build.gradle              # Subproject dependencies
ellys-chocolates/build.gradle            # Kotlin project dependencies
little-elephant/build.gradle             # Subproject dependencies
tall-people/build.gradle                 # Subproject dependencies
wolf-delaymaster/build.gradle            # Subproject dependencies
```

## Breaking Changes & Considerations

### JUnit 4 → 5 Migration

While JUnit version was updated to 5.11.3, the actual test code still uses JUnit 4 APIs. To fully leverage JUnit 5:

- Update test imports from `org.junit.*` to `org.junit.jupiter.api.*`
- Replace `@Test` annotations with JUnit 5 equivalents
- Consider using `@ParameterizedTest` for data-driven tests
- Update assertions to use JUnit 5's improved assertion methods

### SLF4J 1.x → 2.x

SLF4J 2.x includes:
- Fluent logging API
- Better performance
- Modern Java features support

Existing code should continue to work, but consider adopting the fluent API for new code.

### Gradle 4 → 9

Major changes include:
- Configuration cache enabled by default in newer versions
- Build performance improvements
- Enhanced dependency management
- Better Kotlin DSL support

## Top Three Recommended Improvements

### 1. Migrate to Gradle Kotlin DSL

**Current**: Groovy-based `build.gradle` files
**Recommended**: Kotlin-based `build.gradle.kts` files

**Benefits**:
- Type-safe build configuration
- Superior IDE support (autocomplete, refactoring, navigation)
- Compile-time error detection
- Industry standard for modern Gradle projects
- Consistency with Kotlin codebase

**Migration Path**:
```bash
# Gradle provides automated migration assistance
./gradlew init --type kotlin-application
```

### 2. Implement Gradle Version Catalog

**Current**: Versions defined in `ext` block in root `build.gradle`
**Recommended**: Create `gradle/libs.versions.toml`

**Benefits**:
- Centralized dependency management
- Type-safe dependency accessors
- Easier to update multiple dependencies
- Better suited for multi-module projects
- IDE support for version updates

**Example Structure**:
```toml
[versions]
logback = "1.5.12"
slf4j = "2.0.16"
guava = "33.3.1-jre"
spock = "2.3-groovy-4.0"

[libraries]
logback-classic = { module = "ch.qos.logback:logback-classic", version.ref = "logback" }
slf4j-api = { module = "org.slf4j:slf4j-api", version.ref = "slf4j" }
guava = { module = "com.google.guava:guava", version.ref = "guava" }
spock-core = { module = "org.spockframework:spock-core", version.ref = "spock" }
```

### 3. Add CI/CD Pipeline

**Current**: No automated testing infrastructure
**Recommended**: GitHub Actions workflow

**Benefits**:
- Automated testing on every commit/PR
- Cross-platform testing (if needed)
- Early detection of build failures
- Code quality enforcement
- Dependency vulnerability scanning

**Example Workflow** (`.github/workflows/ci.yml`):
```yaml
name: CI

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Build with Gradle
        run: ./gradlew build
      - name: Run tests
        run: ./gradlew test
```

## Additional Improvement Opportunities

### Code Quality Tools
- **SpotBugs**: Static analysis for Java/Kotlin code
- **Checkstyle**: Enforce coding standards
- **Detekt**: Kotlin static analysis
- **JaCoCo**: Code coverage reporting

### Documentation
- Add module-specific README files explaining each dojo challenge
- Document test scenarios and edge cases
- Create contribution guidelines

### Testing
- Migrate from JUnit 4 to JUnit 5 (tests currently use old API)
- Add integration tests if applicable
- Implement property-based testing with Spock's data tables

### Dependency Management
- Add Dependabot or Renovate for automated dependency updates
- Implement dependency lock files for reproducible builds

## Testing the Updates

To verify the updates work correctly:

```bash
# Clean build
./gradlew clean

# Build all projects
./gradlew build

# Run tests
./gradlew test

# Check for dependency updates (requires plugin)
./gradlew dependencyUpdates
```

## Rollback Procedure

If issues arise, revert to previous versions:

```bash
git revert HEAD~2  # Reverts last 2 commits (Gradle update + dependency updates)
```

Or manually restore from commit `0ac3cb3` (before updates).

## References

- [Gradle 9.1 Release Notes](https://docs.gradle.org/9.1/release-notes.html)
- [Spock Framework 2.3 Documentation](https://spockframework.org/spock/docs/2.3/all_in_one.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Kotlin 2.1.0 Release](https://kotlinlang.org/docs/whatsnew21.html)

## Questions or Issues?

If you encounter any issues with the updated dependencies:

1. Check the compatibility matrix for each library
2. Review migration guides for major version upgrades
3. Test each module independently to isolate issues
4. Consider incremental rollback of specific dependencies if needed

---

**Last Updated**: 2025-11-23
**Updated By**: Claude (AI Assistant)
**Branch**: `claude/update-dependencies-01BnCxkW8wyHWpkgymKDWDzS`
