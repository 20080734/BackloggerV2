package org.wit.backloggerv2.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_game_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.backloggerv2.R
import org.wit.backloggerv2.main.MainApp
import org.wit.backloggerv2.models.GameModel

//Activity for the main display page
class GameListActivity : AppCompatActivity(), GameListener {

    private var gridLayoutManager: GridLayoutManager? = null
    lateinit var app: MainApp

    //creates the tiles in a grid style
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)
        app = application as MainApp

        //toolbar.title = title     //this causes the app to crash when running on mobile
        //setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = GameAdapter(app.games.findAll(), this)
        gridLayoutManager = GridLayoutManager(applicationContext,3,LinearLayoutManager.VERTICAL,false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        loadGames()
    }

    //decides toolbar type
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //adds toolbar controls
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<BackloggerActivity>(0)
            R.id.item_info -> startActivity<InfoActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    //go to the edit page on game click
    override fun onGameClick(game: GameModel) {
        startActivityForResult(intentFor<BackloggerActivity>().putExtra("game_edit", game), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadGames()
        super.onActivityResult(requestCode, resultCode, data)
    }

    //displays and loads the current backlog of games
    private fun loadGames() {
        showGames(app.games.findAll())
    }
    fun showGames (games: List<GameModel>) {
        recyclerView.adapter = GameAdapter(games, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

