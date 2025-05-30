package com.mx.ebany.elpadrinoloco.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.data.models.CategoryFood
import com.mx.ebany.elpadrinoloco.data.models.ComidaCatalogo
import com.mx.ebany.elpadrinoloco.databinding.ItemCategoryBinding
import com.mx.ebany.elpadrinoloco.databinding.ItemFoodAdminCatalogBinding
import com.mx.ebany.elpadrinoloco.databinding.ItemFoodCatalogBinding
import com.mx.ebany.elpadrinoloco.utils.Catalogs
import com.mx.ebany.elpadrinoloco.utils.Tools

class FoodCatalogAdapter(private var foodList: List<ComidaCatalogo>, private var onDelete: (ComidaCatalogo) -> Unit) :RecyclerView.Adapter<FoodCatalogAdapter.CategoryHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        return CategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_food_admin_catalog, parent, false))

    }

    override fun onBindViewHolder(
        holder: FoodCatalogAdapter.CategoryHolder,
        position: Int
    ) {
        holder.bindView(foodList[position], onDelete)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class CategoryHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemFoodAdminCatalogBinding.bind(view)
        fun bindView(
            food: ComidaCatalogo,
            onDelete: (ComidaCatalogo) -> Unit
        ){
            binding.tvFoodName.text = "Nombre: "  + food.nombreAlimento
            binding.tvPrice.text = "MXN ${food.precioUnitario}"
            val bitmap = Tools.base64ToBitmap(food.imagen)
            bitmap?.let {
                binding.ivFood.setImageBitmap(it)
            }

            binding.ivDelete.setOnClickListener {
                onDelete(food)
            }
        }
    }


}