package com.robosoft.foursquare.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.foursquare.R
import com.robosoft.foursquare.model.dataclass.hotel.HotelResponse
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get

class RecyclerAdapter(val context: Context, val data: HotelResponse) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hotelImg: ImageView = itemView.findViewById(R.id.hotel_img)
        val hotelName: TextView  = itemView.findViewById(R.id.hotel_tv)
        val rating: TextView = itemView.findViewById(R.id.rating_tv)
        val address: TextView = itemView.findViewById(R.id.address_tv)
        val favourite: ImageButton = itemView.findViewById(R.id.favourite_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_recycler_tab, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotelData = data[position]

        val imageUrl = hotelData.placeImages.url

        holder.hotelImg.let {
            if (imageUrl != null) {
                val uri = Uri.parse(imageUrl)
                Picasso.with(context).load(uri).into(it)
            }
        }
        holder.hotelName.text = hotelData.placeName
        holder.rating.text = hotelData.totalrating.toString()
        holder.address.text = hotelData.address
        holder.favourite.setOnClickListener {
            holder.favourite.setImageResource(R.drawable.favourite_icon_selected)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
