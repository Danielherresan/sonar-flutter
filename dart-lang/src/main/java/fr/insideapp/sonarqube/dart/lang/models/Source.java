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
package fr.insideapp.sonarqube.dart.lang.models;

import org.sonar.api.batch.fs.InputFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Source {
    private InputFile inputFile;
    private String contents;
    private List<Issue> issues = new ArrayList<>();

    public Source(InputFile inputFile) throws IOException {
        this.inputFile = inputFile;
        processContent();
    }

    private void processContent() throws IOException {
        this.contents = inputFile.contents();
    }

    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    public InputFile getInputFile() {
        return inputFile;
    }

    public String getContents() {
        return contents;
    }


    public List<Issue> getIssues() {
        return issues;
    }

}
