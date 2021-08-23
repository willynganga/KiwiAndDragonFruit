package ke.co.willynganga.modernhorticulture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.willynganga.modernhorticulture.di.firebase.Authenticator
import ke.co.willynganga.modernhorticulture.util.Event
import ke.co.willynganga.modernhorticulture.util.Resource
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticator: Authenticator
) : ViewModel() {

    val currentUser = authenticator.getCurrentUser()

    val signInResponse: MutableLiveData<Event<Resource<String>>> = MutableLiveData()
    val signUpResponse: MutableLiveData<Event<Resource<String>>> = MutableLiveData()
    val resetPasswordResponse: MutableLiveData<Event<Resource<String>>> = MutableLiveData()

    fun signUpWithEmailAndPassword(email: String, password: String) {
        signUpResponse.postValue(Event(Resource.Loading()))
        authenticator.signUpWithEmailAndPassword(email, password).let {
            if (it.startsWith("Account")) {
                signUpResponse.postValue(Event(Resource.Success(it)))
            } else {
                signUpResponse.postValue(Event(Resource.Error(it)))
            }
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String)  {
        signInResponse.postValue(Event(Resource.Loading()))
        authenticator.signInWithEmailAndPassword(email, password).let {
            if (it.contains("successfully")) {
                signInResponse.postValue(Event(Resource.Success(it)))
            } else {
                signInResponse.postValue(Event(Resource.Error(it)))
            }
        }
    }

    fun sendPasswordResetEmail(email: String) {
        resetPasswordResponse.postValue(Event(Resource.Loading()))
        authenticator.sendResetPasswordEmail(email).let {
            if (it.startsWith("Please")) {
                resetPasswordResponse.postValue(Event(Resource.Success(it)))
            } else {
                resetPasswordResponse.postValue(Event(Resource.Error(it)))
            }
        }
    }

    fun logOut() = authenticator.logOut()

}