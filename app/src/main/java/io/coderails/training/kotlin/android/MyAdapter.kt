package io.coderails.training.kotlin.android

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import kotlinx.android.synthetic.main.recylcer_item.view.*

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    lateinit var itemList: List<Item>
    lateinit var queue: RequestQueue

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemViewHolder = inflater.inflate(R.layout.recylcer_item, parent, false)
        return MyViewHolder(itemViewHolder, this)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(viewHodler: MyViewHolder, position: Int) {
        val currentItem = itemList.get(position)
        viewHodler.itemView.title_view.text = currentItem.title
        val imageRequest = ImageRequest(currentItem.imageUrl, Response.Listener<Bitmap> { bitmap ->
            viewHodler.itemView.imageView.setImageBitmap(bitmap)
        }, 120, 120, ImageView.ScaleType.FIT_CENTER, null, Response.ErrorListener {

        })
        queue.add(imageRequest)
    }
}
