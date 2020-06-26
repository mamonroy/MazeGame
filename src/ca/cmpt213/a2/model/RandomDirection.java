package ca.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class for generating an array of random direction strings.
 *
 * @author Mark Angelo Monroy (Student ID: 301326143, SFU ID: mamonroy@sfu.sfu)
 * @author Kash Khodabakhshi (Student ID: 301203001, SFU ID: kkhodaba@sfu.ca)
 */
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