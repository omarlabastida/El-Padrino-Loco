package com.mx.ebany.elpadrinoloco.ui.views.AdminMenu

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.data.models.CategoryFood
import com.mx.ebany.elpadrinoloco.data.models.ComidaCatalogo
import com.mx.ebany.elpadrinoloco.data.models.User
import com.mx.ebany.elpadrinoloco.databinding.FragmentCapturePictureBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import com.mx.ebany.elpadrinoloco.utils.Constants
import com.mx.ebany.elpadrinoloco.utils.Tools
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CapturePictureFragment : Fragment() {

    private lateinit var binding: FragmentCapturePictureBinding
    private val viewModel: MainViewModel by activityViewModels()
    var foodData = ComidaCatalogo()
    var uriPicture: Uri = Uri.EMPTY

    private val pickImageLauncher = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val uri = result.data?.data
            uri?.let {
                uriPicture = it
                val bitmap = Tools.uriToBitmap(it,requireActivity())
                val imageB64 = Tools.bitmapToBase64(bitmap)
                val decodedBitmap = Tools.base64ToBitmap(imageB64)
                if(imageB64.isNotEmpty()){
                    binding.ivFood.setImageBitmap(decodedBitmap)
                    binding.ivFood.setVisibility(View.VISIBLE)
                    binding.ivAdd.setVisibility(View.GONE)
                }else{
                    binding.ivFood.setVisibility(View.VISIBLE)
                    binding.ivAdd.setVisibility(View.GONE)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCapturePictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialValues()
        setObservers()
        setListeners()
    }

    private fun setInitialValues() {
    }

    private fun setObservers() {

        viewModel.categoryFood.observe(requireActivity()) { categoryList ->
            setCategory(categoryList)
        }

    }

    private fun setListeners() {
        binding.ivAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageLauncher.launch(intent)
        }

        binding.btnAddElement.setOnClickListener {
            Log.e("TAG", "FoodData -> $foodData")
            binding.etName.text.toString().let {
                foodData.nombreAlimento = it
            }
            binding.etPrice.text.toString().let {
                foodData.precioUnitario = it + ".00 MXN"
            }
            if(uriPicture != null){
                viewModel.uploadFileToStorage(uriPicture)
            }else{
                Toast.makeText(requireActivity(), "Captura una foto", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun setCategory(category: List<CategoryFood>){

        val categoryList = mutableListOf<String>()

        category.forEach{
            categoryList.add(it.nameCategoryFood)
        }
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCategory.adapter = adapter

        binding.spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                foodData.idCategoria = category.filter { it.idCategoryFood == category[i].idCategoryFood }.first().idCategoryFood
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
    }

}