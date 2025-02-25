# Configurable Java Format Maven Plugin

[![mvn verify][ci_img]][ci_link]
[![coverity][coverity_img]][coverity_link]
[![Maven Central][maven_img]][maven_link]
[![Apache License][license_img]][license_link]

A Maven plugin for automated source code formatting using the Google Java Formatter, with configurable options.

This software is provided WITHOUT ANY WARRANTY, and is available under the Apache License, Version 2. Any code loss
caused by using this plugin is not the responsibility of the author(s). Be sure to use some source repository management
system such as GIT before using this plugin.

Contributions are welcome.

## Installation

Add the plugin to your `pom.xml`:

```xml

<build>
    <plugins>
        <plugin>
            <groupId>io.github.mrdolch.formatter</groupId>
            <artifactId>configurable-java-format-maven-plugin</artifactId>
            <version>2025.21.1</version>
        </plugin>
    </plugins>
</build>
```

```xml

<repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/MrDolch/configurable-java-format-maven-plugin</url>
</repository>
```

## Goals

### `check`

Checks if the source code conforms to the formatting rules. The build will fail if the formatting is incorrect.

```bash
export MAVEN_OPTS="--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED"

mvn io.github.mrdolch.formatter:configurable-java-format-maven-plugin:check
```

### `format`

Formats the source code according to the specified parameters.

```bash
export MAVEN_OPTS="--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
  --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED"

mvn io.github.mrdolch.formatter:configurable-java-format-maven-plugin:format
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
            <groupId>io.github.mrdolch.formatter</groupId>
            <artifactId>configurable-format-maven-plugin</artifactId>
            <version>2025.21.1</version>
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

[ci_img]: https://github.com/mrdolch/configurable-java-format-maven-plugin/actions/workflows/maven-verify.yml/badge.svg

[ci_link]: https://github.com/mrdolch/configurable-java-format-maven-plugin/actions/maven-verify.yml

[coverity_link]: https://scan.coverity.com/projects/configurable-java-format-maven-plugin

[coverity_img]: https://scan.coverity.com/projects/31384/badge.svg

[maven_img]: https://maven-badges.herokuapp.com/maven-central/io.github.mrdolch.formatter/configurable-java-format-maven-plugin/badge.svg

[maven_link]: https://maven-badges.herokuapp.com/maven-central/io.github.mrdolch.formatter/configurable-java-format-maven-plugin

[license_img]: https://img.shields.io/badge/license-Apache%202.0-blue.svg

[license_link]: https://github.com/revelc/formatter-maven-plugin/blob/main/LICENSE