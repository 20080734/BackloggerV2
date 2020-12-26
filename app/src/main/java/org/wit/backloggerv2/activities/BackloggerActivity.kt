package org.wit.backloggerv2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_backlogger.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.backloggerv2.models.GameModel
import org.wit.backloggerv2.R
import org.wit.backloggerv2.main.MainApp

class BackloggerActivity : AppCompatActivity(), AnkoLogger {

    var game = GameModel()
    lateinit var app : MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backlogger)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            game.description = gameDescription.text.toString()
            game.developer = gameDeveloper.text.toString()
            game.publisher = gamePublisher.text.toString()
            game.releaseDate = gameReleaseDate.text.toString()
            game.platform = gamePlatform.text.toString()
            game.genre = gameGenre.text.toString()
            game.metacritic = gameMetacritic.text.toString()
            game.coverArt = gameCoverArt.text.toString()

            if (game.title.isNotEmpty()) {
                app.games.add(game.copy())
                info("add Button Pressed: ${game}")
                for (i in app.games.indices) {
                    info("Game[$i]:${app.games[i]}")
                }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please enter all fields")
            }
        }
    }
}