package data

import kotlinx.serialization.Serializable

@Serializable
enum class Cell {

    /**
     * Player 1.
     */
    RED,

    /**
     * Player 2.
     */
    YELLOW,

    /**
     * Empty cell
     */
    NONE

}