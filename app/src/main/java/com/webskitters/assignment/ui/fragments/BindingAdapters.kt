package com.webskitters.assignment.ui.fragments

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.webskitters.assignment.R
import com.webskitters.assignment.data.WSData
import com.webskitters.assignment.ui.fragments.user.UserFragmentDirections
import com.webskitters.assignment.utils.BitmapManager

class BindingAdapters {

    companion object{
        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
            when(emptyDatabase.value){
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
                else -> view.visibility = View.INVISIBLE
            }
        }
        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view: ConstraintLayout, currentItem: WSData){
            view.setOnClickListener {
                val action = UserFragmentDirections.actionUserFragmentToAddUserFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }
        @BindingAdapter("android:imageSet")
        @JvmStatic
        fun imageSet(view: ImageView, imagePath: String){
            if (imagePath!=null && imagePath.length>0)
            {
                Glide.with(view.context)
                    .load(imagePath + ".png")
                    .placeholder(R.drawable.ic_user)
                    .error(R.drawable.ic_user)
                    .transform(CircleCrop())
                    .into(view)

            }

        }

        @BindingAdapter("android:uriSet")
        @JvmStatic
        fun uriSet(view: ImageView, imagePath: ByteArray){
            if (imagePath!=null && imagePath.size>0)
            {

                val image: Bitmap = BitmapManager.byteToBitmap(imagePath)
                view.setImageBitmap(image)
            }
            else{
                view.setImageResource(R.drawable.ic_user)
            }

        }


    }

}