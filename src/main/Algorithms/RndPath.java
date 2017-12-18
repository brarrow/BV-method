package main.Algorithms;

import main.MatrixGraph;
import main.Path;
import main.SpecialValues;

import java.util.Random;

public class RndPath {
    public static Path FindLoopPathFrom(MatrixGraph graph, int cityfirst) {
        double min_val;
        int citynow = cityfirst;
        Random rnd = new Random(System.currentTimeMillis());
        int city_suggest;
        int n = graph.getCityNumber();
        int path[] = new int[n];
        boolean visited[] = new boolean[n];

        for (int moves = 1; moves < n; moves++) {
            visited[citynow] = true;
            path[moves] = citynow;
            city_suggest = -1;
            while (city_suggest == -1) {
                int nmb = 0 + rnd.nextInt(n);
                if (!visited[nmb]) {
                    city_suggest = nmb;
                    visited[nmb] = true;
                }
            }
            if (city_suggest != -1) citynow = city_suggest;
        }
        return new Path(graph, path);
    }
}
