package com.mx.ebany.elpadrinoloco.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.data.models.CategoryFood
import com.mx.ebany.elpadrinoloco.data.models.ComidaCatalogo
import com.mx.ebany.elpadrinoloco.databinding.ItemRvCatalogFoodBinding
import com.mx.ebany.elpadrinoloco.utils.Tools

class CategoryCatalogoHomeAdapter(private var categoryHomeList: List<CategoryFood>, private var onClick: (CategoryFood) -> Unit) :RecyclerView.Adapter<CategoryCatalogoHomeAdapter.CategoryHolder>() {

    private lateinit var foodAdapter : FoodHomeAdapter
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        return CategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_catalog_food, parent, false))

    }

    override fun onBindViewHolder(
        holder: CategoryCatalogoHomeAdapter.CategoryHolder,
        position: Int
    ) {
        holder.bindView(categoryHomeList[position], onClick)
    }

    override fun getItemCount(): Int {
        return categoryHomeList.size
    }

    inner class CategoryHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemRvCatalogFoodBinding.bind(view)
        fun bindView(
            category: CategoryFood,
            onClick: (CategoryFood) -> Unit
        ){
            binding.tvCategory.text = category.nameCategoryFood
            setFoodReciclerView(category.foodList, binding.rvCatalogFood)
        }
    }

    private fun setFoodReciclerView(foodList: List<ComidaCatalogo>, rvCatalogFood: RecyclerView) {
        foodAdapter = FoodHomeAdapter(foodList){ food ->

        }
        rvCatalogFood.adapter = foodAdapter
        Tools.setSizeCardRecyclerView(foodList, rvCatalogFood)
        foodAdapter.notifyDataSetChanged()
    }


}