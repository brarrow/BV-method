package main.Algorithms;

import main.Console;
import main.MatrixGraph;
import main.Path;
import main.SpecialValues;

public class BVMethod {
    public static boolean findedbetter = false;

    public static Path FindPath(MatrixGraph graph, boolean rnd) {

        int n = graph.getCityNumber();
        int[][] basematrix = new int[n][n];
        int[][] bvmatrix = new int[n][n];
        Path now;
        Path best;
        //start paths with rnd or greedy alg
        if (rnd) {
            now = RndPath.FindLoopPathFrom(graph, 0);// choose first city for begin
            basematrix[0] = now.GetCities();
            best = now;

            //get basematrix
            for (int i = 1; i < n; i++) { // without first city
                now = RndPath.FindLoopPathFrom(graph, i);
                basematrix[i] = now.GetCities();

                if (now.GetPathWeight() < best.GetPathWeight()) {
                    best = now;
                }
            }
        } else {
            now = Greedy.FindLoopPathFrom(graph, 0);// choose first city for begin
            basematrix[0] = now.GetCities();
            best = now;

            //get basematrix
            for (int i = 1; i < n; i++) { // without first city
                now = Greedy.FindLoopPathFrom(graph, i);
                basematrix[i] = now.GetCities();

                if (now.GetPathWeight() < best.GetPathWeight()) {
                    best = now;
                }
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
                        now = Modifiers.ApplyAllAndChooseBest(graph, now, i, j);
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
        int valOfBetterRnd = 0;
        int valOfBetterGrd = 0;
        for (int i = 0; i < iter; i++) {
            graph = new MatrixGraph(n);
            path = FindPath(graph, true);
            if (findedbetter) {
                valOfBetterRnd++;
                findedbetter = false;
            }
            path = FindPath(graph, false);
            if (findedbetter) {
                valOfBetterGrd++;
                findedbetter = false;
            }
        }
        //System.out.println("Finded better: " + valOfBetter + ", from " + iter);
        System.out.println("Random: " + valOfBetterRnd + " " + iter);
        System.out.println("Greedy: " + valOfBetterGrd + " " + iter);
    }

    public static void GetStat() {
        MatrixGraph graph;
        Path pathBuf;
        double bestWeightRnd = SpecialValues.INF;
        double bestWeightGrd = SpecialValues.INF;
        int valOfBetterRnd = 0;
        int valOfBetterGrd = 0;
        double maxRnd = 0;
        double maxGrd = 0;
        int iter = 500;
        for (int n = 2; n <= 25; n++) {
            valOfBetterRnd = 0;
            valOfBetterGrd = 0;
            bestWeightGrd = SpecialValues.INF;
            bestWeightRnd = SpecialValues.INF;
            for (int i = 0; i < iter; i++) {
                graph = new MatrixGraph(n);
                pathBuf = FindPath(graph, true);
                if (findedbetter) {
                    valOfBetterRnd++;
                    findedbetter = false;
                }
                if (pathBuf.GetPathWeight() < bestWeightRnd) {
                    bestWeightRnd = pathBuf.GetPathWeight();
                }
                pathBuf = FindPath(graph, false);
                if (findedbetter) {
                    valOfBetterGrd++;
                    findedbetter = false;
                }
                if (pathBuf.GetPathWeight() < bestWeightGrd) {
                    bestWeightGrd = pathBuf.GetPathWeight();
                }
            }
            if (valOfBetterRnd / 5 > maxRnd) maxRnd = valOfBetterRnd / 5;
            if (valOfBetterGrd / 5 > maxGrd) maxGrd = valOfBetterGrd / 5;
            //System.out.println("Finded better: " + valOfBetter + ", from " + iter);
            System.out.println("Rnd:(" + n + ";" + valOfBetterRnd / 5 + "), Length = " + bestWeightRnd);
            System.out.println("Grd:(" + n + ";" + valOfBetterGrd / 5 + ") , Length = " + bestWeightGrd);

        }
        System.out.println("Max rnd: " + maxRnd + " Max grd: " + maxGrd);
    }
}
