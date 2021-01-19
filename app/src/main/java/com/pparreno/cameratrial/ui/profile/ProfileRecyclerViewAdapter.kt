package com.pparreno.cameratrial.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pparreno.cameratrial.databinding.ProfileRecyclerItemBinding
import com.pparreno.cameratrial.utils.files.UIHelper


class ProfileRecyclerViewAdapter : RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder>() {
    lateinit var  viewBinding: ProfileRecyclerItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        viewBinding = ProfileRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding.root, viewBinding.itemImage)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position % 3 == 0)
        {
            holder.itemImageView.layoutParams.height = UIHelper.getIntFromDips(450.0f, holder.itemView.context)
        } else {
            holder.itemImageView.layoutParams.height = UIHelper.getIntFromDips(200.0f, holder.itemView.context)
        }
        holder.itemImageView.requestLayout()
    }

    override fun getItemCount(): Int {
        return 15
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var itemImageView : ImageView

        constructor(itemView: View, itemImage: ImageView) : this(itemView) {
            this.itemImageView = itemImage
        }

    }

}