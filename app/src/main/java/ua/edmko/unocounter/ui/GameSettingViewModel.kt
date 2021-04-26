package ua.edmko.unocounter.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class GameSettingViewModel @Inject constructor(private val navigationManager: NavigationManager): BaseViewModel(navigationManager) {

}