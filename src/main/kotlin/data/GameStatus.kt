package data

import kotlinx.serialization.Serializable

@Serializable
sealed class GameStatus {

    @Serializable
    class InProgress : GameStatus()

    @Serializable
    class Draw : GameStatus()

    @Serializable
    class Win(val playerName: String) : GameStatus()
}