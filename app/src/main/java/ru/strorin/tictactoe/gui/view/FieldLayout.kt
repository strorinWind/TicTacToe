package ru.strorin.tictactoe.gui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import ru.strorin.tictactoe.models.GameState
import ru.strorin.tictactoe.R


class FieldLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GridLayout(context, attrs, defStyleAttr) {

    private var x_size: Int = 0
    private var y_size: Int = 0


    fun setSize(x: Int, y: Int, listener: (x: Int, y: Int) -> Unit ) {
        columnCount = y
        removeAllViews()
        x_size = x
        y_size = y

        for (i in 0 until x*y) {
            val t = ImageView(context)
            t.layoutParams = LinearLayout.LayoutParams(
                100,
                100
            )
            t.background = context.getDrawable(R.drawable.border)
            t.id = i
            t.setOnClickListener { v -> listener(v.id.div(y_size), v.id.rem(y_size)) }
            addView(t)
        }
    }

    fun updateCell(x: Int, y: Int, state: GameState) {
        when (state) {
            GameState.Empty -> {
                findViewById<ImageView>(x*y_size + y).setImageDrawable(null)
            }
            GameState.X -> {
                findViewById<ImageView>(x*y_size + y).setImageResource(R.drawable.x_sign)
            }
            GameState.O -> {
                findViewById<ImageView>(x*y_size + y).setImageResource(R.drawable.o_sign)
            }
        }
    }
}