package com.kassim.test.viewmodels

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.kassim.test.model.User

class ViewModel : BaseObservable() {

    private val user: User

    private val successMessage = "Login was successful"
    private val errorMessage = "Email or Password not valid"

    @Bindable
    var toastMessage: String? = null

        private set(toastMessage) {
            field = toastMessage
            notifyPropertyChanged(BR.toastMessage)
        }

    var userEmail: String
        @Bindable
        get() = user.email!!
        set(email) {
            user.email = email
//            notifyPropertyChanged(BR.userEmail)
        }

    var userPassword: String
        @Bindable
        get() = user.password!!
        set(password) {
            user.password = password
//            notifyPropertyChanged(BR.userPassword)
        }

    val isInputDataValid: Boolean
        get() = !TextUtils.isEmpty(userEmail) && Patterns.EMAIL_ADDRESS.matcher(userEmail).matches() && userPassword.length > 5

    init {
        user = User("", "")
    }

    fun onLoginClicked() {
        if (isInputDataValid) {
            toastMessage = successMessage
        } else {
            toastMessage = errorMessage
        }
    }

}