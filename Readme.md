# Configurable Google Java Format Maven Plugin

A Maven plugin for automated source code formatting using the Google Java Formatter, with configurable options.

## Installation

Add the plugin to your `pom.xml`:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>tech.dolch.formatter</groupId>
            <artifactId>configurable-google-java-format-maven-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
        </plugin>
    </plugins>
</build>
```

```xml
<repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/MrDolch/configurable-google-java-format-maven-plugin</url>
</repository>
```

## Goals

### `check`
Checks if the source code conforms to the formatting rules. The build will fail if the formatting is incorrect.

```bash
mvn tech.dolch.formatter:configurable-google-java-format-maven-plugin:check
```

### `format`
Formats the source code according to the specified parameters.

```bash
mvn tech.dolch.formatter:configurable-google-java-format-maven-plugin:format
```

## Configuration Parameters

| Parameter                   | Property                    | Default | Description                    |
|-----------------------------|-----------------------------|---------|--------------------------------|
| `aosp`                      | `aosp`                      | `false` | Use AOSP formatting style      |
| `width`                     | `width`                     | `100`   | Maximum line length            |
| `fixImportsOnly`            | `fixImportsOnly`            | `false` | Only fix imports               |
| `skipSortingImports`        | `skipSortingImports`        | `false` | Do not sort imports            |
| `skipRemovingUnusedImports` | `skipRemovingUnusedImports` | `false` | Do not remove unused imports   |
| `skipReflowingLongStrings`  | `skipReflowingLongStrings`  | `false` | Do not reflow long strings     |
| `skipJavadocFormatting`     | `skipJavadocFormatting`     | `false` | Do not format Javadoc comments |

## Example Configuration

```xml
<build>
    <plugins>
        <plugin>
            <groupId>tech.dolch.formatter</groupId>
            <artifactId>configurable-google-java-format-maven-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
            <executions>
                <execution>
                    <goals>
                        <goal>format</goal>
                    </goals>
                    <configuration>
                        <width>120</width>
                        <fixImportsOnly>false</fixImportsOnly>
                        <skipSortingImports>false</skipSortingImports>
                        <skipRemovingUnusedImports>false</skipRemovingUnusedImports>
                        <skipReflowingLongStrings>false</skipReflowingLongStrings>
                        <skipJavadocFormatting>false</skipJavadocFormatting>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```


