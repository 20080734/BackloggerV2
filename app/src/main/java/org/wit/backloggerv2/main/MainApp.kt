package org.wit.backloggerv2.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.backloggerv2.models.GameJSONStore
//import org.wit.backloggerv2.models.GameMemStore
//import org.wit.backloggerv2.models.GameModel
import org.wit.backloggerv2.models.GameStore

class MainApp : Application(), AnkoLogger {

    lateinit var games: GameStore

    override fun onCreate() {
        super.onCreate()
        games = GameJSONStore(applicationContext)
        info("BackloggerV2 started")

    }
}