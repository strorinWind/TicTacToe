package ru.strorin.tictactoe

import android.util.Log
import ru.strorin.tictactoe.models.GameState
import ru.strorin.tictactoe.models.Player

const val ROWS = 7
const val COLUMNS = 5

const val NUM_TO_WIN = 3


class FieldPresenter {

    private lateinit var view: FieldView
    private lateinit var fieldMap: Array<GameState>
    private lateinit var currentPlayer: Player

    fun attach(v: FieldView) {
        view = v
        v.setPresenter(this)
    }

    fun init(){
        view.createField(ROWS, COLUMNS)
        fieldMap = Array(ROWS* COLUMNS) { _ -> GameState.Empty}
        currentPlayer = Player.X
    }

    fun clickCell(x: Int, y: Int) {
        val id = x * COLUMNS + y
        Log.e("LALLA", "$x $y")
        if (fieldMap[id] != GameState.Empty) {
            return
        }
        val p = currentPlayer
        when (currentPlayer) {
            Player.X -> {
                fieldMap[id] = GameState.X
                view.showState(x, y, GameState.X)
                currentPlayer = Player.O
            }
            Player.O -> {
                fieldMap[id] = GameState.O
                view.showState(x, y, GameState.O)
                currentPlayer = Player.X
            }
        }
        if (checkIfWin(x, y)) {
            view.showWin(p)
        }
    }

    private fun checkIfWin(x: Int, y: Int) : Boolean {
        var num = 1
        //Up
        var step = 1
        val current = x * COLUMNS + y
        while (current >= COLUMNS * step
            && num < NUM_TO_WIN
            && fieldMap[current - step * COLUMNS] == fieldMap[current]
        ) {
            step++
            num++
        }
        //down
        step = 1
        while (current + COLUMNS * step < fieldMap.size
            && num < NUM_TO_WIN
            && fieldMap[current + step * COLUMNS] == fieldMap[current]
        ) {
            step++
            num++
        }
        if (num >= NUM_TO_WIN) {
            return true
        }
        //left
        step = 1
        num = 1
        while (current > step
            && num < NUM_TO_WIN
            && fieldMap[current - step] == fieldMap[current]
        ) {
            step++
            num++
        }
        //right
        step = 1
        while (current + step < fieldMap.size
            && num < NUM_TO_WIN
            && fieldMap[current + step] == fieldMap[current]
        ) {
            step++
            num++
        }
        if (num >= NUM_TO_WIN) {
            return true
        }
        //left-up - right-down
        num = 1
        step = 1
        while (current >= (COLUMNS + 1) * step
            && num < NUM_TO_WIN
            && fieldMap[current - step * (COLUMNS + 1)] == fieldMap[current]
        ) {
            step++
            num++
        }
        step = 1
        while (current + (COLUMNS + 1) * step < fieldMap.size
            && num < NUM_TO_WIN
            && fieldMap[current + step * (COLUMNS + 1)] == fieldMap[current]
        ) {
            step++
            num++
        }
        if (num >= NUM_TO_WIN) {
            return true
        }
        //up-right - left-down
        num = 1
        step = 1
        while (current >= (COLUMNS - 1) * step
            && num < NUM_TO_WIN
            && fieldMap[current - (COLUMNS - 1) * step] == fieldMap[current]
        ) {
            num++
            step++
        }
        step = 1
        while (current + (COLUMNS - 1) * step < fieldMap.size
            && num < NUM_TO_WIN
            && fieldMap[current + (COLUMNS - 1) * step] == fieldMap[current]
        ) {
            num++
            step++
        }
        if (num >= NUM_TO_WIN) {
            return true
        }
        return false
    }
}