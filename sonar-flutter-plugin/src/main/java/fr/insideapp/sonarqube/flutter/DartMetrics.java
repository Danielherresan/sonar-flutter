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
package fr.insideapp.sonarqube.flutter;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.Arrays;
import java.util.List;

public class DartMetrics implements Metrics {
    private static final Metric<Double> NOT_ALLOWED_WIDGET_STYLE = new Metric.Builder(
            "not_allowed_widget_style",
            "Number of classes not allowed in dart files",
            Metric.ValueType.FLOAT)
            .setDescription("Shows the number of classes that should not be used defined by design system")
            .setDirection(Metric.DIRECTION_WORST)
            .setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_RELIABILITY)
            .create();

    @SuppressWarnings("rawtypes")
    @Override
    public List<Metric> getMetrics() {
        return Arrays.asList(NOT_ALLOWED_WIDGET_STYLE);
    }
}
