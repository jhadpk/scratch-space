package com.workspace.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class DirectionProblem {
    private static List<String> northSouth = new LinkedList<>(Arrays.asList("N", "S"));
    private static List<String> eastWest = new LinkedList<>(Arrays.asList("E", "W"));
    private static final List<String> directions = Arrays.asList("N", "S", "E", "W");
    private static final HashMap<String, String> restrictedAccess = new HashMap<>();

    private HashSet<String> uniquePaths = new HashSet<>();

    static {
        restrictedAccess.put("N", "S");
        restrictedAccess.put("S", "N");
        restrictedAccess.put("E", "W");
        restrictedAccess.put("W", "E");
    }

    private void findDirection(final List<String> pathTaken, final int length, final String pathToTake) {
        if (pathTaken.size() == 0) {
            pathTaken.add(pathToTake);
        } else if (pathTaken.size() < length && !pathToTake.equalsIgnoreCase(
                restrictedAccess.get(pathTaken.get(pathTaken.size() - 1)))) {
            pathTaken.add(pathToTake);

        }

        if (pathTaken.size() == length) {
            if (!uniquePaths.contains(pathTaken.toString())) {
                uniquePaths.add(pathTaken.toString());
                System.out.println(pathTaken.toString());
                pathTaken.remove(pathTaken.size() - 1);
            }
        } else {
            if (northSouth.contains(pathToTake)) {
                for (final String ewDir : eastWest) {
                    findDirection(pathTaken, length, ewDir);
                }
                //since movement in same direction is allowed
                findDirection(pathTaken, length, pathToTake);
            }
            if (eastWest.contains(pathToTake)) {
                for (final String nsDir : northSouth) {
                    findDirection(pathTaken, length, nsDir);
                }
                //since movement in same direction is allowed
                findDirection(pathTaken, length, pathToTake);
            }

            removeLast(pathTaken);

        }
    }


    private void removeLast(final List<String> pathTaken) {
        pathTaken.remove(pathTaken.size() - 1);
    }


    public static void main(String[] args) {
        for (String direction : directions) {
            new DirectionProblem().findDirection(new ArrayList<String>(), 5, direction);
        }
    }
}
