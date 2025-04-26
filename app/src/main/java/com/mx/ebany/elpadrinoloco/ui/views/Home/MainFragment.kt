package com.mx.ebany.elpadrinoloco.ui.views.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mx.ebany.elpadrinoloco.data.models.CategoryFood
import com.mx.ebany.elpadrinoloco.data.models.ComidaCatalogo
import com.mx.ebany.elpadrinoloco.data.models.User
import com.mx.ebany.elpadrinoloco.databinding.FragmentMainBinding
import com.mx.ebany.elpadrinoloco.ui.adapters.CategoryCatalogoHomeAdapter
import com.mx.ebany.elpadrinoloco.ui.adapters.CategoryFoodAdapter
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import com.mx.ebany.elpadrinoloco.utils.Constants
import com.mx.ebany.elpadrinoloco.utils.Tools
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale.Category


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var categoryAdapter : CategoryFoodAdapter
    private lateinit var categoryCatalogAdapter: CategoryCatalogoHomeAdapter
    private var categoryList = mutableListOf<CategoryFood>()
    private var categoryCatalogList = mutableListOf<CategoryFood>()

    private val TAG = "MainFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialValues()
        setListeners()
        setObservers()
    }

    private fun setInitialValues() {
        viewModel.loadUsers()





    }

    private fun setListeners() {

    }

    private fun setObservers() {
        viewModel.dataUser.observe(requireActivity()) { user ->
            Log.d("MainActivity", "User: $user")
        }

        viewModel.actualClient.observe(requireActivity()) { user ->
            binding.tvClientActual.text = "Que se te antoja ${user.nombre}?"
        }

        viewModel.categoryFood.observe(requireActivity()) { category ->
            categoryList.clear()
            categoryList.addAll(category)
            setCategoryReciclerView(categoryList)
            viewModel.getComidaCatalogo()
        }

        viewModel.comidaCatalogo.observe(requireActivity()) { comida ->
            categoryCatalogList.clear()
            categoryList.forEach {
                val data = it
                data.foodList = comida.filter { food ->
                    food.idCategoria == it.idCategoryFood
                }
                categoryCatalogList.add(data)
            }
            setCategoryCatalogReciclerView(categoryCatalogList)
        }

    }

    private fun setCategoryReciclerView(categoryList: List<CategoryFood>) {
        categoryAdapter = CategoryFoodAdapter(categoryList){ categoryFood ->
            Log.e(TAG, "Category Select -> $categoryFood")
        }
        binding.rvCategory.adapter = categoryAdapter
        Tools.setSizeCardRecyclerView(categoryList, binding.rvCategory)
        categoryAdapter.notifyDataSetChanged()
    }

    private fun setCategoryCatalogReciclerView(categoryCatalogList: List<CategoryFood>) {
        categoryCatalogAdapter = CategoryCatalogoHomeAdapter(categoryCatalogList){ categoryFood ->
            Log.e(TAG, "Category Select -> $categoryFood")
        }
        binding.rvCatalogFood.adapter = categoryCatalogAdapter
        Tools.setSizeCardRecyclerView(categoryCatalogList, binding.rvCatalogFood)
        categoryCatalogAdapter.notifyDataSetChanged()
    }

}