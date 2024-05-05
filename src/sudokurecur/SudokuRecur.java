/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokurecur;

/**
 *
 * @author Brayan
 */
public class SudokuRecur {

    private static final int TAMAMAtriz = 9;

    public static void main(String[] args) {
        int[][] sudo = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 0}
        };
        muestraSudoku(sudo);

        // JOptionPane.showMessageDialog(null, "BIENVENIDOS");
        if (analizadorSudoku(sudo)) {
            //JOptionPane.showMessageDialog(null, "bien");
            System.out.println("FUNCIONO");
        } else {
            // JOptionPane.showMessageDialog(null, "MAL");
            System.out.println("ERROR FATAL");
        }

    }

    private static boolean NumeroEnFila(int[][] sudoku, int num, int fila) {
        for (int i = 0; i < TAMAMAtriz; i++) {
            if (sudoku[fila][i] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean NumeroEnColumna(int[][] sudoku, int num, int columna) {
        for (int i = 0; i < TAMAMAtriz; i++) {
            if (sudoku[i][columna] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean NumeroEnCuadrante(int[][] sudoku, int num, int fila, int columna) {
        int localCuadranteFila = fila - fila % 3;
        int localCuadranteColumna = columna - columna % 3;
        for (int i = localCuadranteFila; i < localCuadranteFila + 3; i++) {
            for (int f = localCuadranteColumna; f < localCuadranteColumna + 3; f++) {
                if (sudoku[i][f] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean LugarDelSudoku(int[][] sudoku, int num, int fila, int columna) {
        return !NumeroEnFila(sudoku, num, fila)
                && !NumeroEnColumna(sudoku, num, columna)
                && !NumeroEnCuadrante(sudoku, num, fila, columna);
    }

    private static boolean analizadorSudoku(int[][] sudoku) {
        for (int fila = 0; fila < TAMAMAtriz; fila++) {
            for (int columna = 0; columna < TAMAMAtriz; columna++) {
                if (sudoku[fila][columna] == 0) {
                    for (int numeroAValidar = 1; numeroAValidar <= TAMAMAtriz; numeroAValidar++) {
                        if (LugarDelSudoku(sudoku, numeroAValidar, fila, columna)) {
                            sudoku[fila][columna] = numeroAValidar;
                            if (analizadorSudoku(sudoku)) {
                                return true;
                            } else {
                                sudoku[fila][columna] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void muestraSudoku(int[][] sudo) {

        for (int fila = 0; fila < TAMAMAtriz; fila++) {
            if (fila % 3 == 0 && fila != 0) {
                System.out.println("-----------");
            }
            for (int columna = 0; columna < TAMAMAtriz; columna++) {
                if (columna % 3 == 0 && columna != 0) {
                    System.out.print(" | ");
                }
                System.out.println(sudo[fila][columna]);
            }
            System.out.println();
        }

    }
}
