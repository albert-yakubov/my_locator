package com.stepasha.buildinglocator

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stepasha.buildinglocator.RegisterActivity.Companion.token
import com.stepasha.buildinglocator.model.*
import com.stepasha.buildinglocator.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

//access_token: "e9163369-2fb7-484b-852e-64ae9c407182"
//token_type: "bearer"
//expires_in: 2760
//scope: "read write trust"
    companion object{
        var successfulLogin: Boolean = false
val url = "https://ay-mylocator.herokuapp.com/login"

       var content_type = "application/x-www-form-urlencoded"
      //var content_type = "application/json"
    const val CLIENT_ID = "lambda-client"
    const val CLIENT_SECRET = "lambda-secret"


    var authString = "$CLIENT_ID:$CLIENT_SECRET"
    var encodedAuthString: String = Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)
    var auth = "Basic $encodedAuthString"
        var grant_type = "password"
        var type: String = ""
        var experes: Int = 0
        var scope: String = ""
        var userId:Int = 0
    var client_id = "lambda-client"
    var client_secret = "lambda-secret"
    lateinit var username: String
    lateinit var password: String

    }

    private var validatedUsername: Boolean = false
    private var validatedPassword: Boolean = false
    private var error: Boolean? = false
    private lateinit var authString: String

    //Making these variables global since they're probably gonna be needed when checking the database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //Move to the list activity if username and password are good
        btn_login.setOnClickListener {

            validateUsername()
            validatePassword()
           // getUser()
            login()
           // startActivity(Intent(this, MainActivity::class.java))
        }

        //Move to the register activity when the register button is clicked on
        btn_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    //Checks to see if the entered username is okay or not.
    private fun validateUsername(): Boolean {
        //Gets the text from the username text input layout
       username = text_input_username.editText?.text.toString().trim()

        if (username.isEmpty()) {
            text_input_username.error = "Field can't be empty"
            validatedUsername = false
            return false
        } else if (username.length < 4) {
            text_input_username.error = "Username should be at least four characters"
            return false
        } else if (username.length > 12) {
            text_input_username.error = "Username can't be more than 12 characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_username.error = null
            text_input_username.isErrorEnabled = false
            validatedUsername = true
            return true
        }
    }
    //Checks to see if the entered password is okay or not.
    private fun validatePassword(): Boolean {
        //Gets the text from the password text input layout
        password = text_input_password.editText?.text.toString().trim()

        if (password.isEmpty()) {
            text_input_password.error = "Field can't be empty"
            validatedPassword = false
            return false
        } else if (password.length < 4) {
            text_input_password.error = "Password should be at least four characters"
            return false
        } else if (password.length > 12) {
            text_input_password.error = "Password can't be more than 12 characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_password.error = null
            text_input_password.isErrorEnabled = false
            validatedPassword = true
            return true
        }
    }
   /* var client: OAuth2Client =
        OAuth2Client.Builder(username, password, "lambda-client", "lambda-secret", "https://ay-mylocator.herokuapp.com/login").build()
    var response: OAuthResponse = client.requestAccessToken()*/

   /* if (response.isSuccessful) {
        val accessToken: String = response.getAccessToken()
        val refreshToken: String = response.getRefreshToken()
    } else
    {
        val error: OAuthError = response.getOAuthError()
        val errorMsg: String = error.getError()
    }

    response.getCode() // HTTP Status code
}*/

    fun login(){


        val call: Call<ResponseBody> = ServiceBuilder.create()
            .login( auth, content_type, username, password )

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("Login:", "OnFailure ${t.message}")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful) {
                    Log.i("Login", "Success ${response.body()}")
                    token = response.body()!!.access_token

                    successfulLogin = true

                }
                else{
                    Log.i("Login", "Failure ${response.body()}")


                    successfulLogin = false
                }

                if(successfulLogin){
                    successfulLogin = false
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        })
    }
    fun getUser(){
        username = text_input_username.editText?.text.toString().trim()



        val call: Call<UserResult> = ServiceBuilder.create().getUser(username)
                    call.enqueue(object: Callback<UserResult> {
            override fun onFailure(call: Call<UserResult>, t: Throwable) {
                Log.i("Login:", "OnFailure ${t.message.toString()}")
                Toast.makeText(this@LoginActivity, "Invalid Login Info", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {
                if(response.isSuccessful) {
                    Log.i("Login", "Success ${response.body()}")

                    Toast.makeText(this@LoginActivity, "Welcome $username", Toast.LENGTH_LONG).show()
                    successfulLogin = true

                }
                else{
                    Log.i("Login", "Failure ${response.errorBody().toString()}")
                    Toast.makeText(this@LoginActivity, "Invalid Login Info", Toast.LENGTH_LONG).show()

                    successfulLogin = false
                }

                if(successfulLogin){
                    successfulLogin = false
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        })
    }


}

