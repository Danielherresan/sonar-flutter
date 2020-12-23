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
package fr.insideapp.sonarqube.dart.lang.checks;
import fr.insideapp.sonarqube.dart.lang.models.Issue;
import fr.insideapp.sonarqube.dart.lang.models.Location;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import java.util.List;
import java.util.stream.Stream;
@Rule(key = "NotAllowedWidgetCheck")
public class NotAllowedWidgetCheck extends AbstractDartCheck {
    private static final Logger LOGGER = Loggers.get(NotAllowedWidgetCheck.class);
    private static final String ERROR_MESSAGE = "The usage of %s class is not allowed";
    private static final String GALATEA_DIVISOR = "galateadivisor2730";
    HelperChecks helperChecks = new HelperChecks();
    private int index = 0;
    @RuleProperty(key = "not-allowed-class", description = "Regexp that matches the class style")
    public
    String classStyleReg;
    @Override
    public void validate() {
        if (DartSource == null) {
            throw new IllegalStateException("Source code not set, cannot validate anything");
        }
        String [] rules = classStyleReg.split(",");
        String [] lines  = DartSource.getContents().split("\n");
        Stream.of (rules).forEach(rule ->{
            int[] i = new int[]{0};
            Stream.of(lines)
                    .map(s -> ++i[0] +GALATEA_DIVISOR+s)
                    .filter(line -> line.contains(rule))
                    .forEach(foundLine ->{
                        int currentLine= Integer.parseInt( foundLine.split(GALATEA_DIVISOR)[0]);
                        foundLine =  foundLine.split(GALATEA_DIVISOR)[1];
                        index = foundLine.indexOf(rule);
                        List<Integer> resultsPerLine = helperChecks.foundColumn(foundLine,rule);
                        if (!resultsPerLine.isEmpty() ) {
                            Stream.of(resultsPerLine).forEach(columns -> {
                                columns.forEach(column -> generateIssue( new Location(currentLine,column)));
                            });
                        }
                    });
        });
    }

    private void generateIssue(Location errorLocation ) {
        String message = String.format(ERROR_MESSAGE, classStyleReg);
        LOGGER.info(message);
        Issue issue = new Issue(getRuleKey(), errorLocation.getLine(), errorLocation.getColumn(), message );
        getDartSource().addIssue(issue);
    }
}
