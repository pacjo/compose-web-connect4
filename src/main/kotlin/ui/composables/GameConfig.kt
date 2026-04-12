package ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import data.GameConfig
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.NumberInput
import org.jetbrains.compose.web.dom.Text
import ui.style.AppStyle

@Composable
fun GameConfig(
    config: GameConfig,
    onApply: (GameConfig) -> Unit
) {
    var rows by remember { mutableStateOf(config.rows) }
    var cols by remember { mutableStateOf(config.cols) }
    var winCondition by remember { mutableStateOf(config.winCondition) }

    Div({ classes(AppStyle.config) }) {
        H3 { Text("Game Settings") }

        ConfigEntry(
            text = "Rows: ",
            value = rows,
            onValueChange = { rows = it }
        )

        ConfigEntry(
            text = "Columns: ",
            value = cols,
            onValueChange = { cols = it }
        )

        ConfigEntry(
            text = "Win Condition: ",
            value = winCondition,
            onValueChange = { winCondition = it }
        )

        Button({
            onClick {
                onApply(GameConfig(rows, cols, winCondition))
            }
        }) {
            Text("Apply")
        }
    }
}

@Composable
private fun ConfigEntry(text: String, value: Int, onValueChange: (Int) -> Unit) {
    Label {
        Text(text)
        NumberInput(
            value = value,

            // in some cases it would make sense to make this per value, but since here
            // all of them are related we can limit it all the same (alternatively we
            // could do IntRange-s as a companion object in e.g. GameConfig)
            min = 4,
            max = 15
        ) {
            onInput {
                val newValue = it.value?.toInt()
                if (newValue != null) {
                    onValueChange(newValue)
                }
            }
        }
    }
}