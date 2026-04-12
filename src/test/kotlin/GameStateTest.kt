package data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GameStateTest {

    @Test
    fun testDropPiece() {
        var state = GameState()
        state = state.dropPiece(0)

        // given default size of 6 rows and 7 cols,
        // row 5 is the bottom row for default board,
        // and as such should have the red piece
        // we've dropped above
        assertEquals(Cell.RED, state.board[5, 0])
        assertEquals(2, state.currentPlayer)
    }

    @Test
    fun testHorizontalWin() {
        var state = GameState()
        val moves = listOf(
            0, 0, // R on 0, Y on 0
            1, 1, // R on 1, Y on 1
            2, 2, // R on 2, Y on 2
            3     // R on 3 -> horizontal win
        )
        moves.forEach { state = state.dropPiece(it) }

        assertTrue(state.status is GameStatus.Win)
        assertEquals("Player 1", (state.status).playerName)
    }

    @Test
    fun testVerticalWin() {
        var state = GameState()
        val moves = listOf(
            0, 1, // R on 0, Y on 1
            0, 1, // R on 0, Y on 1
            0, 1, // R on 0, Y on 1
            0     // R on 0 -> vertical win
        )
        moves.forEach { state = state.dropPiece(it) }

        assertTrue(state.status is GameStatus.Win)
        assertEquals("Player 1", (state.status).playerName)
    }

    @Test
    fun testDiagonalWin() {
        var state = GameState()
        val moves = listOf(
            0, // R [5,0]
            1, // Y [5,1]
            1, // R [4,1]
            2, // Y [5,2]
            0, // R [4,0]
            2, // Y [4,2]
            2, // R [3,2]
            3, // Y [5,3]
            3, // R [4,3]
            3, // Y [3,3]
            3  // R [2,3] -> diagonal win
        )
        moves.forEach { state = state.dropPiece(it) }

        assertTrue(state.status is GameStatus.Win)
    }

    @Test
    fun testDraw() {
        val boardSize = 3       // this way we won't wil by accident
        var state = GameState(GameConfig(boardSize, boardSize))
        // once per row
        repeat(boardSize) {
            (0..<boardSize).forEach { col ->
                state = state.dropPiece(col)
            }
        }

        assertTrue(state.status is GameStatus.Draw)
    }

    @Test
    fun testIgnoreFullColumnDrop() {
        var state = GameState()
        // fill single column
        repeat(6) {
            state = state.dropPiece(0)
        }

        val stateAfterFull = state.dropPiece(0)
        // state should be the same as updating it was skipped
        assertEquals(state, stateAfterFull)
    }
}