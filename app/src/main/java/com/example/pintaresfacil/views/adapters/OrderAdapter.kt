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
import com.example.pintaresfacil.databinding.OrderItemBinding
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.models.OrderItem
import kotlin.properties.Delegates

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    //En el momento que cambie la colección se actualizara al momento la lista
    internal var collection: List<OrderItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var itemListener: (OrderItem) -> Unit = {}

    override fun getItemCount() = collection.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.order_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], itemListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = OrderItemBinding.bind(itemView)

        fun bind(item: OrderItem, itemListener: (OrderItem) -> Unit) {
            binding.tvItem.text = "${item.name} ${item.price}$"
            binding.btnDelete.setOnClickListener {
                itemListener(item)
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