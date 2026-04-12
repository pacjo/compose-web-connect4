import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import data.GameState
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import ui.composables.GameBoard
import ui.style.AppStyle

fun main() {
    renderComposable(rootElementId = "root") {
        App()
    }
}

@Composable
fun App() {
    // try loading or fail to default
    var gameState by remember { mutableStateOf(GameState.load() ?: GameState()) }

    // persistence
    LaunchedEffect(gameState) {
        gameState.save()
    }

    Style(AppStyle)

    Div({ classes(AppStyle.container) }) {
        H1 { Text("Connect4") }

        GameBoard(gameState) { col -> gameState = gameState.dropPiece(col) }

        Div {
            Button({ onClick { gameState = GameState(gameState.config) } }) {
                Text("New Game")
            }
        }
    }
}