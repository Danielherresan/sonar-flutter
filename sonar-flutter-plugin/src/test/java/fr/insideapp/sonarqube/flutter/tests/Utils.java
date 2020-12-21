/*
 * SonarQube Flutter Plugin
 * Copyright (C) 2020 inside|app
 * contact@insideapp.fr
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package fr.insideapp.sonarqube.flutter.tests;

import fr.insideapp.sonarqube.dart.lang.Dart;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static final String MODULE_KEY = "sonar-design-system-flutter";

    public static final Path BASE_DIR = Paths.get("src", "test", "resources");

    private Utils() {
    }

    public static InputFile getInputFile(String relativePath) throws IOException {
        return TestInputFileBuilder.create(MODULE_KEY, BASE_DIR.resolve(relativePath).toString())
                .setModuleBaseDir(Paths.get("."))
                .setContents(new String(Files.readAllBytes(BASE_DIR.resolve(relativePath))))
                .setLanguage(Dart.KEY)
                .setCharset(StandardCharsets.UTF_8)
                .build();
    }

    public static SensorContextTester getSensorContext() {
        return SensorContextTester.create(BASE_DIR);
    }

    public static DefaultFileSystem getFileSystem() {
        return new DefaultFileSystem(BASE_DIR);
    }
}

