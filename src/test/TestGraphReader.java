package test;

import main.GraphReader;
import main.SpecialValues;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Created by lelay on 08.10.17.
 */
public class TestGraphReader implements SpecialValues {
    public boolean test() throws FileNotFoundException {
        GraphReader gr = new GraphReader(new String[]{ //so f**king bad realisation only for mine local machine, but ...
                "/home/lelay/Programming/git_projects/BV-method/src/test/test_graph_1.txt"
        });

        double[][] matrix = gr.readFile(true);

        double[][] myMatrix = new double[3][3];
        myMatrix[0][1] = 100;
        myMatrix[1][0] = 100;
        myMatrix[1][2] = 322;
        myMatrix[2][1] = 322;
        myMatrix[2][0] = 228;
        myMatrix[0][2] = 228;

        return Arrays.deepEquals(matrix, myMatrix);
    }

    public boolean test2() throws FileNotFoundException {
        GraphReader gr = new GraphReader(new String[]{
                "/home/lelay/Programming/git_projects/BV-method/src/test/test_graph_2.txt"
        });

        double[][] matrix = gr.readFileLineByLine();

        double[][] myMatrix = new double[3][3];

        myMatrix[0][0] = INF;
        myMatrix[0][1] = 2;
        myMatrix[0][2] = 4;

        myMatrix[1][0] = INF;
        myMatrix[1][1] = INF;
        myMatrix[1][2] = 8;

        myMatrix[2][0] = 322;
        myMatrix[2][1] = 228;
        myMatrix[2][2] = INF;

        return Arrays.deepEquals(matrix, myMatrix);
    }
}
