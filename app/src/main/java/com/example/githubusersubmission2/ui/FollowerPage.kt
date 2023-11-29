package com.example.githubusersubmission2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersubmission2.databinding.FragmentFollowerPageBinding

class FollowerPage : Fragment() {
    private var position = 0
    private var username: String = ""
    private lateinit var binding: FragmentFollowerPageBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME) ?: "Rohman"
        }

        detailViewModel.getFollowing(username)
        detailViewModel.getFollower(username)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollower.layoutManager = layoutManager

        detailViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        if (position == 1) {
            detailViewModel.userFollower.observe(viewLifecycleOwner) {
                val adapter = ReviewAdapter()
                adapter.submitList(it)
                binding.rvFollower.adapter = adapter
            }
        } else {
            detailViewModel.userFollowing.observe(viewLifecycleOwner) {
                val adapter = ReviewAdapter()
                adapter.submitList(it)
                binding.rvFollower.adapter = adapter
            }
        }
    }

    companion object {
        const val ARG_USERNAME = "Farhan"
        const val ARG_POSITION = "0"
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}