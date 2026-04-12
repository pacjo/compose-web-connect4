package data

import kotlinx.serialization.Serializable

@Serializable
class Board(
    val rows: Int,
    val cols: Int
) {
    private var board: Array<Array<Cell>> = Array(rows) { Array(cols)  { Cell.NONE } }

    operator fun get(x: Int, y: Int): Cell {
        return board[x][y]
    }

    operator fun set(x: Int, y: Int, value: Cell) {
        board[x][y] = value
    }

    fun copy(): Board {
        val newBoard = Board(rows, cols)
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                newBoard[r, c] = this[r, c]
            }
        }
        return newBoard
    }
}