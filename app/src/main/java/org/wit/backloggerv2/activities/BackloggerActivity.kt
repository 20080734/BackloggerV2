package org.wit.backloggerv2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_backlogger.*
import kotlinx.android.synthetic.main.card_game.view.*
//import kotlinx.android.synthetic.main.activity_gameview.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.backloggerv2.models.GameModel
import org.wit.backloggerv2.R
import org.wit.backloggerv2.helpers.readImage
import org.wit.backloggerv2.helpers.readImageFromPath
import org.wit.backloggerv2.helpers.showImagePicker
import org.wit.backloggerv2.main.MainApp


//Activity for the create/edit function
class BackloggerActivity : AppCompatActivity(), AnkoLogger {

    var game = GameModel()
    val IMAGE_REQUEST = 1
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backlogger)
        app = application as MainApp
        var edit = false
        //var view = false              //left over from test branch

        //toolbarAdd.title = title      //this causes the app to crash when running on mobile
        //setSupportActionBar(toolbarAdd)
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
            gameCoverArt.setImageBitmap(readImageFromPath(this, game.coverArt)) //this should display image on edit, does not work for some reason
            if (game.coverArt != null) {
                chooseImage.setText(R.string.change_game_coverart)
            }

            btnAdd.setText(R.string.save_changes)
        }

        //attempt at individual views
        /*if (intent.hasExtra("game_view")) {
            view = true

            game = intent.extras?.getParcelable<GameModel>("game_view")!!
            gameTitleView.text = game.title
            gameDescriptionView.setText(game.description)
            gameDeveloperView.setText(game.developer)
            gamePublisherView.setText(game.publisher)
            gameReleaseDateView.setText(game.releaseDate)
            gamePlatformView.setText(game.platform)
            gameGenreView.setText(game.genre)
            gameCoverArtView.setImageBitmap(readImageFromPath(this, game.coverArt)) //this should display image on edit, does not work for some reason

        }*/

        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            game.description = gameDescription.text.toString()
            game.developer = gameDeveloper.text.toString()
            game.publisher = gamePublisher.text.toString()
            game.releaseDate = gameReleaseDate.text.toString()
            game.platform = gamePlatform.text.toString()
            game.genre = gameGenre.text.toString()
            game.metacritic = gameMetacritic.text.toString()

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
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
    }

    //decides toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //controls for toolbar options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.games.delete(game)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    game.coverArt = data.getData().toString()
                    gameCoverArt.setImageBitmap(readImage(this, resultCode, data))
                }
            }
        }
    }
}