package com.mx.ebany.elpadrinoloco.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.data.models.CategoryFood
import com.mx.ebany.elpadrinoloco.databinding.ItemCategoryBinding
import com.mx.ebany.elpadrinoloco.utils.Catalogs

class CategoryFoodAdapter(private var categoryList: List<CategoryFood>, private var onClick: (CategoryFood) -> Unit) :RecyclerView.Adapter<CategoryFoodAdapter.CategoryHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        return CategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))

    }

    override fun onBindViewHolder(
        holder: CategoryFoodAdapter.CategoryHolder,
        position: Int
    ) {
        holder.bindView(categoryList[position], onClick)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemCategoryBinding.bind(view)
        fun bindView(
            category: CategoryFood,
            onClick: (CategoryFood) -> Unit
        ){
            binding.tvCategory.text = category.nameCategoryFood
            binding.ivCategory.setImageResource(Catalogs.getImageCategory(category.idCategoryFood))

            val context = binding.root.context
            val selectedColor = ContextCompat.getColor(context, R.color.rojo_chile)
            val unselectedColor = ContextCompat.getColor(context, R.color.gray)

            binding.cvCategory.setCardBackgroundColor(
                if (adapterPosition == selectedPosition) selectedColor else unselectedColor
            )

            binding.cvCategory.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onClick(category)
            }
        }

    }


}