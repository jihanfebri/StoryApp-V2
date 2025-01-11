package com.example.story.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.story.databinding.ActivityDetailBinding
import com.example.story.livedata.respon.ListStoryItem
import com.example.story.view.data.adapter.HomeAdapter

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HomeAdapter()
        supportActionBar?.hide()

        @Suppress("DEPRECATION")
        val detailStory = intent.getParcelableExtra<ListStoryItem>(DETAIL_STORY)
        populateData(detailStory)

        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun populateData(storyItem: ListStoryItem?) {
        storyItem?.let {
            binding.tvName.text = it.name
            binding.tvDescription.text = it.description
            binding.imgPhotos.setImageResource(0)
            Glide.with(binding.imgPhotos).load(it.photoUrl).into(binding.imgPhotos)
        }
    }

    companion object {
        const val DETAIL_STORY = "story_detail"
    }
}
