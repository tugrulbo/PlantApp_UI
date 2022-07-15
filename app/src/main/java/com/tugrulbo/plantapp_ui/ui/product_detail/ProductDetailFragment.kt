package com.tugrulbo.plantapp_ui.ui.product_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tugrulbo.plantapp_ui.R
import com.tugrulbo.plantapp_ui.databinding.FragmentProductDetailBinding

const val TITLE = "title"
const val SUBTITLE = "subtitle"
const val IMAGE_URL = "imgUrl"
const val PRICE = "price"

class ProductDetailFragment : Fragment() {

    lateinit var binding: FragmentProductDetailBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        onClicks()
        setupData()
    }

    private fun onClicks(){
        binding.productDetailImgBack.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun setupData(){
        val title = arguments?.get(TITLE)
        val subtitle = arguments?.get(SUBTITLE)
        val imgUrl = arguments?.get(IMAGE_URL)
        val price = arguments?.get(PRICE)

        Glide.with(requireContext())
            .load(imgUrl)
            .centerCrop()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.productDetailImg)

        title?.let { text->
            binding.productDetailTxtTitle.text = text.toString()
        }?:run{
            binding.productDetailTxtTitle.visibility = View.GONE
        }

        subtitle?.let { text->
            binding.productDetailTxtSubTitle.text = text.toString()
        }?:run{
            binding.productDetailTxtSubTitle.visibility = View.GONE
        }

        price?.let { text->
            binding.productDetailTxtPrice.text = text.toString()
        }?:run{
            binding.productDetailTxtTitle.visibility = View.GONE
        }
    }

}