package com.example.pintaresfacil.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.pintaresfacil.R
import com.example.pintaresfacil.databinding.ClientsItemBinding
import com.example.pintaresfacil.models.Client
import kotlin.properties.Delegates

class ItemsClient : RecyclerView.Adapter<ItemsClient.ViewHolder>() {


    //En el momento que cambie la colección se actualizara al momento la lista
    internal var collection: List<Client> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var itemListener: (Client) -> Unit = {}

    //Cantidad de items a mostar
    override fun getItemCount() = collection.size

    //Creamos el view Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.clients_item))

    //Devuelve las posiciones
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], itemListener)
    }
    //Recibira la vista
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ClientsItemBinding.bind(itemView)

        fun bind(item: Client, itemListener: (Client) -> Unit) {
            binding.tvEmail.text = item.email.toString()
            binding.tvName.text = item.name.toString()
            binding.tvSurName.text = item.surName.toString()
            binding.tvDni.text = item.dni.toString()
            binding.tvMobile.text = item.mobile.toString()
            binding.tvPassword.text = item.password.toString()




            }
        }


    fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

}

