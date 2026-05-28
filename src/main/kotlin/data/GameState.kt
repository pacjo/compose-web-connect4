package data

import kotlinx.browser.localStorage
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class GameState(
    val config: GameConfig = GameConfig(),
    val board: Board = Board(config.rows, config.cols),
    val status: GameStatus = GameStatus.InProgress("Player 1"),
    val currentPlayer: Int = 1,
    private val moveHistory: MutableList<Int> = mutableListOf()
) {
    fun dropPiece(col: Int): GameState {
        // don't allow moves after win/draw
        if (status !is GameStatus.InProgress)
            return this

        // find first empty row or return early (so no action takes place)
        val row = (config.rows - 1 downTo 0)
            .firstOrNull { board[it, col] == Cell.NONE } ?: return this

        // update history
        moveHistory.add(col)

        val newBoard = board.copy()
        val playerCell = if (currentPlayer == 1) Cell.RED else Cell.YELLOW

        // place last move
        newBoard[row, col] = playerCell

        // and check conditions
        val hasWon = checkWin(newBoard, row, col, playerCell)
        val isFull = (0 until config.rows).all { r ->
            (0 until config.cols).all { c ->
                newBoard[r, c] != Cell.NONE
            }
        }

        val newPlayer = 3 - currentPlayer       // magic trick for going between numbers 1 and 2
        val newStatus = when {
            hasWon -> GameStatus.Win("Player $currentPlayer")
            isFull -> GameStatus.Draw
            else -> GameStatus.InProgress("Player $newPlayer")
        }

        return copy(
            board = newBoard,
            status = newStatus,
            currentPlayer = newPlayer
        )
    }

    // unused as requested during interview
    fun undoMove(): GameState {
        // this should also check for an empty list, since .removeLast() can throw an exception
        // but interviewer after mentioning it said there's no need to add that
        // and we've finished a couple of minutes before the end of the interview time slot

        // find the row in which the last piece landed
        val col = moveHistory.removeLast()
        val newBoard = board.copy()
        val rowToRemove = (0 until config.rows).first { row ->
            newBoard[row, col] != Cell.NONE
        }

        // undo by setting to empty
        newBoard[rowToRemove, col] = Cell.NONE

        // and return (don't forget to change player)
        return copy(
            board = newBoard,
            status = status,
            currentPlayer = 3 - currentPlayer
        )
    }

    private fun checkWin(
        board: Board,
        row: Int,
        col: Int,
        targetCell: Cell
    ): Boolean {
        // direction pairs
        val directions = listOf(
            0 to 1,   // horizontal
            1 to 0,   // vertical
            1 to 1,   // diagonal \
            1 to -1   // diagonal /
        )

        // count number of same subsequent cells in both directions starting from current (thus +1)
        return directions.any { (directionRow, directionCol) ->
            val count =
                countDirection(board, row, col, directionRow, directionCol, targetCell) +
                countDirection(board, row, col, -directionRow, -directionCol, targetCell) +
                1

            count >= config.winCondition
        }
    }

    private fun countDirection(
        board: Board,
        row: Int,
        col: Int,
        directionRow: Int,
        directionCol: Int,
        targetCell: Cell
    ): Int {
        var count = 0
        var currentRow = row + directionRow
        var currentCol = col + directionCol

        // while both coordinates are in range and cell pointed by them is same as target cell
        while (
            currentRow in 0 until config.rows &&
            currentCol in 0 until config.cols &&
            board[currentRow, currentCol] == targetCell
        ) {
            // increase count and move to next
            count++
            currentRow += directionRow
            currentCol += directionCol
        }

        return count
    }

    fun save() {
        localStorage.setItem(LS_ITEM, Json.encodeToString(this))
    }

    companion object {
        const val LS_ITEM = "connect4"

        fun load(): GameState? = try {
            localStorage.getItem(LS_ITEM)?.let {
                Json.decodeFromString(it)
            }
        } catch (_: Exception) {
            null
        }
    }
}