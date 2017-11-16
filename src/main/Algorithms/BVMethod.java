package main.Algorithms;

import com.sun.org.apache.bcel.internal.classfile.ConstantDouble;
import main.Console;
import main.MatrixGraph;
import main.Path;

public class BVMethod {
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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(bvmatrix[i][j] + " ");
            }
            System.out.println();
        }

        boolean havebetterpath = false;
        do {
            havebetterpath = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (bvmatrix[i][j] != 0) {
                        now = Modifiers.ApplyAllAndChooseBest(graph, best, i, j);
                        if (now.GetPathWeight() < best.GetPathWeight()) {
                            System.out.println("Was: " + best.GetPathWeight() + "Now: " + now.GetPathWeight());
                            best = now;
                            bvmatrix[i][j] = 0;
                            havebetterpath = true;
                        }
                    }
                }
            }
        } while (havebetterpath);


        return best;
    }
}
