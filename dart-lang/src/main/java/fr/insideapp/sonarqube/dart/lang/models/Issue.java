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

import org.sonar.api.rule.RuleKey;

public class Issue {
    private final RuleKey ruleKey;
    private final int line;
    private final int column;
    private final String message;

    public Issue(RuleKey ruleKey, int line, int column, String message) {
        this.ruleKey = ruleKey;
        this.line = line;
        this.column = column;
        this.message = message;
    }

    public RuleKey getRuleKey() {
        return ruleKey;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getMessage() {
        return message;
    }

}
