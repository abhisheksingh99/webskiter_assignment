package com.webskitters.assignment.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.webskitters.assignment.databinding.ItemImageBinding
import com.webskitters.assignment.model.ImageListItem

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.MyViewHolder>(),Filterable {
    var dataList = emptyList<ImageListItem>()
    private var listner:ImageAdapterClickListner?=null
    var mPois: List<ImageListItem> = dataList
    class MyViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(imageList: ImageListItem){
            binding.viewmodel = imageList
            binding.executePendingBindings()

            if (imageList.IsSelected == true)
            {
                binding.checkBox.visibility= View.VISIBLE
                binding.checkBox.isChecked=true
            }
            else if (imageList.IsSelected == false)
            {
                binding.checkBox.isChecked=false
                binding.checkBox.visibility= View.GONE
            }
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemImageBinding.inflate(layoutInflater, parent, false)
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



        holder.itemView.setOnClickListener {
            listner?.onClick(position,dataList.get(position))
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(imageList : List<ImageListItem>){
        this.dataList = imageList
        mPois=dataList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {

                if(filterResults.values!=null)
                {

                    dataList=(filterResults.values as List<ImageListItem>)

                    notifyDataSetChanged()
                }

            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    mPois
                else
                    mPois.filter {
                        it.title.toLowerCase().contains(queryString) || it.IsSelected ==true
                    }
                return filterResults
            }
        }
    }


    interface ImageAdapterClickListner{
        fun onClick(position :Int,imageList: ImageListItem)
    }

    fun setListner(listnern:ImageAdapterClickListner)
    {
        listner=listnern
    }

    fun updateRow(position: Int, imageList: ImageListItem) {
        dataList.get(position).IsSelected = imageList.IsSelected
        notifyDataSetChanged()
    }

}