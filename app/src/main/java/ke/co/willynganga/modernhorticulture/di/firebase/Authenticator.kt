package ke.co.willynganga.modernhorticulture.di.firebase

interface Authenticator {

    fun getCurrentUser(): User?

    fun signUpWithEmailAndPassword(email: String, password: String): String

    fun signInWithEmailAndPassword(email: String, password: String): String

    fun sendResetPasswordEmail(email: String): String

    fun logOut()

}