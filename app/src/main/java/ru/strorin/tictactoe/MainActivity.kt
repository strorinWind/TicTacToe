package ru.strorin.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.strorin.tictactoe.gui.view.FieldLayout



class MainActivity : AppCompatActivity() {

    private val presenter: FieldPresenter = FieldPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<FieldLayout>(R.id.field).setSize(7, 5)

        val fragmentTransaction = supportFragmentManager.beginTransaction();
        val fragment = FieldFragment()
        fragmentTransaction.add(R.id.main_container, fragment)
        fragmentTransaction.commit()

        presenter.attach(fragment)
    }
}
