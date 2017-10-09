package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by lelay on 08.10.17.
 */
public class GraphReader {

    private String path;

    public GraphReader(String[] args){
        /*command line arguments:
        1) the first is the path to a file
        */
        this.path = args[0];
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }

    //isBiDirectional parameter means if it's a bi-directional graph or not
    public double[][] readFile(boolean isBiDirectional) throws FileNotFoundException {
        // the first number in the file is a number of city in graph, so then we can create a matrix with NxN cages
        // in the text file you have an vertices written like "1 2 150" as "(i, j, weight)"
        Scanner scan = new Scanner(new File(this.path));

        int cityNumber = scan.nextInt();
        double[][] weightMatrix = new double[cityNumber][cityNumber];

        int i = 0;
        int j = 0;
        while(scan.hasNext()){
            i = scan.nextInt();
            j = scan.nextInt();
            weightMatrix[i][j] = scan.nextInt();

            if (isBiDirectional){
                weightMatrix[j][i] = weightMatrix[i][j];
            }
        }

        scan.close();
        return weightMatrix; //returns the weightMatrix
    }

    //default method (one-directional graph)
    public double[][] readFile() throws FileNotFoundException {
        return readFile(false);
    }
}
