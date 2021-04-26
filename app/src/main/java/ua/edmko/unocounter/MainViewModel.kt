package ua.edmko.unocounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val navigationManager: NavigationManager): BaseViewModel(navigationManager){
init{
    viewModelScope.launch {
        delay(1000L)
        navigationManager.navigate(NavigationDirections.homeRoot)
    }
}
}