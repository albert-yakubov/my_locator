package com.stepasha.buildinglocator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stepasha.buildinglocator.R
import com.stepasha.buildinglocator.model.Map
import com.stepasha.buildinglocator.model.Maps
import kotlinx.android.synthetic.main.item_view.view.*

class MapsAdapter(private var maps: MutableList<Map>?) :
    RecyclerView.Adapter<MapsAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return maps!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val currentMap = maps?.get(position)

        // If the url link is longer than 10, then get the image from the url. Else use a default image.
        val imageSuffix = currentMap?.imageurl.toString()
        if ((currentMap?.imageurl.toString().endsWith("jpeg")) ||
            (currentMap?.imageurl.toString().endsWith("jpg")) ||
            (currentMap?.imageurl.toString().endsWith("png")) ||
            (currentMap?.imageurl.toString().contains("auto"))
        ) {
            Picasso.get().load(currentMap?.imageurl).into(holder.image)
        }



        holder.mapAddress.text = currentMap?.address
        holder.description.text = currentMap?.description
        holder.postdate.text = currentMap?.posteddate
        holder.additionalInfo.text = currentMap?.additionalInfo


    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView? = itemView.iv_property_image
        val mapAddress: TextView = itemView.vAddress
        val description: TextView = itemView.vDescription
        val postdate: TextView = itemView.vPostDate
        val additionalInfo: TextView = itemView.vAdditionalInfo


     //   fun cardViewDeleteOnLongPress(itemPosition: Int) {
     //       maps?.removeAt(itemPosition)
     //       updateMap(maps)
     //   }
    }

    fun updateMap(newList: ArrayList<Map>?) {
        maps = newList
        notifyDataSetChanged()
    }

    //fun deleteProperty(id: Int){
    //    val call: Call<Void> = ServiceBuilder.create().deleteProperty(LoginActivity.token, id)
    //    call.enqueue(object: Callback<Void> {
    //        override fun onFailure(call: Call<Void>, t: Throwable) {
    //            Log.i("Add Property", "OnFailure ${t.message}")
    //        }
//
    //        override fun onResponse(call: Call<Void>, response: Response<Void>) {
    //            if(response.isSuccessful){
    //                Log.i("Delete Property", "OnResponseSuccess ${response.message()}")
//
    //            }
    //            else{
    //                Log.i("Add Property", "OnResponseFailure ${response.errorBody()}")
    //            }
    //        }
//
    //    })
    //}


}