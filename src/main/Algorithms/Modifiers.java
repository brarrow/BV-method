package main.Algorithms;

import main.MatrixGraph;
import main.Path;

/**
 * @author lelay
 */
public class Modifiers {
    static public Path smoothing(Path path, int indexOfCity1, int indexOfCity2){
        Path modPath = new Path();
        modPath.copyPath(path);

        //swapping 2 cities to check if there's a better path to travel
        int temp = modPath.getCity(indexOfCity1);
        modPath.setCity(indexOfCity1, modPath.getCity(indexOfCity2));
        modPath.setCity(indexOfCity2, temp);

        if(modPath.getCity(0) != modPath.getCity(modPath.GetLength() - 1)){
            modPath.setCity(modPath.GetLength() - 1, modPath.getCity(0));
        }

        return modPath;
    }

    static public Path transposition(Path path, int startCity, int lastCity){
        Path modPath = new Path();
        modPath.copyPath(path);

        int temp;
        for(int i = startCity; i < lastCity; ++i){
            temp = modPath.getCity(i);
            modPath.setCity(i, modPath.getCity(i+1));
            modPath.setCity(i+1, temp);
        }

        if(modPath.getCity(0) != modPath.getCity(modPath.GetLength() - 1)){
            modPath.setCity(modPath.GetLength() - 1, modPath.getCity(0));
        }

        return modPath;
    }

    static public Path inversionTransition(Path path, int indexOfCity1, int indexOfCity2){
        Path modPath = new Path();
        modPath.copyPath(path);

        int temp;
        while(++indexOfCity1 <= --indexOfCity2){
            temp = modPath.getCity(indexOfCity1);
            modPath.setCity(indexOfCity1, modPath.getCity(indexOfCity2));
            modPath.setCity(indexOfCity2, temp);
        }

        if(modPath.getCity(0) != modPath.getCity(modPath.GetLength() - 1)){
            modPath.setCity(modPath.GetLength() - 1, modPath.getCity(0));
        }

        return modPath;
    }

    static public Path inversionTransport(Path path, int indexOfCity1, int indexOfCity2){
        return inversionTransition(path, --indexOfCity1, ++indexOfCity2);
    }
}
