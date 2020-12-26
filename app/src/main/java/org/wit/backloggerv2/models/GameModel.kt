package org.wit.backloggerv2.models

data class GameModel(var title: String = "",
                     var description: String = "",
                     var developer: String = "",
                     var publisher: String = "",
                     var releaseDate: String = "",
                     var platform: String = "",
                     var genre: String = "",
                     var metacritic: String = "",
                     var coverArt: String = "")