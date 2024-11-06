package com.example.app.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.version.CanAccessToApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(
    private val canAccessToApp: CanAccessToApp ) : ViewModel() {

    private val _blockVersion = MutableStateFlow(false)
    val blockVersion: StateFlow<Boolean> = _blockVersion

    init {
        checkUserVersion()
    }

    private fun checkUserVersion() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                canAccessToApp()
            }
            _blockVersion.value = !result

        }
    }
}



/* Función para llamar al caso de uso y actualizar el estado
private fun checkForUpdates() {
    viewModelScope.launch {
        _blockVersion.value = checkForUpdatesUseCase()
    }
} */



