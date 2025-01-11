package com.example.story.view.start

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.story.R
import com.example.story.databinding.ActivityLoginBinding
import com.example.story.livedata.preference.manager.ModelUser
import com.example.story.livedata.preference.repo.Results
import com.example.story.view.FactoryView
import com.example.story.view.data.model.LoginModel
import com.example.story.view.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginModel> {
        FactoryView.getInstance(this)
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAction()
        startAnimation()

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }


    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(email, password).observe(this) { result ->
                when (result) {
                    is Results.Loading -> {
                        setLoadingIndicator(true)
                    }
                    is Results.Success -> {
                        val data = result.result.loginResult
                        data?.let {
                            viewModel.saveSession(ModelUser(it.name.toString(), it.userId.toString(), it.token.toString()))
                            navigateToHome()
                        }
                        setLoadingIndicator(false)
                    }
                    is Results.Error -> {
                        setLoadingIndicator(false)
                        Toast.makeText(this, R.string.failedLogin, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setLoadingIndicator(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun navigateToHome() {
        Intent(this, HomeActivity::class.java).apply {
            FactoryView.clearInstance()
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
            finish()
        }
    }

    private fun startAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_Y, -20f, 20f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val titleAnim = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(1000)
        val messageAnim = ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(1000)
        val emailLayoutAnim = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(750)
        val passwordLayoutAnim = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(750)
        val loginButtonAnim = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(750)
        val registerButtonAnim = ObjectAnimator.ofFloat(binding.registerLayout, View.ALPHA, 1f).setDuration(750)

        AnimatorSet().apply {
            playSequentially(
                titleAnim,
                messageAnim,
                emailLayoutAnim,
                passwordLayoutAnim,
                loginButtonAnim,
                registerButtonAnim
            )
            startDelay = 100
        }.start()
    }
}
