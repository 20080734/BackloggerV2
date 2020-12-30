package org.wit.backloggerv2.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_game.view.*
import org.wit.backloggerv2.R
import org.wit.backloggerv2.helpers.readImageFromPath
import org.wit.backloggerv2.models.GameModel


interface GameListener {
    fun onGameClick(game: GameModel)
}

class GameAdapter constructor(private var games: List<GameModel>, private val listener: GameListener) : RecyclerView.Adapter<GameAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.card_game,
                    parent,
                    false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val game = games[holder.adapterPosition]
        holder.bind(game, listener)
    }

    override fun getItemCount(): Int = games.size

    //what the user sees per game card
    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: GameModel,  listener : GameListener) {
            itemView.gameTitle.text = game.title
            itemView.gameCoverArt.setImageBitmap(readImageFromPath(itemView.context, game.coverArt))
            itemView.setOnClickListener { listener.onGameClick(game) }
        }
    }




}