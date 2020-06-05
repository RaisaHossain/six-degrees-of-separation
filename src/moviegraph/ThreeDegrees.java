package moviegraph;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main program:
 * -prompts user for the name of an input file
 * -Reads the file contents into a graph data structure.
 * -Prints the graph data structure.
 * -Prompts the user for two names to try connecting.
 * Performs the search and either prints the shortest
 * chain linking the two names in a chain of maximum
 * length three, or prints a message that reports no chain was found.
 * -If either the first or second name is the empty string
 * (the user hit enter giving no name), then the program should end.
 * -If either name is not found in the graph, then the program should
 * print a message to that effect, and request two new names for the next search.
 *
 * @author Raisa Hossain
 */
public class ThreeDegrees {
    /**
     * Main function:
     * @param args
     * @throws FileNotFoundException if file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enter movie data filename: ");
        Scanner input = new Scanner(System.in);
        String filename;
        filename = input.nextLine();
        MovieGraph G = new MovieGraph(filename);
        System.out.println("Displaying MovieGraph...");
        for (String v : G.vertices()) {
            System.out.println((v + ": "));
            for (String w : G.adjacentTo(v)) {
                System.out.println(w + " ");
            }
            System.out.println();
        }
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            System.out.println("Enter first actor's name (two case-senstive words): ");
            String src = input.nextLine();
            if(src.isEmpty()) {
                System.exit(0);
                break;
            }
            System.out.println("Enter second actor's name(two case-sensitive words): ");
            String actor = input.nextLine();
            if(actor.isEmpty()) {
                System.exit(0);
                break;            
            }
            if(!G.hasVertex(src)) {
                System.out.println(src + " is not known in the " + filename);
                continue;
            }
            if(!G.hasVertex(actor)) {
                System.out.println(actor + " is not known in the " + filename);
                continue;
            }
            PathFinder pathFinder = new PathFinder(G, src);
            if (pathFinder.hasPathTo(actor) && pathFinder.distanceTo(actor) <= 3 && pathFinder.distanceTo(actor) != 0) {
                ArrayList<String> arr = new ArrayList<>();
                for (String v : pathFinder.pathTo(actor)) {
                    arr.add(v);
                }
                System.out.println(arr.get(0) + " was in " + arr.get(1));
                int idx = 2;
                while (idx < arr.size() - 1) {
                    System.out.println("with " + arr.get(idx) + " who was in " + arr.get(idx + 1));
                    idx += 2;
                }
                System.out.println("with " + arr.get(arr.size() - 1) + ".");
            }
            if(pathFinder.distanceTo(actor) == 0) {
                System.out.println("Silly! They are in their own movie!");
            }
            else if(pathFinder.distanceTo(actor) > 3) {
                System.out.println("No path less than or equal to a 3-hop distance exists between " + src +
                        " and " + actor);
            }
            else if(!pathFinder.hasPathTo(actor)) {
                System.out.println(actor + " is not known in the " + filename);
            }
        }
    }
}
