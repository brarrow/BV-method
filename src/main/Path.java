package main;

public class Path {
    private int[] cities;
    private int length;
    private double pathweight;

    public Path(MatrixGraph graph, int[] cities) {
        this.cities = cities;
        pathweight = GetPathWeight(graph, cities);

    }

    public Path() {

    }

    public double GetPathWeight() {
        return pathweight;
    }

    public int GetLength() {
        return length;
    }

    public int[] GetCities() {
        return cities;
    }

    private double GetPathWeight(MatrixGraph graph, int[] path) {
        double sum = 0;
        for (int i = 0; i < path.length - 1; i++) {
            sum += graph.getWeight(path[i], path[i + 1]);
        }
        sum += graph.getWeight(path[path.length - 1], path[0]);
        return sum;
    }
}
