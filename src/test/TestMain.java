package test;

import java.io.FileNotFoundException;

/**
 * Created by lelay on 08.10.17.
 */
public class TestMain {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Testing GraphReader: ");
        TestGraphReader tgr = new TestGraphReader();
        System.out.println(tgr.test());
    }
}
