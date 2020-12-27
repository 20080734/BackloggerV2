package org.wit.backloggerv2.models

interface GameStore {
    fun findAll(): List<GameModel>
    fun create(game: GameModel)
}

