package main.Algorithms;

import main.MatrixGraph;
import main.SpecialValues;
import main.Path;

public class Greedy {
    public static Path FindLoopPath(MatrixGraph graph) {

        double min_val = SpecialValues.INF;
        int n = graph.getCityNumber();
        int city_now = 0;

        for (int i = 0; i < n; i++) { // finding optimal first step
            for (int j = 0; j < n; j++) {
                if (graph.getWeight(i, j) < min_val) {
                    min_val = graph.getWeight(i, j);
                    city_now = i;
                }
            }
        }
        return FindLoopPathFrom(graph, city_now);
    }

    public static Path FindLoopPathFrom(MatrixGraph graph, int cityfirst) {

        double min_val = graph.INF;
        int citynow = cityfirst;
        int city_suggest;
        int n = graph.getCityNumber();
        int path[] = new int[n];
        boolean visited[] = new boolean[n];

        for (int moves = 0; moves < n; moves++) {
            visited[citynow] = true;
            path[moves] = citynow;

            min_val = SpecialValues.INF;
            city_suggest = -1;
            for (int j = 0; j < n; j++) {
                if ((graph.getWeight(citynow, j) < min_val) && (!visited[j])) {
                    min_val = graph.getWeight(citynow, j);
                    city_suggest = j;
                }
            }
            citynow = city_suggest;
        }
        return new Path(graph, path);
    }
}
