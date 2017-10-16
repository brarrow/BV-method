package main;

import main.Algorithms.Greedy;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Console {

    public static void cycle() {

        Scanner input = new Scanner(System.in);
        String line[];
        int path[] = new int[0];
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
                    for (int arg : path) {
                        System.out.print(arg + 1 + " ");
                    }
                    double sum = 0;
                    for (int i = 0; i < path.length - 1; i++) {
                        sum += graph.getWeight(path[i], path[i + 1]);
                    }
                    sum += graph.getWeight(path[path.length - 1], path[0]);
                    System.out.println("; Sum is: " + sum);
                    break;
                }

                case "\\f": {
                    if (line.length == 2) {
                        path = Greedy.FindLoopPathFrom(graph, Integer.parseInt(line[1]) - 1);
                    } else path = Greedy.FindLoopPath(graph);
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
                        "\\f -[first_city] \t-- Find optimal path\n" +
                        "\\g -[city_number] -[delta]\t-- Generate graph\n"
        );
    }
}