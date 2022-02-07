package com.example.bakinghelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(
    private val context: match, private val dataList: ArrayList<DataBK>
): RecyclerView.Adapter<CustomAdapter.ItemViewHolder>(){

    var mPosition = 0

    fun getPosition() : Int{
        return mPosition
    }

    fun setPosition(position : Int){
        mPosition = position
    }

    fun addItem(dataBK: DataBK){
        dataList.add(dataBK)
        notifyDataSetChanged()
    }

    fun matchItem(){
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView : View):
            RecyclerView.ViewHolder(itemView){
        private val name = itemView.findViewById<TextView>(R.id.txt_name)
        private val origin = itemView.findViewById<TextView>(R.id.txt_origin)
        private val matchorigin = itemView.findViewById<TextView>(R.id.txt_matchorigin)

        fun bind(dataBK: DataBK, context: match){
            name.text = dataBK.name
            origin.text = dataBK.origin.toString()
            matchorigin.text = dataBK.matchorigin.toString()
        }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
        holder.itemView.setOnClickListener{view -> setPosition(position)}
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}