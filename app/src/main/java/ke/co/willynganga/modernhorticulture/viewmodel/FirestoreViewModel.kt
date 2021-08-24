package ke.co.willynganga.modernhorticulture.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.USERS_COLLECTION
import javax.inject.Inject

@HiltViewModel
class FirestoreViewModel @Inject constructor(
    currentUser: FirebaseUser,
    private val db: FirebaseFirestore
): ViewModel() {

    val currentUser = currentUser

    val username: MutableLiveData<String> = MutableLiveData()

    fun saveUserName(uid: String, name: String) {
        val user = hashMapOf("name" to name)
        db.collection(USERS_COLLECTION)
            .document(uid)
            .set(user)
    }

    fun getUserName(uid: String) {
        db.collection(USERS_COLLECTION).document(uid)
            .get()
            .addOnSuccessListener { snapshot ->
                snapshot.getString("name").let {
                    username.postValue(it)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error fetching name", exception)
            }
    }

}