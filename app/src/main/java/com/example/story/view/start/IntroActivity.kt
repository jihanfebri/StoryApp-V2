package com.example.story.view.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.story.databinding.ActivityIntroBinding
import com.example.story.view.FactoryView
import com.example.story.view.data.model.IntroModel
import com.example.story.view.home.HomeActivity

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding

    private val viewModel by viewModels<IntroModel> {
        FactoryView.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.buttonStart.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }



    }
}
