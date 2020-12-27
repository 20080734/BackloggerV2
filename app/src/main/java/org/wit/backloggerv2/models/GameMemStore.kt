package org.wit.backloggerv2.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class GameMemStore : GameStore, AnkoLogger{
    val games = ArrayList<GameModel>()

    override fun findAll(): List<GameModel> {
        return games
    }

    override fun create(game: GameModel) {
        games.add(game)
        games.forEach{ info("${it}") }
    }

}