package com.example.story.view.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.story.R
import com.example.story.databinding.ActivityHomeBinding
import com.example.story.view.FactoryView
import com.example.story.view.data.adapter.HomeAdapter
import com.example.story.view.data.adapter.LoadingAdapter
import com.example.story.view.data.model.HomeModel
import com.example.story.view.start.LoginActivity
import com.example.story.view.maps.MapsActivity
import com.example.story.view.story.StoryActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val viewModel by viewModels<HomeModel> {
        FactoryView.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        swipeRefreshLayout = findViewById(R.id.swipe_refresh)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        displayStories()

        val layoutManager = LinearLayoutManager(this)
        binding.rvStory.layoutManager = layoutManager

        val itemDecorations = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvStory.addItemDecoration(itemDecorations)

        binding.fbAddStory.setOnClickListener {
            startActivity(Intent(this, StoryActivity::class.java))
        }

        binding.iconMaps.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.account_menu -> {
                    val intent = Intent(this, SettingActivity::class.java)
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
                    true
                }
                else -> false
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun displayStories() {
        val adapter = HomeAdapter()
        binding.rvStory.adapter = adapter.withLoadStateFooter(
            footer = LoadingAdapter {
                adapter.retry()
            }
        )
        viewModel.getStory.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }



    override fun onResume() {
        super.onResume()
        viewModel.getStory
    }
}
