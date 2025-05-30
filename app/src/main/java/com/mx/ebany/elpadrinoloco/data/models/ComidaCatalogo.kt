package com.mx.ebany.elpadrinoloco.data.models

data class ComidaCatalogo(
    val idAlimento: Int = 0,
    val idSucursal: Int = 0,
    val disponibilidad: Boolean = false,
    var nombreAlimento: String = "",
    var precioUnitario: String = "",
    val promocion: String = "",
    var imagen: String = "",
    var idCategoria: Int = 0,

    )
