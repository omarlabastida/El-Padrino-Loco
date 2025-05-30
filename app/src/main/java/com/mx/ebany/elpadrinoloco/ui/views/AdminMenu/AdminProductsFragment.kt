package com.mx.ebany.elpadrinoloco.ui.views.AdminMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.data.models.CategoryFood
import com.mx.ebany.elpadrinoloco.data.models.ComidaCatalogo
import com.mx.ebany.elpadrinoloco.databinding.FragmentAdminProductsBinding
import com.mx.ebany.elpadrinoloco.ui.adapters.CategoryCatalogoHomeAdapter
import com.mx.ebany.elpadrinoloco.ui.adapters.CategoryFoodAdapter
import com.mx.ebany.elpadrinoloco.ui.adapters.FoodCatalogAdapter
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import com.mx.ebany.elpadrinoloco.utils.Constants
import com.mx.ebany.elpadrinoloco.utils.Tools
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminProductsFragment : Fragment() {

    private lateinit var binding: FragmentAdminProductsBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var categoryAdapter : CategoryFoodAdapter
    private lateinit var foodCatalogAdapter: FoodCatalogAdapter
    private var categoryList = mutableListOf<CategoryFood>()
    private var foodCatalogList = mutableListOf<ComidaCatalogo>()
    private val TAG = "AdminProductsFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.cvAddProduct.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_adminProductsFragment_to_capturePictureFragment)
        }
    }

    private fun setObservers() {
        viewModel.categoryFood.observe(requireActivity()) { category ->
            categoryList.clear()
            categoryList.addAll(category)
            setCategoryReciclerView(categoryList)
            viewModel.getComidaCatalogo()
        }

        viewModel.comidaCatalogo.observe(requireActivity()) { category ->
            foodCatalogList.clear()
            foodCatalogList.addAll(category)
            setFoodCatalogReciclerView(foodCatalogList)
        }
    }

    private fun setCategoryReciclerView(categoryList: List<CategoryFood>) {
        categoryAdapter = CategoryFoodAdapter(categoryList){ categoryFood ->
            Log.e(TAG, "Category Select -> $categoryFood")
            binding.tvCategoryList.text = categoryFood.nameCategoryFood
            viewModel.getComidaCatalogo(categoryFood.idCategoryFood)
        }
        binding.rvCategory.adapter = categoryAdapter
        Tools.setSizeCardRecyclerView(categoryList, binding.rvCategory)
        categoryAdapter.notifyDataSetChanged()
    }


    private fun setFoodCatalogReciclerView(categoryList: List<ComidaCatalogo>) {
        foodCatalogAdapter = FoodCatalogAdapter(categoryList){ categoryFood ->
            Log.e(TAG, "Food Delete -> $categoryFood")
            foodCatalogList.removeIf { it == categoryFood }
            viewModel.deleteDataFirestore("idAlimento", categoryFood.idAlimento, Constants.TABLE_COMIDA_CATALOGO)
        }
        binding.rvFood.adapter = foodCatalogAdapter
        Tools.setSizeCardRecyclerView(categoryList, binding.rvCategory)
        foodCatalogAdapter.notifyDataSetChanged()
    }


}