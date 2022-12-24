package com.robosoft.foursquare.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.foursquare.R
import com.robosoft.foursquare.model.dataclass.photoReview.PhotoReviewResponse
import com.robosoft.foursquare.view.activity.IndividualHotelContainerActivity
import com.robosoft.foursquare.view.fragment.individualhotel.IndividualPhotoFragment
import com.squareup.picasso.Picasso

class PhotoAdapter(
    private val activity: FragmentActivity?,
    private val data: PhotoReviewResponse,
    lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg: ImageView = itemView.findViewById(R.id.photo_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_review_tab, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = data.data.reviews[position]

        val imageUrl = image.reviewImage.urls[position]
        holder.profileImg.let {
            val uri = Uri.parse(imageUrl)
            Picasso.with(activity).load(uri).into(it)
        }

        holder.itemView.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.hotel_container,IndividualPhotoFragment())?.commit()
        }

    }

    override fun getItemCount(): Int {
        return data.data.reviews.size
    }
}