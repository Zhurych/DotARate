package com.ez.dotarate.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.database.Game
import com.ez.dotarate.database.Hero
import com.ez.dotarate.model.UserResponse
import com.ez.dotarate.model.WinsAndLosses
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import com.ez.dotarate.model.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ProfileSearchViewModel
@Inject constructor(
    application: Application,
    private val userRepository: UserRepositoryImpl
) : AndroidViewModel(application) {

    val liveUser = MutableLiveData<UserResponse>()
    val liveWinsAndLosses = MutableLiveData<WinsAndLosses>()

    val isNeedPositionToStartGames = ObservableBoolean(false)
    val isNeedPositionToStartMph = ObservableBoolean(false)

    fun fetchUser(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val response = userRepository.getUserResponse(id32)
                if (response.isSuccessful) {
                    liveUser.postValue(response.body())
                } else {

                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }

    fun fetchWinsAndLosses(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val response = userRepository.getWinsAndLosses(id32)
                if (response.isSuccessful) {
                    liveWinsAndLosses.postValue(response.body())
                } else {

                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }
}