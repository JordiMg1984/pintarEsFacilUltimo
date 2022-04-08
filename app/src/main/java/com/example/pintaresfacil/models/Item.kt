package com.example.pintaresfacil.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//Creamos la tabla Itema que contendra los datos de la lista de pedidos
@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val image_url: String,
    val price: Int,
    var available: Int,
    var checked: Boolean = false
) {
    companion object {
        fun items() = mutableListOf(
            Item(
                name = "Pintura plástica Interior Blanca 15lt",
                image_url = "https://th.bing.com/th/id/OIP.q4fC1Y5OoH4Hi6MqNZC7fwHaHa?pid=ImgDet&rs=1",
                price = 35,
                available = 3
            ),
            Item(
                name = "Pintura plástica Interior Blanca 4lt",
                image_url = "https://cdn.manomano.com/images/images_products/3565902/P/12987973_1.jpg",
                price = 15 ,
                available = 6
            ),
            Item(
                name = "Pintura plástica Exterior Blanca 15lt",
                image_url = "https://media.pinturescarrion.com/product/reveton-cubic-blanco-800x800.jpg",
                price = 60 ,
                available = 4
            ),
            Item(
                name = "Pintura plástica Exterior Blanca 4lt",
                image_url = "https://th.bing.com/th/id/OIP.blaWAV1jZo5kSt6BS-an9wAAAA?pid=ImgDet&rs=1",
                price = 25 ,
                available = 5
            ),
            Item(
                name = "Esmalte Brillante al disolvente 4lt ",
                image_url = "https://cdn.manomano.com/esmalte-brillante-reveton-4-lt-L-3565902-18421487_1.jpg",
                price = 60 ,
                available = 2
            ),
            Item(
                name = "Esmalte Brillante al disolvente 750ml ",
                image_url = "https://www.fransa.es/1084-large_default/esmalte-sintetico-brillante-color-rojo-carruaje-reveton-750ml.jpg",
                price = 12 ,
                available = 6
            ),
            Item(
                name = "Esmalte Satinado al disolvente 4lt  ",
                image_url = "https://www.dispivalonline.es/8487-large_default/reveton-esmalte-satinado-blanco-4-lt.jpg",
                price = 65 ,
                available = 3
            ),
            Item(
                name = "Esmalte Satinado al disolvente 750ml  ",
                image_url = "https://th.bing.com/th/id/OIP.CqQHno9neYZNvBgFT1IShQHaHa?pid=ImgDet&rs=1",
                price = 13 ,
                available = 6
            ),
            Item(
                name = "Imprimación al disolvente gris 750ml  ",
                image_url = "https://tevisa.es/6476-medium_default/imprimacion-hierro-antioxidante-gris-4lt-tollens.jpg",
                price = 10 ,
                available = 6
            ),
            Item(
                name = "Selladora al disolvente blanca 750ml  ",
                image_url = "https://www.dispivalonline.es/10210-large_default/imprimacion-selladora-750-ml.jpg",
                price = 7 ,
                available = 6
            ),

            Item(
                name = "Brocha para pinturas plásticas nº12  ",
                image_url = "https://th.bing.com/th/id/OIP._8dkaLyqq-wIyiqnDihixAHaEf?pid=ImgDet&rs=1",
                price = 8 ,
                available = 6
            ),
            Item(
                name = "Rodillo pelo corto interiores 22cm ",
                image_url = "https://th.bing.com/th/id/OIP.JNzYcW-moXH1wV0MJRJvEwHaHa?pid=ImgDet&rs=1",
                price = 11 ,
                available = 6
            ),
            Item(
                name = "Rodillo pelo largo exteriores 22cm ",
                image_url = "https://media.bahag.com/assets/resp_product/31/62/3162768_25742821.jpg",
                price = 14 ,
                available = 6
            ),
            Item(
                name = "Cubeta negra 16lt ",
                image_url = "https://th.bing.com/th/id/R.91b31ed79c9e588440dcb1e821cdf485?rik=qZULhqU7pPa03Q&riu=http%3a%2f%2fwww.alavesadepinturas.com%2ftienda%2f115-large_default%2fcubeta-negra-16-lt-con-rejilla.jpg&ehk=Ne7ddrxQ8bzbUDQHTyPuDNJH%2fMf9ZYZpBM5Mkr7lo9E%3d&risl=&pid=ImgRaw&r=0",
                price = 5 ,
                available = 6
            ),
            Item(
                name = "Kit esmaltar madera/hierro ",
                image_url = "https://images-na.ssl-images-amazon.com/images/I/7166BNneMwL._AC_SX522_.jpg",
                price = 8 ,
                available = 6
            ),
            Item(
                name = "Cinta carrocero 24mm ",
                image_url = "https://chinoantonio.com/210450-large_default/cinta-de-carrocero-de-18-mm.jpg",
                price = 1 ,
                available = 30
            ),
            Item(
                name = " Plástico cubrir superficies 4x5  ",
                image_url = "https://th.bing.com/th/id/OIP.Q7sgvGIjorjV8bGR-_v4_AAAAA?pid=ImgDet&rs=1",
                price = 1 ,
                available = 25
            ),
            Item(
                name = " Moqueta para cubrir el suelo interior/exterior 1x25mt  ",
                image_url = "https://www.cuidadodelhogar.com/server/Portal_0010592/img/products/fieltro-plastificado-especial-suelos-de-1x25_9460160_xxl.jpg",
                price = 15 ,
                available = 6
            ),
            Item(
                name = "Juego 3 lijas para madera/hierro ",
                image_url = "https://http2.mlstatic.com/D_NQ_NP_812716-MLA45179445985_032021-F.jpg",
                price = 2 ,
                available = 21
            ),



        )
    }
}