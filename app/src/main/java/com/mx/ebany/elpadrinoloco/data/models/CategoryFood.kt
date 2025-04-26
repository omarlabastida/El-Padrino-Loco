package com.mx.ebany.elpadrinoloco.data.models

data class CategoryFood(
    val idCategoryFood: Int = 0,
    val nameCategoryFood: String = "",
    var foodList: List<ComidaCatalogo> = emptyList()
)
