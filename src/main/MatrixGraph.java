package main;

import java.util.Random;

/**
 * Created by lelay on 09.10.17.
 */
public class MatrixGraph {

    private double[][] matrixGraph;

    public MatrixGraph(double[][] matrixGraph) {
        this.matrixGraph = matrixGraph;
    }

    public MatrixGraph(int cityNumber) {
        this.matrixGraph = generateGraph(cityNumber);
    }

    public double[][] generateGraph(int cityNumber, int delta) {
        double[][] matrGr = new double[cityNumber][cityNumber];

        Random rand = new Random();

        for(int i = 0; i < cityNumber; ++i) {
            matrGr[i][(i + 1) % cityNumber] = rand.nextDouble() * delta;
            // rand.nextDouble get us the value in borders [0, 1]
        }

        return matrGr;
    }

    //defaut value of the delta param is 100
    public double[][] generateGraph(int cityNumber) {
        return generateGraph(cityNumber, 100);
    }

    public void showMatrixGraph() {
        for(int i = 0; i < this.matrixGraph.length; ++i) {
            for(int j = 0; j < this.matrixGraph[0].length; ++j) {
                System.out.print(this.matrixGraph[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public void setWeight(int i, int j, double weight) {
        this.matrixGraph[i][j] = weight;
    }

    public double getWeight(int i, int j) {
        return this.matrixGraph[i][j];
    }
}
