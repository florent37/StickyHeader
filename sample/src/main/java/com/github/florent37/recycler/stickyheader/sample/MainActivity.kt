package com.github.florent37.recycler.stickyheader.sample

import com.github.florent37.recycler.stickyheader.sample.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.recycler.stickyheader.sample.variants.autoattach.ActivityAutoAttach
import com.github.florent37.recycler.stickyheader.sample.variants.parentadapter.ActivityParentAdapter
import com.github.florent37.recycler.stickyheader.sample.variants.tobeattached.ActivityToBeAttached

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        findViewById<View>(R.id.autoAttach).setOnClickListener {
            startActivity(Intent(this, ActivityAutoAttach::class.java))
        }
        findViewById<View>(R.id.toBeAttached).setOnClickListener {
            startActivity(Intent(this, ActivityToBeAttached::class.java))
        }
        findViewById<View>(R.id.parentadapter).setOnClickListener {
            startActivity(Intent(this, ActivityParentAdapter::class.java))
        }
    }

}