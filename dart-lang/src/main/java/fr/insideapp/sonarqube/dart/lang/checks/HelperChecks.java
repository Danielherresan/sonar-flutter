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
import java.util.ArrayList;
import java.util.List;

public class HelperChecks {
  public  List<Integer> foundColumn(String line, String word){
      int index = line.indexOf(word);
      int currentPosition = 0;
      List <Integer>  issuesPositions = new ArrayList<Integer>();
      while (index != -1){
          index = line.indexOf(word);
          String subLine = line.substring(index - 2, index);
          String endLine = line.substring(index).replaceAll("\\s+", "");
          currentPosition += index+1;
          if (!subLine.matches(".*[a-z].*")&& endLine.substring(0, word.length() + 1).equals(word + "(")){
              issuesPositions.add(currentPosition);
          }
          line = line.substring(index+1);
          index = line.indexOf(word);
      }
      return issuesPositions;
  }
 }
