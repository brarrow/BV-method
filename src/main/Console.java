package main;

import main.Algorithms.BVMethod;
import main.Algorithms.Greedy;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Console {

    public static void cycle() {

        Scanner input = new Scanner(System.in);
        String line[];
        Path path = new Path();
        MatrixGraph graph = new MatrixGraph();
        do {
            line = input.nextLine().split(" ");
            switch (line[0]) {
                case "\\h": {
                    printhelp();
                    break;
                }

                case "\\l": {

                }

                case "\\g": {
                    switch (line.length) {
                        case 2: {
                            graph = new MatrixGraph(Integer.parseInt(line[1]));
                            System.out.println("Success!");
                            break;
                        }
                        case 3: {
                            graph = new MatrixGraph(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                            System.out.println("Success!");
                            break;
                        }
                        default: {
                            System.out.println("Unexpected command. Try again!");
                            break;
                        }
                    }
                    break;
                }

                case "\\sw": {
                    graph.showMatrixGraph();
                    break;
                }

                case "\\sp": {
                    for (int arg : path.GetCities()) {
                        System.out.print((arg + 1) + " ");
                    }

                    System.out.println("; Sum is: " + path.GetPathWeight());
                    break;
                }

                case "\\stat": {
                    switch (line.length) {
                        case 3: {
                            int iter = Integer.parseInt(line[2]);
                            int n = Integer.parseInt(line[1]);
                            BVMethod.GetStat(iter, n);
                            break;
                        }
                        default: {
                            BVMethod.GetStat();
                            break;
                        }
                    }
                    break;
                }

                case "\\f": {
                    path = BVMethod.FindPath(graph);
                    System.out.println("Success!");
                    break;
                }
                default: {
                    System.out.println("Unexpected command. Try again!");
                }
            }

        } while (!line[0].equals("\\q"));
    }

    private static void printhelp() {
        System.out.println(
                "\\q \t-- Quit the program\n" +
                        "\\l [path_to_file] \t-- Load graph from file\n" +
                        "\\sw \t-- Show graph in console\n" +
                        "\\sp \t-- Show finded path" +
                        "\\f [first_city] \t-- Find optimal path\n" +
                        "\\g [city_number] [delta]\t-- Generate graph\n" +
                        "\\stat [city_number] [iterations]\t-- Get statistic"
        );
    }
}