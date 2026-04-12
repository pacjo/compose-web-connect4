package data

import kotlinx.serialization.Serializable

@Serializable
sealed class GameStatus {

    @Serializable
    class InProgress(val playerName: String) : GameStatus()

    @Serializable
    object Draw : GameStatus()

    @Serializable
    class Win(val playerName: String) : GameStatus()
}