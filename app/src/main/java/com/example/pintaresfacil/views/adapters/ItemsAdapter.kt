package com.example.pintaresfacil.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pintaresfacil.R
import com.example.pintaresfacil.databinding.ItemItemBinding
import com.example.pintaresfacil.models.Item
import kotlin.properties.Delegates

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {


    //En el momento que cambie la colección se actualizara al momento la lista
    internal var collection: List<Item> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var itemListener: (Item) -> Unit = {}

    //Cantidad de items a mostar
    override fun getItemCount() = collection.size

    //Creamos el view Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_item))

    //Devuelve las posiciones
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], itemListener)
    }
    //Recibira la vista
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemItemBinding.bind(itemView)

        fun bind(item: Item, itemListener: (Item) -> Unit) {
            binding.ivImage.loadFromUrl(item.image_url)
            binding.tvItem.text = item.name
            binding.tvStock.text = item.available.toString()
            binding.tvPrice.text = item.price.toString()
            //modificat


            binding.chShopping.setOnCheckedChangeListener { _, checked ->
                item.checked = checked
            }
        }
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

    fun ImageView.loadFromUrl(url: String) =
        Glide.with(this.context.applicationContext)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}