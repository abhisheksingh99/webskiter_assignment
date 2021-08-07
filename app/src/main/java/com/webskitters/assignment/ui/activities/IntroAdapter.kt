package com.webskitters.assignment.ui.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.webskitters.assignment.R
import com.webskitters.assignment.data.WSData
import com.webskitters.assignment.databinding.ItemPageBinding
import java.util.*

class IntroAdapter : RecyclerView.Adapter<IntroAdapter.MyViewHolder>() {

//    var dataList = emptyList<WSData>()
    private var listner: UserAdapterClickListner?=null
    class MyViewHolder(private val binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root){

        private val colors = intArrayOf(
            android.R.color.holo_blue_bright,
            android.R.color.holo_red_light,
            android.R.color.holo_blue_dark,
            android.R.color.holo_purple
        )

        fun bind(){
////            binding.viewmodel = imageList
//            binding.executePendingBindings()


        if(position == 0){
            binding.tvTitle.text = "ViewPager2"
            binding.tvAbout.text = "In this application we will learn about ViewPager2"
            binding.ivImage.setImageResource(R.drawable.ic_user)
            binding.container.setBackgroundResource(colors[position])
        }
        if(position == 1) {
            binding.tvTitle.text = "ViewPager2"
            binding.tvAbout.text = "In this application we will learn about ViewPager2"
            binding.ivImage.setImageResource(R.drawable.ic_call)
            binding.container.setBackgroundResource(colors[position])
        }
        if(position == 2) {
            binding.tvTitle.text = "ViewPager2"
            binding.tvAbout.text = "In this application we will learn about ViewPager2"
            binding.ivImage.setImageResource(R.drawable.ic_home)
            binding.container.setBackgroundResource(colors[position])
        }
        if(position == 3) {
            binding.tvTitle.text = "ViewPager2"
            binding.tvAbout.text = "In this application we will learn about ViewPager2"
            binding.ivImage.setImageResource(R.drawable.ic_check)
            binding.container.setBackgroundResource(colors[position])
        }

    }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPageBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = dataList[position]
        holder.bind()


    }

    override fun getItemCount(): Int {
        return 4
    }

//    fun setData(imageList : List<WSData>){
//        this.dataList = imageList
//        notifyDataSetChanged()
//    }


    interface UserAdapterClickListner{
        fun onClick(position :Int,imageList: WSData)
        fun onLongClick(position :Int,imageList: WSData)
    }
    fun setListner(listn:UserAdapterClickListner)
    {
        listner=listn
    }

}