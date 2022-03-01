package com.mmts.mykotlindemo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mmts.mykotlindemo.R
import com.mmts.mykotlindemo.bean.ExampleItem
import com.mmts.mykotlindemo.ui.WebActivity
import kotlinx.android.synthetic.main.example_item.view.*
import org.jetbrains.anko.toast

/**
 * @author: As
 * @date: 2022-02-28
 */
class ExampleAdapter (private val exampleList: List<ExampleItem>, private var context: Context) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    val BASE_URL = "https://arcblockio.cn"
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.name_text
        val content: ConstraintLayout = itemView.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        Glide
            .with(context)
            .load(BASE_URL + (currentItem.frontmatter?.banner?.childImageSharp?.fixed?.src ?: ""))
            .centerCrop()
            .thumbnail(0.1f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_place)
            .error(R.drawable.ic_place)
            .into(holder.imageView);
        holder.textView1.text = currentItem.frontmatter?.title ?: "input Title"
        holder.content.setOnClickListener{
            context.toast("click")
            //常规含参跳转界面
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", BASE_URL+ (currentItem.frontmatter?.path ?: " "))
            context.startActivity(intent)
        }
    }
}