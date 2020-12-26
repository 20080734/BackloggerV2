package org.wit.backloggerv2.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.backloggerv2.models.GameModel

class MainApp : Application(), AnkoLogger {

    val games = ArrayList<GameModel>()

    override fun onCreate() {
        super.onCreate()
        info("BackloggerV2 started")
        //test entries
        //games.add(GameModel("One", "des1","dev1","pub1","1","plat1","gen1","11","cover1"))
        //games.add(GameModel("Two", "des2","dev2","pub2","2","plat2","gen2","22","cover2"))
        //games.add(GameModel("Three", "des3","dev3","pub3","3","plat3","gen3","33","cover3"))
    }
}