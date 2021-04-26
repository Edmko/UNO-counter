package ua.edmko.unocounter.base

import androidx.lifecycle.ViewModel
import ua.edmko.unocounter.navigation.NavigationManager

abstract class BaseViewModel(private val navigationManager: NavigationManager): ViewModel() {
}