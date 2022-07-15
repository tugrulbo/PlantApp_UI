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
import org.w3c.dom.Text

class FeaturePagerAdapter(
    private val dataValue:List<RecommendedItemsModel>,
    private val onItemClickListener: OnListClickListener
): RecyclerView.Adapter<FeaturePagerAdapter.ViewPagerHolder>() {


    inner class ViewPagerHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val title:TextView = itemView.findViewById(R.id.homeFeaturedItemTextTitle)
        private val image:ImageView = itemView.findViewById(R.id.homeFeaturedItemViewImage)

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
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_featured_part,parent,false)
        return ViewPagerHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return dataValue.size
    }


}