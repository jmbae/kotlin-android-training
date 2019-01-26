package io.coderails.training.kotlin.android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_recycler.*
import org.json.JSONObject

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

//        val adapter = MyAdapter()
//        adapter.itemList = listOf(Item("title1"),Item("title2"),Item("title3"))
//        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        val url = "https://api.flickr.com/services/feeds/photos_public.gne?tags=dog&format=json&nojsoncallback=1"

        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            val jsonObject = JSONObject(response)
            val jsonArray = jsonObject.getJSONArray("items")

            val itemList = mutableListOf<Item>()

            for(i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val title = item.getString("title")
                val imageUrl: String = item.getJSONObject("media").getString("m")
                itemList.add(Item(title=title, imageUrl = imageUrl))
            }

            val adapter = MyAdapter()
            adapter.itemList = itemList
            adapter.queue = queue
            recycler_view.adapter = adapter

        }, Response.ErrorListener { error ->

        })

        queue.add(request)


    }
}
