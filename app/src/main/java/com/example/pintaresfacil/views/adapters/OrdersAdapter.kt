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
import com.example.pintaresfacil.databinding.OrdersItemBinding
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.models.Order
import com.example.pintaresfacil.models.OrderItem
import kotlin.properties.Delegates

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    //En el momento que cambie la colección se actualizara al momento la lista
    internal var collection: List<Order> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var itemListener: (Order) -> Unit = {}

    override fun getItemCount() = collection.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.orders_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], itemListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = OrdersItemBinding.bind(itemView)

        fun bind(item: Order, itemListener: (Order) -> Unit) {
            var items = String()
            var prize = 0
            item.items.forEach {
                prize += it.price
                items = "${items}${it.name},"
            }
            binding.tvItem.text = "$items $prize$"
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