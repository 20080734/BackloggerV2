package org.wit.backloggerv2.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.backloggerv2.helpers.*
import java.util.*

val JSON_FILE = "myBacklog.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<GameModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class GameJSONStore : GameStore, AnkoLogger {

    val context: Context
    var games = mutableListOf<GameModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<GameModel> {
        return games
    }

    override fun create(game: GameModel) {
        game.id = generateRandomId()
        games.add(game)
        serialize()
    }


    override fun update(game: GameModel) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(games, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        games = Gson().fromJson(jsonString, listType)
    }
}