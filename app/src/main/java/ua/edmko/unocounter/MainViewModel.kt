package ua.edmko.unocounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.edmko.navigation.NavigationDirections
import ua.edmko.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val navigationManager: NavigationManager) :
    ViewModel() {
    init {
        viewModelScope.launch {
            delay(3000L)
            navigationManager.navigate(NavigationDirections.gameSetting)
        }
    }
}