package com.stepasha.buildinglocator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.stepasha.buildinglocator.adapter.MapsAdapter
import com.stepasha.buildinglocator.model.Map
import com.stepasha.buildinglocator.model.Maps

import com.stepasha.buildinglocator.retrofit.LoginServiceSql
import com.stepasha.buildinglocator.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_news.*
import okhttp3.internal.toImmutableList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {
    companion object{
        var map: MutableList<Map>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        getAllMaps()


    }
    fun getAllMaps(){
        val call: Call<MutableList<Map>> = ServiceBuilder.create().getAllMaps()

        call.enqueue(object: Callback<MutableList<Map>> {
            override fun onFailure(call: Call<MutableList<Map>>, t: Throwable) {
                Log.i("Properties ", "onFailure ${t.message}")
            }

            override fun onResponse(call: Call <MutableList<Map>>, response: Response<MutableList<Map>>) {
                if(response.isSuccessful){

                    val list = response.body()

                        map = list

                    vRecycle?.apply {
                        layoutManager = LinearLayoutManager(this@NewsActivity)
                        adapter = MapsAdapter(map)

                    }
                }
                else{
                    Log.i("Properties ", "OnResponseFailure ${response.errorBody()}")
                }
            }

        })
    }
}
