package test;

import main.GraphReader;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Created by lelay on 08.10.17.
 */
public class TestGraphReader {
    public boolean test() throws FileNotFoundException {
        GraphReader gr = new GraphReader(new String[]{ //so f**king bad realisation only for mine local machine, but ...
                "/home/lelay/Programming/git_projects/BV-method/src/test/test_graph_1.txt"
        });

        double[][] matrix = gr.readFile(true);

        double[][] myMatrix = new double[3][3];
        myMatrix[0][1] = 100;
        myMatrix[1][2] = 322;
        myMatrix[2][0] = 228;

        return Arrays.deepEquals(matrix, myMatrix);
    }
}
