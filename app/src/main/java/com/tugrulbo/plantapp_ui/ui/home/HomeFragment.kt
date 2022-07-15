package com.tugrulbo.plantapp_ui.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugrulbo.plantapp_ui.R
import com.tugrulbo.plantapp_ui.ui.home.adapter.FeaturePagerAdapter
import com.tugrulbo.plantapp_ui.ui.home.adapter.RecommendedPagerAdapter
import com.tugrulbo.plantapp_ui.databinding.FragmentHomeBinding
import com.tugrulbo.plantapp_ui.model.RecommendedItemsModel

const val TITLE = "title"
const val SUBTITLE = "subtitle"
const val IMAGE_URL = "imgUrl"
const val PRICE = "price"

class HomeFragment : Fragment(), RecommendedPagerAdapter.OnListClickListener, FeaturePagerAdapter.OnListClickListener {

    private lateinit var binding:FragmentHomeBinding
    private var productList = mutableListOf<RecommendedItemsModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRecommendedSliderData()
        setupRecommendedSlider()
        setupFeatureSlider()
    }

    private fun getRecommendedSliderData(){
        var item1 = RecommendedItemsModel(
            id = 0,
            title = "Peace Lily",
            subTitle = "Spathiphyllum",
            price = "$400",
            imgUrl = "https://images.pexels.com/photos/4750404/pexels-photo-4750404.jpeg?auto=compress&cs=tinysrgb&w=1600"
        )
        var item2 = RecommendedItemsModel(
            id = 0,
            title = "Spider Plant",
            subTitle = "Chlorophytum comosum",
            price = "$600",
            imgUrl = "https://images.pexels.com/photos/5783134/pexels-photo-5783134.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        )
        var item3 = RecommendedItemsModel(
            id = 0,
            title = "Pothos",
            subTitle = "Epipremnum aureum",
            price = "$300",
            imgUrl = "https://images.pexels.com/photos/5978608/pexels-photo-5978608.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        )
        var item4 = RecommendedItemsModel(
            id = 0,
            title = "Lucky Bamboo",
            subTitle = "Dracaena sanderiana",
            price = "$250",
            imgUrl = "https://images.pexels.com/photos/8024369/pexels-photo-8024369.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        )

        productList.add(item1)
        productList.add(item2)
        productList.add(item3)
        productList.add(item4)

    }


    private fun setupRecommendedSlider(){
        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        var recommendedAdapter = RecommendedPagerAdapter(productList,this)
        binding.homeRecommendedRecyclerView.layoutManager = layoutManager
        binding.homeRecommendedRecyclerView.adapter = recommendedAdapter

    }

    private fun setupFeatureSlider(){
        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        var recommendedAdapter = FeaturePagerAdapter(productList,this)
        binding.homeFeatureRecyclerView.layoutManager = layoutManager
        binding.homeFeatureRecyclerView.adapter = recommendedAdapter
    }

    override fun onClick(product: List<RecommendedItemsModel>?, position: Int) {
        val navController = findNavController()
        product?.get(position)?.let { item->
            val bundle = Bundle()
            val title = item.title
            val subtitle = item.subTitle
            val imgUrl = item.imgUrl
            val price = item.price

            bundle.putString(TITLE,title)
            bundle.putString(SUBTITLE,subtitle)
            bundle.putString(IMAGE_URL,imgUrl)
            bundle.putString(PRICE, price)
            navController.navigate(R.id.homeFragmentToProductDetail, bundle)
        }

    }
}