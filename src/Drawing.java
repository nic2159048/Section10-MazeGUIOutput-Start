
/**
 * Drawing reads a file in that is hard coded in start and
 * turns that file's character representation of a maze
 * into a drawing on a JavaFX canvas.
 * 
 * Adapted from the Section 10 code from CS210 Fall 2018.
 * 
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Drawing extends Application {
    // 25 x 25 pixle square
    final int SIZE = 25;
    // Points needed to draw a triangle
    final int TRIANGLE = 3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Process the input file and store it in a 2D array of characters
        char[][] maze = readMaze("PublicTestCases/maze_01.txt");
        primaryStage.setTitle("A-maze-ing!");

        Group root = new Group();

        // Initialized canvas to a default size for demo
        int DEMO = 10;

        // TODO: parameterize so that the canvas is initizlied to the length and
        // width of the maze
        Canvas canvas = new Canvas(DEMO * SIZE, DEMO * SIZE);

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
        gc.setFill(Color.YELLOW);

        // Demo starting points
        int S_POINT = 0; // Start location for square
        int T_POINT = 2; // Start location for triangle

        // Example square drawn
        gc.fillRect(S_POINT * SIZE, S_POINT * SIZE, SIZE, SIZE);

        // Example triangle drawn
        gc.setFill(Color.BLUE);
        // Two arrays that correspond to the points of the triangle to be drawn
        double[] yPoints = new double[] {
                (double) (T_POINT * SIZE + SIZE), (double) SIZE * T_POINT,
                (double) (SIZE * T_POINT + SIZE) };
        double[] xPoints = new double[] { (double) T_POINT * SIZE,
                (double) (SIZE * T_POINT + (SIZE / 2)),
                (double) (SIZE * T_POINT + SIZE) };
        // Could just pass arrays straight into fillPolygon
        gc.fillPolygon(xPoints, yPoints, TRIANGLE);


    }

    /*
     * readMaze accepts a string arguement that is the file name
     * 
     * TODO: Process the file and place its contents in the 2D
     * array Maze. This 2D array will be returned so that you
     * are able to iterate over it when drawing on the Canvas.
     */
    public char[][] readMaze(String fileName) {
        char[][] maze = null;

        return maze;
    }

}
