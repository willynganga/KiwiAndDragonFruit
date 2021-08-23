package ke.co.willynganga.modernhorticulture.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import ke.co.willynganga.modernhorticulture.di.firebase.Authenticator
import ke.co.willynganga.modernhorticulture.di.firebase.User

class FirebaseAuthenticatorRepository(
    private val auth: FirebaseAuth
) : Authenticator {

    override fun getCurrentUser(): User {
        val currentUser = auth.currentUser
        currentUser.let {
            return User(
                it?.email,
                currentUser?.uid
            )
        }
    }

    override fun signUpWithEmailAndPassword(email: String, password: String): String {
        var msg = ""
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    msg = "Account created successfully!"
                }
            }
            .addOnFailureListener { e ->
                msg = e.message.toString()
            }
        return msg
    }

    override fun signInWithEmailAndPassword(email: String, password: String): String {
        var result: String = ""
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FAR", "signInWithEmailAndPassword: Signed in successfully!")
                    result = "Signed in successfully!"
                }
            }
            .addOnFailureListener { e ->
                result = e.message.toString()
            }
        return result
    }


    override fun sendResetPasswordEmail(email: String): String {
        var result = ""
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result = "Please check your email!"
                }
            }
            .addOnFailureListener { e ->
                result = e.message.toString()
            }

        return result
    }

    override fun logOut() {
        auth.signOut()
    }

}