package com.example.githubusersubmission2.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersubmission2.data.response.ItemsItem
import com.example.githubusersubmission2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val ViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        with(binding) {
            srView.setupWithSearchBar(srBar)
            srView.editText.setOnEditorActionListener { textView, actionId, event ->
                srBar.text = srView.text
                srView.hide()
                ViewModel.findUser(srView.text.toString())
                ViewModel.User.observe(this@MainActivity) {
                    if (it.isNullOrEmpty()) {
                        ifUserNotFound(true)
                    } else {
                        ifUserNotFound(false)
                    }
                }
                false
            }
        }
        ViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        ViewModel.User.observe(this) {
            if (it != null) {
                setUserData(it)
            }
        }
    }

    private fun setUserData(dataUser: List<ItemsItem>) {
        val adapter = GitHubAdapter()
        adapter.submitList(dataUser)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun ifUserNotFound(isDataNotFound: Boolean) {
        binding.apply {
            if (isDataNotFound) {
                rvUser.visibility = View.GONE
                tvNotFound.visibility = View.VISIBLE
            } else {
                rvUser.visibility = View.VISIBLE
                tvNotFound.visibility = View.GONE
            }
        }
    }

}