package com.robosoft.foursquare.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.foursquare.R
import com.robosoft.foursquare.model.dataclass.hotel.HotelResponse
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.lang.reflect.Array.get

class RecyclerAdapter(val context: Context?, val data: HotelResponse) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hotelImg: ImageView = itemView.findViewById(R.id.hotel_img)
        val hotelName: TextView  = itemView.findViewById(R.id.hotel_tv)
        val rating: TextView = itemView.findViewById(R.id.rating_tv)
        val desc: TextView = itemView.findViewById(R.id.hotel_type_tv)
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
            val uri = Uri.parse(imageUrl)
            Picasso.with(context).load(uri).into(it)
        }
        holder.hotelName.text = hotelData.placeName
        holder.rating.text = hotelData.totalrating.toString()

        if (hotelData.totalrating >= 4){
            holder.rating.setBackgroundResource(R.drawable.custom_rating_green)
        }
            else if (hotelData.totalrating >=2){
            holder.rating.setBackgroundResource(R.drawable.custom_rating_yellow)
            }
                else{
            holder.rating.setBackgroundResource(R.drawable.custom_rating_red)
                }

        holder.desc.text = hotelData.priceRange  + hotelData.dist.calculated * 1.609344 + "Km"

        holder.address.text = hotelData.address
        holder.favourite.setOnClickListener {
            holder.favourite.setImageResource(R.drawable.favourite_icon_selected)
            Toast.makeText(context,"Added to favourite",Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnClickListener {
            Toast.makeText(context,hotelData.placeName,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
