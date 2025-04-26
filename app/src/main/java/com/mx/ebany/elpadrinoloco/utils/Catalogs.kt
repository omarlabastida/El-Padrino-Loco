package com.mx.ebany.elpadrinoloco.utils

import com.mx.ebany.elpadrinoloco.R

class Catalogs {

    companion object{

        fun getImageCategory(idCategoryFood: Int): Int {

            return when(idCategoryFood){
                1 -> R.drawable.ic_tacos
                2 -> R.drawable.ic_drinks
                3 -> R.drawable.ic_bear
                4 -> R.drawable.ic_gringa
                5 -> R.drawable.ic_tortas
                6 -> R.drawable.ic_alcohol
                7 -> R.drawable.ic_desert
                8 -> R.drawable.ic_coffe
                9 -> R.drawable.ic_hamburguer
                else -> R.drawable.ic_el_padrino_logo
            }

        }

    }
}