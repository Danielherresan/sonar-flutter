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
package fr.insideapp.sonarqube.flutter.rules;


import fr.insideapp.sonarqube.dart.lang.Dart;
import fr.insideapp.sonarqube.dart.lang.checks.CheckRepository;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

import java.util.ArrayList;
import java.util.List;

public class DartRulesDefinition implements RulesDefinition {


    public static final String RULES_DEFINITION_FOLDER = "org/sonar/i10n/design-system-flutter/rules/design-system-flutter";


    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(CheckRepository.REPOSITORY_KEY, Dart.KEY)
                .setName(CheckRepository.REPOSITORY_NAME);

        RuleMetadataLoader metadataLoader = new RuleMetadataLoader(RULES_DEFINITION_FOLDER);
        @SuppressWarnings("rawtypes")
        List<Class> allCheckClasses = new ArrayList<>(CheckRepository.getCheckClasses());
        metadataLoader.addRulesByAnnotatedClass(repository, allCheckClasses);

        for (NewRule rule : repository.rules()) {
            if (CheckRepository.getTemplateRuleKeys().contains(rule.key())) {
                rule.setTemplate(true);
            }
        }
        repository.done();
    }
}

