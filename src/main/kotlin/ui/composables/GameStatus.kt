package ui.composables

import androidx.compose.runtime.Composable
import data.GameState
import data.GameStatus
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import ui.style.AppStyle

@Composable
fun GameStatus(state: GameState) {
    Div({ classes(AppStyle.status) }) {
        val statusText = when (val status = state.status) {
            is GameStatus.InProgress -> "${status.playerName}'s turn"
            is GameStatus.Draw -> "Draw!"
            is GameStatus.Win -> "${status.playerName} wins!"
        }

        H2 { Text(statusText) }
    }
}