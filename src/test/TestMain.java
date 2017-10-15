package test;

import java.io.FileNotFoundException;

/**
 * Created by lelay on 08.10.17.
 */
public class TestMain {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Testing GraphReader: ");
        TestGraphReader tgr = new TestGraphReader();

        System.out.print("Test #1: ");
        System.out.println(tgr.test());

        System.out.print("Test #2: ");
        System.out.println(tgr.test2());
    }
}
