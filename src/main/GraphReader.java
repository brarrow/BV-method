package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by lelay on 08.10.17.
 */
public class GraphReader implements SpecialValues {

    private String path;

    public GraphReader(String path){
        this.path = path;
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
            weightMatrix[i][j] = scan.nextDouble();

            if (isBiDirectional){
                weightMatrix[j][i] = weightMatrix[i][j];
            }
        }

        scan.close();
        return weightMatrix; //returns the weightMatrix
    }

    public double[][] readFileLineByLine() throws FileNotFoundException {
        Scanner scan = new Scanner(new File(this.path));

        int cityNumber = scan.nextInt();
        double[][] weightMatrix = new double[cityNumber][cityNumber];

        for(int i = 0; i < cityNumber; i++){
            for(int j = 0; j < cityNumber; j++){
                if(scan.hasNextDouble()){
                    weightMatrix[i][j] = scan.nextDouble();
                }
                else if(scan.hasNextLine()){
                    String temp = scan.next();
                    if(temp.equals("inf") || temp.equals("INF") || temp.equals("Inf")){
                        weightMatrix[i][j] = INF;
                    }
                    else{
                        System.out.println("\nUndefined value at the [" + i + ", " +
                                j + "] position at the graph matrix file: " + this.path);
                    }
                }
            }
        }

        scan.close();
        return weightMatrix;
    }
    //default method (one-directional graph)
    public double[][] readFile() throws FileNotFoundException {
        return readFile(false);
    }
}
