package data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame

class BoardTest {

    @Test
    fun testSetAndGetCells() {
        val board = Board(6, 7)

        board[0, 0] = Cell.RED
        board[5, 6] = Cell.YELLOW
        board[2, 3] = Cell.RED

        assertEquals(Cell.RED, board[0, 0])
        assertEquals(Cell.YELLOW, board[5, 6])
        assertEquals(Cell.RED, board[2, 3])
    }

    @Test
    fun testCopyCreatesIndependentBoard() {
        val original = Board(6, 7)
        original[0, 0] = Cell.RED
        original[1, 1] = Cell.YELLOW

        val copy = original.copy()

        // quick verify not same
        assertNotSame(original, copy, "Copy should return a new Board instance")

        // check if data copied successfully
        assertEquals(Cell.RED, copy[0, 0], "Copy should retain original values")
        assertEquals(Cell.YELLOW, copy[1, 1], "Copy should retain original values")

        // make change in copy, make sure it's not present in original
        copy[0, 0] = Cell.YELLOW // change existing
        copy[5, 5] = Cell.RED    // and add new

        assertEquals(Cell.RED, original[0, 0], "Original board should not be affected by copy modification")
        assertEquals(Cell.NONE, original[5, 5], "Original board should not be affected by copy modification")
    }
}