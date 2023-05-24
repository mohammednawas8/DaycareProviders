package com.loc.daycareproviders.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.loc.daycareproviders.presentation.navigation.Feature
import com.loc.daycareproviders.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val auth: FirebaseAuth,
) : ViewModel() {

    private val _navigate = MutableSharedFlow<String>(replay = 1)
    val navigate = _navigate.asSharedFlow()

    init {
        viewModelScope.launch {

            val firebaseUser = auth.currentUser
            if (firebaseUser == null) {
                _navigate.emit(Feature.Authentication.route)
            } else {
                _navigate.emit(Feature.Home.route)
            }
        }
    }

}