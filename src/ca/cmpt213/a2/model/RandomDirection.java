package ca.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomDirection {
    String[] directions = {"left", "right", "up", "down"};
    List<String> randomizedDir = new ArrayList<>();

    public RandomDirection() {
        Collections.addAll(randomizedDir, directions);
        Collections.shuffle(randomizedDir);
    }

    public List<String> getRandomizedDir() {
        return randomizedDir;
    }
} // RandomDirection.java