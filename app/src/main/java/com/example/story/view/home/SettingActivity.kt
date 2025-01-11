package com.example.story.view.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.story.R
import com.example.story.databinding.ActivitySettingBinding
import com.example.story.util.Message
import com.example.story.view.FactoryView
import com.example.story.view.data.model.HomeModel
import com.example.story.view.start.LoginActivity

class SettingActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeModel> {
        FactoryView.getInstance(this)
    }

    private lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val language = binding.root.findViewById<LinearLayout>(R.id.btn_changeLang)

        language.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        binding.iconBack.setOnClickListener { finish() }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnLogout.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle(resources.getString(R.string.logOut))
            dialog.setMessage(getString(R.string.makeSure))
            dialog.setPositiveButton(getString(R.string.yesLogout)) {_,_ ->
                viewModel.logout()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                Message.setMessage(this, getString(R.string.warningLogout))
            }
            dialog.setNegativeButton(getString(R.string.noLogout)) {_,_ ->
                Message.setMessage(this, getString(R.string.noSure))
            }
            dialog.show()
        }
    }
}
