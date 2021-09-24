package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[][] matrix = new int[3][3];

        System.out.println("---------");
        int i = 0;
        while (i < 3) {
            System.out.println("|       |");
            i++;
        }
        System.out.println("---------");

       // System.out.println(Game(matrix));

        boolean ok = true;
        boolean turn = true;
        int size = 0;

        while (ok) {
            System.out.println("Enter the coordinates: ");
            try
            {
                int num1 = Integer.parseInt(sc.next());
                int num2 = Integer.parseInt(sc.next());

                if (num1 < 1 || num1 > 3 || num2 < 1 || num2 > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    boolean correct = FreeXY(num1, num2, matrix);

                    if (correct) {
                        AddX(num1, num2, matrix, turn);
                        size += 1;
                        turn = !turn;
                        String game = Game(matrix, size);
                        if (!game.equals("Game not finished")
                                && !game.equals(" ")) {
                            System.out.println(game);
                            ok = false;
                        }
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            }
            catch(NumberFormatException e)
            {
                //If number is not integer,you wil get exception and exception message will be printed
                System.out.println("You should enter numbers!");
            }

        }
    }

    static boolean FreeXY (int x, int y, int[][] matrix) {
        if (matrix[x - 1][y - 1] == 0) {
            return true;
        } else {
            return false;
        }
    }

    static void AddX (int x, int y, int[][] matrix, boolean turn) {
        if (turn == true) {
            matrix[x - 1][y - 1] = 1;
        } else {
            matrix[x - 1][y - 1] = -1;
        }
        System.out.println("---------");
        char[][] realMatrix = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 1) {
                    realMatrix[i][j] = 'X';
                } else if (matrix[i][j] == -1) {
                    realMatrix[i][j] = 'O';
                } else {
                    realMatrix[i][j] = ' ';
                }
            }
        }
        for (char[] row : realMatrix) {
            System.out.println("| " + row[0] + " " + row[1]
            + " " + row[2] + " |");
        }
        System.out.println("---------");
    }

    static int FreePos(int[][] m) {
        for (int[] row: m) {
            for (int pos: row) {
                if (pos == 0) {
                    return 1;
                }
            }
        }
        return 0;
    }

    static int win(int a, int b, int c) {
        if ((a == b) && (b == c)) {
            if (a != 0) {
                return a;
            }
        }
        return 0;
    }

    static int WhoWin(int[][] k) {

        int X = 0;
        int O = 0;
        for (int i = 0; i < 3; ++i) {
            X = Math.max(win(k[i][0], k[i][1], k[i][2]), X);
            O = Math.min(win(k[i][0], k[i][1], k[i][2]), O);
            X = Math.max(win(k[0][i], k[1][i], k[2][i]), X);
            O = Math.min(win(k[0][i], k[1][i], k[2][i]), O);
        }
        X = Math.max(win(k[0][0], k[1][1], k[2][2]), X);
        X = Math.max(win(k[0][2], k[1][1], k[2][0]), X);
        O = Math.min(win(k[0][0], k[1][1], k[2][2]), O);
        O = Math.min(win(k[0][2], k[1][1], k[2][0]), O);
        if ((X > 0) && (O < 0)) {
            return -2;
        } else if ((X == 0) && (O == 0)) {
            return 0;
        } else if (X > 0) {
            return 1;
        }
        return -1;
    }

//    static int CorrectNum(int[][] m) {
//        int X = 0;
//        int O = 0;
//        for (int[] row: m) {
//            for (int pos: row) {
//                if (pos == 1) {
//                    ++X;
//                } else if (pos == 0) {
//                    ++O;
//                }
//            }
//        }
//
//        if (Math.abs(X - O) < 2) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }

    static String Game(int[][] m, int size) {
        int WW = WhoWin(m);
//        int CN = CorrectNum(m);
        int FP = FreePos(m);
//        if ((WW == -2) || (CN == 0)) {
//            return "Impossible";
         if ((FP == 1) && (WW == 0)) {
             return "Game not finished";
         } else if (WW == 1) {
            return "X wins";
        } else if (WW == -1) {
            return "O wins";
        } else if (WW == 0 && size == 9){
            return "Draw";
        } else {
             return " ";
         }
    }

//    static int f(char d) {
//        if (d == 'O') {
//            return -1;
//        } else if (d == 'X') {
//            return 1;
//        } else {
//            return 0;
//        }
//    }
}

