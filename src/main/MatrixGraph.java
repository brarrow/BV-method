package main;

/**
 * Created by lelay on 09.10.17.
 */
public class MatrixGraph {

    private double[][] matrixGraph;

    public MatrixGraph(double[][] matrixGraph) {
        this.matrixGraph = matrixGraph;
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
