package com.ez.dotarate.model.repository

import com.ez.dotarate.network.ServerApi
import javax.inject.Inject

class PandaScoreRepositoryImpl
@Inject constructor(private val api: ServerApi) : PandaScoreRepository {
}