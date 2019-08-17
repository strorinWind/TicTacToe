package ru.strorin.tictactoe

import ru.strorin.tictactoe.models.GameState
import ru.strorin.tictactoe.models.Player

interface FieldView {

    fun setPresenter(p: FieldPresenter)

    fun createField(xSize: Int, ySize: Int)

    fun showState(x: Int, y: Int, state: GameState)

    fun showWin(player: Player)
}