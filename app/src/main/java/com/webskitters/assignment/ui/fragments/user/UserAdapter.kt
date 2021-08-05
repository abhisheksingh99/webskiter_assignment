package com.webskitters.assignment.ui.fragments.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.webskitters.assignment.data.WSData
import com.webskitters.assignment.databinding.ItemUserBinding
import java.util.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    var dataList = emptyList<WSData>()
    private var listner: UserAdapterClickListner?=null
    class MyViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(imageList: WSData){
            binding.viewmodel = imageList
            binding.executePendingBindings()

    }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
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
        val currentItem = dataList[position]
        holder.bind(currentItem)

        holder.itemView.setOnLongClickListener {
            listner?.onLongClick(position,dataList.get(position))
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener {
            listner?.onClick(position,dataList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(imageList : List<WSData>){
        this.dataList = imageList
        notifyDataSetChanged()
    }


    interface UserAdapterClickListner{
        fun onClick(position :Int,imageList: WSData)
        fun onLongClick(position :Int,imageList: WSData)
    }
    fun setListner(listn:UserAdapterClickListner)
    {
        listner=listn
    }

}