package com.ez.dotarate.model.repository

import com.ez.dotarate.model.UpcomingGame

interface PandaScoreRepository {
    fun fetchUpcomingMatches(page: Int, loadSize: Int): ArrayList<UpcomingGame>
}