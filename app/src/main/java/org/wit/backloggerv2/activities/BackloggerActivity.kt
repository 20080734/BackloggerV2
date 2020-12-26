package org.wit.backloggerv2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_backlogger.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.backloggerv2.models.GameModel
import org.wit.backloggerv2.R

class BackloggerActivity : AppCompatActivity(), AnkoLogger {

    var game = GameModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backlogger)
        info("Backlogger Activity started..")

        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            if (game.title.isNotEmpty()) {
                info("add Button Pressed: $game")
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}