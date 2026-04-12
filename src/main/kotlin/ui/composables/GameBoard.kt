package ui.composables

import androidx.compose.runtime.Composable
import data.Cell
import data.GameState
import org.jetbrains.compose.web.dom.Div
import ui.style.AppStyle

@Composable
fun GameBoard(state: GameState, onDrop: (Int) -> Unit) {
    Div({
        classes(AppStyle.board)
        style {
            property("--cols", state.config.cols)
            property("--rows", state.config.rows)
        }
    }) {
        repeat(state.config.cols) { col ->
            Div({ classes(AppStyle.column) }) {
                // match ordering with one in GameState
                for (row in state.config.rows - 1 downTo 0) {
                    BoardCell(
                        state.board[row, col],
                        onClick = { onDrop(col) }
                    )
                }
            }
        }
    }
}

@Composable
fun BoardCell(value: Cell, onClick: () -> Unit) {
    Div({
        classes(AppStyle.cell)

        // only drop if empty
        if (value == Cell.NONE)
            onClick { onClick() }
    }) {
        when (value) {
            Cell.RED -> Div({ classes(AppStyle.piece, AppStyle.cellRed) })
            Cell.YELLOW -> Div({ classes(AppStyle.piece, AppStyle.cellYellow) })
            Cell.NONE -> { }
        }
    }
}