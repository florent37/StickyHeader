package com.github.florent37.recycler.stickyheader.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.recycler.stickyheader.sample.adapter.SimpleUserRecyclerAdapter
import com.github.florent37.recycler.stickyheader.sample.adapter.UserRecyclerAdapter
import com.github.florent37.recycler.stickyheader.sample.model.User
import com.github.florent37.recycler.stickyheader.sample.model.UserHeader
import com.github.florent37.recycler.stickyheader.sample.model.UserModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = SimpleUserRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val usersModels = listOf<UserModel>(
            UserHeader(title = "Familly"),
            User(name="Florent Champigny"),
            User(name="Théo Champigny"),
            User(name="Camille Champigny"),
            User(name="Flocon Champigny"),

            UserHeader(title = "Singers"),
            User(name="Francis Lallane"),
            User(name="Jennifer"),
            User(name="Florent Pagny"),
            User(name="Mika"),
            User(name="Jul"),

            UserHeader(title = "Old Singers"),
            User(name="Charles Aznavour"),
            User(name="Johnny Halliday"),
            User(name="Georges Brassens"),
            User(name="Jean Ferrat"),
            User(name="Alain Bashung"),
            User(name="Serge Gainsbourg"),
            User(name="Renaud"),
            User(name="Elvis Presley")
        )

        adapter.items = usersModels


    }

}