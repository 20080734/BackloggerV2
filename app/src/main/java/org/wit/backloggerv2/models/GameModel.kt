package org.wit.backloggerv2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
//Model for each game in the backlog, only title is necessary, the rest are optional
@Parcelize
data class GameModel(var id: Long = 0,
                     var title: String = "",
                     var description: String = "",
                     var developer: String = "",
                     var publisher: String = "",
                     var releaseDate: String = "",
                     var platform: String = "",
                     var genre: String = "",
                     var metacritic: String = "",
                     var coverArt: String = "") : Parcelable