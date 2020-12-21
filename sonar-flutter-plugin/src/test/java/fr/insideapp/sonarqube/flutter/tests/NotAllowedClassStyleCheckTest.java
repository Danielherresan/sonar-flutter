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


import fr.insideapp.sonarqube.dart.lang.checks.NotAllowedClassStyleCheck;
import fr.insideapp.sonarqube.dart.lang.models.Source;
import fr.insideapp.sonarqube.flutter.tests.Utils;
import org.junit.Rule;
import org.junit.Test;
import org.sonar.api.utils.log.LogTester;
import org.sonar.api.utils.log.LoggerLevel;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class NotAllowedClassStyleCheckTest {
    @Rule
    public LogTester logTester = new LogTester();

    @Test
    public void testFailedValidateNoSource() {
        NotAllowedClassStyleCheck check = new NotAllowedClassStyleCheck();
        try {
            check.validate();
            fail("No source code should raise an exception");
        } catch (IllegalStateException e) {
            assertEquals("Source code not set, cannot validate anything", e.getMessage());
        }
    }

    @Test
    public void shouldNotHandleIssue() throws IOException {
        // Arrange
        Source source = getSource("main.dart");
        System.out.println("Naniiiiiiiiiiiiiiiiiiiiiiiiiiii");
        NotAllowedClassStyleCheck check = new NotAllowedClassStyleCheck();
        check.setDartSource(source);
        check.classStyleReg = "Text,ButtonElement";
        // Act
        check.validate();
        // Assert
        assertEquals(8, source.getIssues().size());
    }

    @Test
    public void shouldHandleIssue() throws IOException {
        // Arrange
        Source source = getSource("main.dart");
        NotAllowedClassStyleCheck check = new NotAllowedClassStyleCheck();
        check.setDartSource(source);
        check.classStyleReg = "Text,ButtonElement";
        String message = "The usage of Text,ButtonElement class is not allowed";
        // Act
        check.validate();
        // Assert
        assertEquals(8, logTester.logs(LoggerLevel.INFO).size());
        assertThat(logTester.logs(LoggerLevel.INFO).get(0), containsString(message));
        assertEquals(8, source.getIssues().size());
    }

    private Source getSource(String filename) throws IOException {
        return new Source(Utils.getInputFile("style-class/" + filename));
    }

}
