package com.mx.ebany.elpadrinoloco.data.models

data class Mesa(
    val idMesa: Int = 0,
    val idSucursal: Int = 0,
    val numeroMesa: Int = 0,
    val disponibilidad: Boolean = false,
    val cuenta: String = "",
    val meseroAsociado: String = "",
    val numeroUsuarios: Int = 0,
)
