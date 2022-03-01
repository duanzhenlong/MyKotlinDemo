package com.mmts.mykotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mmts.mykotlindemo.adapter.ExampleAdapter
import com.mmts.mykotlindemo.bean.ExampleItem
import com.mmts.mykotlindemo.util.FileUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val exampleList = generateDummyList()
        recycler_view.adapter = ExampleAdapter(exampleList,this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }


    private fun generateDummyList(): List<ExampleItem> {
        val type = object : TypeToken<List<ExampleItem>>() {}.type
        return Gson().fromJson(FileUtil.getAssetPodcasts("posts.json", this), type)
    }

}