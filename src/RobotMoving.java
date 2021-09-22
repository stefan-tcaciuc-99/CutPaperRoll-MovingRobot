//Stefan Tcaciuc R00192770
import java.util.Scanner;

public class RobotMoving {


    private static void printDouble(double[][] T) {         //print the double matrix
        for (int row = 0; row < T.length; row++) {
            System.out.print("| ");
            for (int col = 0; col < T.length; col++) {
                System.out.print(String.format("%.1f", T[col][row]) + " | ");
            }
            System.out.println();
        }
    }

    private static void printString(String[][] S) {     //print the string matrix
        for (int row = 0; row < S.length; row++) {
            System.out.print("| ");
            for (int col = 0; col < S.length; col++) {
                System.out.print(S[col][row] + " | ");
            }
            System.out.println();
        }
    }

    private static double min(double x, double y, double z) {       //check from 3 direction which is the cheapest one and return it
        if (x <= y && x <= z) {
            return x;
        } else if (y <= x && y <= z) {
            return y;
        } else {
            return z;
        }
    }

    private static void getDirection(String[][] S, int x, int y) {      //recursively backtrack trough the array of directions to reach the origin point from the destination
        String direction = "";
        if (S[x][y].equals("↓")) {
            direction += " ↓ ";
            getDirection(S, x, y - 1);
        } else if (S[x][y].equals("→")) {
            direction += " → ";
            getDirection(S, x - 1, y);
        } else if (S[x][y].equals("↘")) {
            direction += " ↘ ";
            getDirection(S, x - 1, y - 1);
        }
        System.out.print(direction);
    }

    private static void matrix(int n, double[] cost) {
        double[][] T = new double[n][n];            //matrix array to store the move and the cost associated with it
        String[][] S = new String[n][n];            //matrix array to store the direction of the move
        T[0][0] = 0;
        S[0][0] = "•";
        for (int j = 1; j < T.length; j++) {
            T[0][j] = T[0][j - 1] + cost[1];        //set cost for position (0,1) to (0,T.length)
            S[0][j] = "↓";                         //set the direction for position (0,1) to (0,T.length)

        }
        for (int i = 1; i < T.length; i++) {
            T[i][0] = T[i - 1][0] + cost[0];  //set cost for position (1,0) to (T.length,0)
            S[i][0] = "→";                    //set direction for position (1,0) to (T.length,0)

            for (int j = 1; j < T.length; j++) {
                double min = min(T[i - 1][j] + cost[0], T[i][j - 1] + cost[1], T[i - 1][j - 1] + cost[2]);      //check from 3 possible directions to move and pick cheapest one
                String direction = "";
                T[i][j] = min;                                      //move in the cheapest direction
                if (min == T[i - 1][j] + cost[0]) {
                    direction = "→";
                }
                if (min == T[i][j - 1] + cost[1]) {                 //check to see what direction we moved and set the direction for the array of directions
                    direction = "↓";

                }
                if (min == T[i - 1][j - 1] + cost[2]) {
                    direction = "↘";
                }
                S[i][j] = direction;                //set the direction

            }
        }
        System.out.println("MINIMUM COST = €" + String.format("%.1f", T[n - 1][n - 1]));
        printDouble(T);
        System.out.println("\nDIRECTION");
        printString(S);
        System.out.println("\nSHORTEST PATH DIRECTION");
        getDirection(S, S.length - 1, S.length - 1);
        System.out.println();
    }


    public static void main(String[] args) {
        double[] cost1 = {1.1, 1.3, 2.5};
        double[] cost2 = {1.5, 1.2, 2.3};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of matrix. Where matrix is >= 1: ");
        int n = scanner.nextInt();
        System.out.println("\nCOST 1");
        matrix(n, cost1);
        System.out.println("=================================\nCOST 2");
        matrix(n, cost2);


    }
}
