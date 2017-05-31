package info.overrideandroid.connect4.board;

import info.overrideandroid.connect4.rules.Player;

/**
 * Created by Rahul on 30/05/17.
 */

public class BoardLogic {
    /**
     * Possible outcomes
     */
    public enum Outcome {
        NOTHING, DRAW, PLAYER1_WINS, PLAYER2_WINS;
    }
    /** flag to mark draw */
    private boolean draw;

    /** Reference to player win */
    private int cellValue;

    /**
     * Reference to a main grid
     */
    private final int[][] grid;

    /**
     * number of columns in the grid
     */
    private final int numCols;

    /**
     * number of rows in the grid
     */
    private final int numRows;

    /**
     * Initialise members
     *
     * @param _grid
     */
    public BoardLogic(int[][] _grid) {
        grid = _grid;
        numCols = _grid.length;
        numRows = _grid[0].length;

    }

    public Outcome checkWin() {
        draw = true;
        cellValue = 0;
        if (horizontalCheck() || verticalCheck() ||
                ascendingDiagonalCheck() || descendingDiagonalCheck()) {
            return cellValue == Player.PLAYER1 ? Outcome.PLAYER1_WINS : Outcome.PLAYER2_WINS;
        }
        // nobody won, return draw if it is, nothing if it's not
        return draw ? Outcome.DRAW : Outcome.NOTHING;
    }


    private boolean horizontalCheck() {
        // horizontalCheck
        for (int j = 0; j < numRows - 3; j++) {
            for (int i = 0; i < numCols; i++) {
                cellValue = grid[i][j];
                if (cellValue == 0) draw = false;
                if (cellValue != 0 && grid[i][j + 1] == cellValue && grid[i][j + 2] == cellValue && grid[i][j + 3] == cellValue) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verticalCheck() {
        // verticalCheck
        for (int i = 0; i < numCols - 3; i++) {
            for (int j = 0; j < this.numRows; j++) {
                cellValue = grid[i][j];
                if (cellValue == 0) draw = false;
                if (cellValue != 0 && grid[i + 1][j] == cellValue && grid[i + 2][j] == cellValue && grid[i + 3][j] == cellValue) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ascendingDiagonalCheck() {
        // ascendingDiagonalCheck
        for (int i = 3; i < numCols; i++) {
            for (int j = 0; j < numRows - 3; j++) {
                cellValue = grid[i][j];
                if (cellValue == 0) draw = false;
                if (cellValue != 0 && grid[i - 1][j + 1] == cellValue && grid[i - 2][j + 2] == cellValue && grid[i - 3][j + 3] == cellValue)
                    return true;
            }
        }
        return false;
    }

    private boolean descendingDiagonalCheck() {
        // descendingDiagonalCheck
        for (int i = 3; i < numCols; i++) {
            for (int j = 3; j < numRows; j++) {
                cellValue = grid[i][j];
                if (cellValue == 0) draw = false;
                if (cellValue != 0 && grid[i - 1][j - 1] == cellValue && grid[i - 2][j - 2] == cellValue && grid[i - 3][j - 3] == cellValue)
                    return true;
            }
        }
        return false;
    }

}
