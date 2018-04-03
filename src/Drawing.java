
/**
 * Drawing reads a file in that is hard coded in start and
 * turns that file's character representation of a maze
 * into a drawing on a JavaFX canvas.
 * 
 * Adapted from the Section 10 code from CS210 Fall 2018.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Drawing extends Application {
    // 25 x 25 pixel square
    final int SIZE = 25;
    // Points needed to draw a triangle
    final int TRIANGLE = 3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Process the input file and store it in a 2D array of characters
        char[][] maze = readMaze("PublicTestCases/maze_02.txt");

        primaryStage.setTitle("A-maze-ing!");

        Group root = new Group();



        // TODO: parameterize so that the canvas is initialized to the length and
        // width of the maze
        Canvas canvas = new Canvas(maze.length * SIZE, maze[1].length * SIZE);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        initScreenMaze(gc, maze);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /*
     * TODO: Iterate over the 2D array maze and draw the shape that corresponds
     * to the character. You will have to parameterize the draw shape method
     * calls below. Nested loops are acceptable.
     */
    public void initScreenMaze(GraphicsContext gc, char[][] maze) {
        // default is yellow because it is a corn maze


        // Demo starting points
        int S_POINT = 0; // Start location for square
        // Start location for triangle

        // Example square drawn
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == '*') {
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(j * SIZE, i * SIZE, SIZE, SIZE);
                }
                else if (maze[i][j] == 'S') {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(j * SIZE, i * SIZE, SIZE, SIZE);
                } else if (maze[i][j] == 'E') {
                    gc.setFill(Color.GREEN);
                    int T_POINT = i;
                    int Y_POINT = j;
                    // Two arrays that correspond to the points of the triangle
                    // to be drawn
                    double[] yPoints = new double[] {
                            (double) (T_POINT * SIZE + SIZE),
                            (double) SIZE * T_POINT,
                            (double) (SIZE * T_POINT + SIZE) };
                    double[] xPoints = new double[] { (double) Y_POINT * SIZE,
                            (double) (SIZE * Y_POINT + (SIZE / 2)),
                            (double) (SIZE * Y_POINT + SIZE) };
                    // Could just pass arrays straight into fillPolygon
                    gc.fillPolygon(xPoints, yPoints, TRIANGLE);
                    // gc.fillRect(j * SIZE, i * SIZE, SIZE, SIZE);

                }
            }
        }


        // Example triangle drawn



    }

    /*
     * readMaze accepts a string argument that is the file name
     * 
     * TODO: Process the file and place its contents in the 2D
     * array Maze. This 2D array will be returned so that you
     * are able to iterate over it when drawing on the Canvas.
     */
    public char[][] readMaze(String fileName) {

        // Initializes file and scanner
        File file = new File(fileName);
        Scanner in = null;
        // error handling for missing file
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found");
            System.exit(1);
        }
        String rc = in.nextLine();
        String[] nums = rc.split(" ");
        int rows = Integer.valueOf(nums[0]);
        int cols = Integer.valueOf(nums[0]);

        char[][] maze = new char[rows][cols];
        String hold = null;
        char[] chars = null;
        for (int i = 0; i < rows; i++) {
            hold = in.nextLine();
            chars = hold.toCharArray();
            for (int j = 0; j < cols; j++) {
                maze[i][j] = chars[j];
            }
        }
        return maze;
    }

}
