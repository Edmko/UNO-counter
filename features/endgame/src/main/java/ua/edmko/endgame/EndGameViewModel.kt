package ua.edmko.endgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EndGameViewModel @Inject constructor(endGameNavigator: EndGameNavigator) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(2000L)
            endGameNavigator.back()
        }
    }
}