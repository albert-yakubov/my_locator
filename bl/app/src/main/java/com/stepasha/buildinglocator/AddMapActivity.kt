package com.stepasha.buildinglocator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stepasha.buildinglocator.model.Map
import com.stepasha.buildinglocator.model.NewProperty
import com.stepasha.buildinglocator.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_add_map.*
import retrofit2.Call

import retrofit2.Response
import java.io.*

import java.sql.Date
import java.util.*
import javax.security.auth.callback.Callback


class AddMapActivity : AppCompatActivity() {

    private var imageurl: String? = null
    private var postdate: String? = null
    lateinit var address: String
    lateinit var description : String
    lateinit var additionalInfo: String





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_map)
        pb_add_property.visibility = View.GONE





        btn_property_add.setOnClickListener {
            address = textAddress.editText?.text.toString()
            description = textDescription.editText?.text.toString()
            additionalInfo = textAdditionalInfo.editText?.text.toString()
            imageurl = imageLayout.editText?.text.toString()
            createProperty()
        }
        imageLayout.setOnClickListener {

        }
    }


    fun createProperty(){
        pb_add_property?.visibility = View.VISIBLE
        val call: retrofit2.Call<Void> = ServiceBuilder.create().createProperty(NewProperty(imageurl.toString(), address,description,additionalInfo))
        call.enqueue(object: retrofit2.Callback<Void> {
            override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                Log.i("Add Property", "OnFailure ${t.message}")
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Log.i("Add Property", "OnResponseSuccess ${response.message()}")
                    val nProperty = Map(0, postdate.toString(), imageurl.toString(), address, description, additionalInfo)
               //     LoginActivity.properties?.plus(nProperty)
                    pb_add_property?.visibility = View.GONE
             Toast.makeText(this@AddMapActivity, "Addded", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@AddMapActivity, MainActivity::class.java)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(this@AddMapActivity, "NOT Addded", Toast.LENGTH_LONG).show()
                    pb_add_property?.visibility = View.GONE
                    Log.i("Add Property", "OnResponseFailure ${response.errorBody()}")
                }
            }

        })
    }





}
