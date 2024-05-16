package com.welkinwits.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.provider.Settings
import android.text.TextUtils
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.firebase.messaging.FirebaseMessaging
import java.io.File
import java.io.IOException
import java.io.InputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object Util {

    data class RGB(
        var red: Int? = (0..256).random(),
        var green: Int? = (0..256).random(),
        var blue: Int? = (0..256).random()
    )

    var colorMap = HashMap<Int, RGB>()

    fun loadJSONFromAsset(context: Context, fileName: String): String? {
        var json: String? = null
        json = try {
            val inputStream: InputStream =
                context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            return null
        }
        return json
    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String = Settings.Secure.getString(
        context.contentResolver, Settings.Secure.ANDROID_ID
    )

    suspend fun getFCMToken() = suspendCoroutine<String?> { continuation ->
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(task.result)
            } else {
                continuation.resume(null)
            }
        }
    }

    fun File.toBase64(): String? {
        val result: String?
        inputStream().use { inputStream ->
            val sourceBytes = inputStream.readBytes()
            result = Base64.encodeToString(sourceBytes, Base64.DEFAULT)
        }

        return result
    }

    fun generateRandomColor(position: Int): Int {
        if (!colorMap.containsKey(position)) {
            colorMap[position] = RGB()
        }
        val baseColor: Int = Color.WHITE
        val baseRed: Int = Color.red(baseColor)
        val baseGreen: Int = Color.green(baseColor)
        val baseBlue: Int = Color.blue(baseColor)
        val red: Int = (baseRed + colorMap[position]?.red!!) / 2
        val green: Int = (baseGreen + colorMap[position]?.green!!) / 2
        val blue: Int = (baseBlue + colorMap[position]?.blue!!) / 2
        return Color.rgb(red, green, blue)
    }

    fun navigateToWhatsApp(context: Context, number: String?) {
        try {
            val url = "https://api.whatsapp.com/send?phone=${number}"
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                ).setPackage("com.whatsapp")
            )
        } catch (e: Exception) {
            Toast.makeText(context, "Whatsapp app not installed in your device", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun <T, K, R> LiveData<T>.combineWith(
        liveData: LiveData<K>,
        block: (T?, K?) -> R
    ): LiveData<R> {
        val result = MediatorLiveData<R>()
        result.addSource(this) {
            result.value = block(this.value, liveData.value)
        }
        result.addSource(liveData) {
            result.value = block(this.value, liveData.value)
        }
        return result
    }
}