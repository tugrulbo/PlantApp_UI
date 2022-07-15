package com.tugrulbo.plantapp_ui.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugrulbo.plantapp_ui.ui.home.adapter.FeaturePagerAdapter
import com.tugrulbo.plantapp_ui.ui.home.adapter.RecommendedPagerAdapter
import com.tugrulbo.plantapp_ui.databinding.FragmentHomeBinding
import com.tugrulbo.plantapp_ui.model.RecommendedItemsModel


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
            imgUrl = "https://images.pexels.com/photos/4750404/pexels-photo-4750404.jpeg?auto=compress&cs=tinysrgb&w=1600"
        )
        var item2 = RecommendedItemsModel(
            id = 0,
            title = "Spider Plant",
            subTitle = "Chlorophytum comosum",
            imgUrl = "https://images.pexels.com/photos/5783134/pexels-photo-5783134.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        )
        var item3 = RecommendedItemsModel(
            id = 0,
            title = "Pothos",
            subTitle = "Epipremnum aureum",
            imgUrl = "https://images.pexels.com/photos/5978608/pexels-photo-5978608.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        )
        var item4 = RecommendedItemsModel(
            id = 0,
            title = "Lucky Bamboo",
            subTitle = "Dracaena sanderiana",
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
        Toast.makeText(requireContext(), product?.get(position)?.title, Toast.LENGTH_SHORT).show()
    }
}