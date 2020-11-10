package com.github.florent37.recycler.stickyheader.sample.variants.autoattach

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.recycler.stickyheader.sample.R
import com.github.florent37.recycler.stickyheader.sample.common.UserRepository

class ActivityAutoAttach : AppCompatActivity() {

    private val userRepository = UserRepository()

    private lateinit var recyclerView: RecyclerView
    private val adapter = UserRecyclerAdapterAutoAttach()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_recycler)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.items = userRepository.provideUsers()

    }

}