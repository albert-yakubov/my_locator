package com.stepasha.buildinglocator.util

import android.util.Log
import android.util.Patterns
import com.stepasha.buildinglocator.RegisterActivity
import com.stepasha.buildinglocator.model.NewUser
import com.stepasha.buildinglocator.model.RegisterResponse
import com.stepasha.buildinglocator.retrofit.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterValidation {

    companion object {
        const val PASSWORD_MIN_LENGTH = 4
        const val PASSWORD_MAX_LENGTH = 8
        const val NAME_AND_USERNAME_MIN_LENGTH = 2
        const val NAME_AND_USERNAME_MAX_LENTH = 8
        const val EMAIL_FORMAT_ERROR_TEXT = "Invalid email format."
        const val BLANK_ERROR_TEXT = "Field can't be empty."
        fun characterLengthErrorText(
            maxLength: Int,
            minLength: Int,
            tooLong: Boolean = false
        ): String {

            return when (tooLong) {
                true -> "Must be at less than $maxLength characters"
                else -> "Must be at least $minLength characters long."
            }

        }
    }
    fun validate(name: String?, minLength: Int, maxLength: Int): ValidationWithMessage {

        return when {

            name.isNullOrBlank() -> ValidationWithMessage(
                BLANK_ERROR_TEXT,
                false
            )
            name.length < minLength -> ValidationWithMessage(
                characterLengthErrorText(
                    maxLength,
                    minLength,
                    false
                ),
                false
            )
            name.length > maxLength -> ValidationWithMessage(
                characterLengthErrorText(
                    maxLength,
                    minLength,
                    true
                ),
                false
            )
            else -> ValidationWithMessage(null, true)
        }
    }
    fun validate(email: String?): ValidationWithMessage {

        return when {
            email.isNullOrBlank() -> ValidationWithMessage(
                BLANK_ERROR_TEXT,
                false
            )
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ValidationWithMessage(
                EMAIL_FORMAT_ERROR_TEXT,
                false
            )
            else -> ValidationWithMessage(
                BLANK_ERROR_TEXT,
                false
            )
        }
    }
    fun createUserForAPI(newUser: NewUser) {
        val call: Call<RegisterResponse> = ServiceBuilder.create().createUser(newUser)

        call.enqueue(object: Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.i("OnFailure", t.message)
            }

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                RegisterActivity.token = response.body()!!.token
                Log.i("onRespone", RegisterActivity.token)
            }
        })
    }


}
data class ValidationWithMessage(val errorText: String?, val valid: Boolean) {
    fun isValid() : Boolean {
        return valid
    }
    fun getValidationErrorText() : String {
        return errorText.toString()
    }
}