package org.wit.backloggerv2.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
class GameMemStore : GameStore, AnkoLogger{
    val games = ArrayList<GameModel>()

    override fun findAll(): List<GameModel> {
        return games
    }

    override fun create(game: GameModel) {
        games.add(game)
        games.forEach{ info("${it}") }
    }

    override fun update(game: GameModel) {
        var foundGame: GameModel? = games.find { g -> g.id == game.id }
        if (foundGame != null) {
            foundGame.title = game.title
            foundGame.description = game.description
            foundGame.developer = game.developer
            foundGame.publisher = game.publisher
            foundGame.releaseDate = game.releaseDate
            foundGame.platform = game.platform
            foundGame.genre = game.genre
            foundGame.metacritic = game.metacritic
            //foundGame.coverArt = game.coverArt
            logAll()
        }
    }

    fun logAll() {
        games.forEach { info("${it}") }
    }
}