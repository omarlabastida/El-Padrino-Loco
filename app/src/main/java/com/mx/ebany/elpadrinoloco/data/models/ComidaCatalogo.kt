package com.mx.ebany.elpadrinoloco.data.models

data class ComidaCatalogo(
    val idAlimento: Int = 0,
    val idSucursal: Int = 0,
    val disponibilidad: Boolean = false,
    val nombreAlimento: String = "",
    val precioUnitario: String = "",
    val promocion: String = "",
    val imagen: String = "",
    val idCategoria: Int = 0,

)
