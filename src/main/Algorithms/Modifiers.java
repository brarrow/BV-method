package main.Algorithms;

import main.MatrixGraph;
import main.Path;

import java.util.Vector;

/**
 * @author lelay
 */
public class Modifiers {
    static public Path ApplyAllAndChooseBest(MatrixGraph graph, Path path, int indexOfCity1, int indexOfCity2) {
        //int[] results = new int[10];
        Vector<Path> paths = new Vector<Path>();
        paths.add(smoothing(graph, path, indexOfCity1, indexOfCity2));
        paths.add(transposition(graph, path, indexOfCity1, indexOfCity2));
        paths.add(inversionTransition(graph, path, indexOfCity1, indexOfCity2));
        paths.add(inversionTransport(graph, path, indexOfCity1, indexOfCity2));

        Path bestPath = path;

        for (Path el : paths) {
            if (bestPath.GetPathWeight() > el.GetPathWeight()) {
                bestPath = el;
            }
        }
        return bestPath;
    }

    static public Path smoothing(MatrixGraph graph, Path path, int indexOfCity1, int indexOfCity2) {
        Path modPath = new Path();
        modPath.copyPath(path);

        //swapping 2 cities to check if there's a better path to travel
        int temp = modPath.getCity(indexOfCity1);
        modPath.setCity(indexOfCity1, modPath.getCity(indexOfCity2));
        modPath.setCity(indexOfCity2, temp);
        modPath = new Path(graph, modPath.GetCities());

        return modPath;
    }

    static public Path transposition(MatrixGraph graph, Path path, int startCity, int lastCity) {
        Path modPath = new Path();
        modPath.copyPath(path);

        int temp;
        for (int i = startCity; i < lastCity; ++i) {
            temp = modPath.getCity(i);
            modPath.setCity(i, modPath.getCity(i + 1));
            modPath.setCity(i + 1, temp);
        }
        modPath = new Path(graph, modPath.GetCities());

        return modPath;
    }

    static public Path inversionTransition(MatrixGraph graph, Path path, int indexOfCity1, int indexOfCity2) {
        Path modPath = new Path();
        modPath.copyPath(path);

        int temp;
        while (++indexOfCity1 <= --indexOfCity2) {
            temp = modPath.getCity(indexOfCity1);
            modPath.setCity(indexOfCity1, modPath.getCity(indexOfCity2));
            modPath.setCity(indexOfCity2, temp);
        }
        modPath = new Path(graph, modPath.GetCities());

        return modPath;
    }

    static public Path inversionTransport(MatrixGraph graph, Path path, int indexOfCity1, int indexOfCity2) {
        return inversionTransition(graph, path, --indexOfCity1, ++indexOfCity2);
    }
}
