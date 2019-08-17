package ru.strorin.tictactoe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.strorin.tictactoe.gui.view.FieldLayout
import ru.strorin.tictactoe.models.GameState
import ru.strorin.tictactoe.models.Player

class FieldFragment: Fragment(), FieldView {

    private lateinit var fieldView: FieldLayout
    private lateinit var presenter: FieldPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.field_fragment, container, false)
        fieldView = inflate.findViewById(R.id.field)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.init()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setPresenter(p: FieldPresenter) {
        presenter = p
    }

    override fun createField(xSize: Int, ySize: Int) {
        fieldView.setSize(xSize, ySize) { a: Int, b: Int -> presenter.clickCell(a, b)}
    }

    override fun showState(x: Int, y: Int, state: GameState) {
        fieldView.updateCell(x, y, state)
    }

    override fun showWin(player: Player) {
        Toast.makeText(context, player.name + " WON!!!!!", Toast.LENGTH_SHORT).show()
    }
}