package com.tugrulbo.plantapp_ui.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tugrulbo.plantapp_ui.R
import com.tugrulbo.plantapp_ui.model.RecommendedItemsModel

class RecommendedPagerAdapter(
    private val dataValue:List<RecommendedItemsModel>,
    private val onItemClickListener: OnListClickListener
): RecyclerView.Adapter<RecommendedPagerAdapter.ViewPagerHolder>() {


    inner class ViewPagerHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val title:TextView = itemView.findViewById(R.id.homeRecommendedItemTextTitle)
        private val subTitle:TextView = itemView.findViewById(R.id.homeRecommendedItemTextSub)
        private val image:ImageView = itemView.findViewById(R.id.homeRecommendedItemViewImage)

        fun bind(){
            val url = if (dataValue[layoutPosition].imgUrl != null) dataValue[layoutPosition].imgUrl else null //1
            Glide.with(itemView)
                .load(url)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image)

            dataValue[layoutPosition].title?.let {_title->
                title.text = _title
            }?:run{
                title.visibility = View.GONE
            }

            dataValue[layoutPosition].subTitle?.let {_title->
                subTitle.text = _title
            }?:run{
                subTitle.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onItemClickListener.onClick(dataValue, layoutPosition)
            }
        }
    }

    interface OnListClickListener {
        fun onClick(product: List<RecommendedItemsModel>?,position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_recommend_part,parent,false)
        return ViewPagerHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return dataValue.size
    }


}