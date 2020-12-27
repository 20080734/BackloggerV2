package org.wit.backloggerv2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_backlogger.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.backloggerv2.models.GameModel
import org.wit.backloggerv2.R
import org.wit.backloggerv2.main.MainApp

class BackloggerActivity : AppCompatActivity(), AnkoLogger {

    var game = GameModel()
    var edit = false
    lateinit var app : MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backlogger)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Backloggerv2 Activity started..")


        if (intent.hasExtra("game_edit")) {
            edit = true
            game = intent.extras?.getParcelable<GameModel>("game_edit")!!
            gameTitle.setText(game.title)
            gameDescription.setText(game.description)
            gameDeveloper.setText(game.developer)
            gamePublisher.setText(game.publisher)
            gameReleaseDate.setText(game.releaseDate)
            gamePlatform.setText(game.platform)
            gameGenre.setText(game.genre)
            gameMetacritic.setText(game.metacritic)
            gameCoverArt.setText(game.coverArt)
            btnAdd.setText(R.string.save_changes)
        }


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

            if (game.title.isEmpty()) {
                toast(R.string.enter_game_title)
            }
            else
            {
                if (edit)
                {
                    app.games.update(game.copy())
                }
                else
                {
                    app.games.create(game.copy())
                }
            }
            info("add Button Pressed: ${game}")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}