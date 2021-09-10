package ke.co.willynganga.modernhorticulture.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.willynganga.modernhorticulture.model.FruitDescription
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.DESCRIPTION_FIELD
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.DISEASE_CONTROL_MEASURES_FIELD
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.ECOLOGICAL_REQUIREMENTS_FIELD
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.FRUITS_COLLECTION
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.HOW_TO_PLANT_FIELD
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.IMG_FIELD
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.SOIL_MANAGEMENT_FIELD
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.USERS_COLLECTION
import javax.inject.Inject

@HiltViewModel
class FirestoreViewModel @Inject constructor(
    currentUser: FirebaseUser,
    private val db: FirebaseFirestore
) : ViewModel() {

    val currentUser = currentUser

    val username: MutableLiveData<String> = MutableLiveData()
    val fruitDescription: MutableLiveData<FruitDescription> = MutableLiveData()

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

    fun getFruitDescription(name: String) {
        db.collection(FRUITS_COLLECTION).document(name)
            .get()
            .addOnSuccessListener { snapshot ->
                fruitDescription.postValue(
                    FruitDescription(
                        diseaseControlMeasures = snapshot.getString(DISEASE_CONTROL_MEASURES_FIELD)!!,
                        ecologicalRequirements = snapshot.getString(ECOLOGICAL_REQUIREMENTS_FIELD)!!,
                        howToPlant = snapshot.getString(HOW_TO_PLANT_FIELD)!!,
                        soilManagement = snapshot.getString(SOIL_MANAGEMENT_FIELD)!!,
                        imgSrc = snapshot.getString(IMG_FIELD)!!,
                        description = snapshot.getString(DESCRIPTION_FIELD)!!
                    )
                )
            }
    }

}