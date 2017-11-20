package main.Algorithms;

import main.Console;
import main.MatrixGraph;
import main.Path;

public class BVMethod {
    public static boolean findedbetter = false;

    public static Path FindPath(MatrixGraph graph) {

        int n = graph.getCityNumber();
        int[][] basematrix = new int[n][n];
        int[][] bvmatrix = new int[n][n];

        Path now = Greedy.FindLoopPathFrom(graph, 0);// choose first city for begin
        basematrix[0] = now.GetCities();
        Path best = now;

        //get basematrix
        for (int i = 1; i < n; i++) { // without first city
            now = Greedy.FindLoopPathFrom(graph, i);
            basematrix[i] = now.GetCities();

            if (now.GetPathWeight() < best.GetPathWeight()) {
                best = now;
            }
        }

        //get bvmatrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (n - 1); j++) {
                bvmatrix[basematrix[i][j]][basematrix[i][j + 1]]++;
            }
        }

        boolean havebetterpath = false;
        do {
            havebetterpath = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (bvmatrix[i][j] != 0) {
                        now = Modifiers.ApplyAllAndChooseBest(graph, best, i, j);
                        if (now.GetPathWeight() < best.GetPathWeight()) {
                            //System.out.println("Was: " + best.GetPathWeight() + "\tNow: " + now.GetPathWeight());
                            best = now;
                            findedbetter = true;
                            bvmatrix[i][j] = 0;
                            havebetterpath = true;
                        }
                    }
                }
            }
        } while (havebetterpath);


        return best;
    }

    public static void GetStat(int iter, int n) {
        MatrixGraph graph;
        Path path;
        int valOfBetter = 0;
        for (int i = 0; i < iter; i++) {
            graph = new MatrixGraph(n);
            path = FindPath(graph);
            if (findedbetter) {
                valOfBetter++;
                findedbetter = false;
            }
        }
        //System.out.println("Finded better: " + valOfBetter + ", from " + iter);
        System.out.println(valOfBetter + " " + iter);
    }

    public static void GetStat() {
        MatrixGraph graph;
        Path path;
        double valOfBetter = 0;
        double max = 0;
        int iter = 500;
        for (int n = 2; n <= 25; n++) {
            valOfBetter = 0;
            for (int i = 0; i < iter; i++) {
                graph = new MatrixGraph(n);
                path = FindPath(graph);
                if (findedbetter) {
                    valOfBetter++;
                    findedbetter = false;
                }
            }
            if (valOfBetter / 5 > max) max = valOfBetter / 5;
            //System.out.println("Finded better: " + valOfBetter + ", from " + iter);
            System.out.println("(" + n + ";" + valOfBetter / 5 + ") ");
        }
        System.out.println(max);
    }
}
