package com.example.instaapp.ui.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instaapp.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun login(code: String) {
        // Access Token almak için API çağrısı yapılacak ve User nesnesi güncellenecek
        // val user = apiService.getUser(code)
        // _user.value = user
    }
}
