package com.vickikbt.notflix.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.FragmentProgressBinding

class CustomProgressFragment : AbstractProgressFragment(R.layout.fragment_progress) {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    private lateinit var fadeInAnimation:Animation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProgressBinding.bind(view)

        fadeInAnimation=AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in)
    }

    override fun onCancelled() {
        val retryText = requireActivity().resources.getString(R.string.retry)
        binding.buttonDownload.text = retryText
        binding.textViewStatus.apply {
            startAnimation(fadeInAnimation)
            text = "Module download cancelled"
        }

        binding.progressBarDownload.hide()

        binding.buttonDownload.setOnClickListener { retryDownload() }
    }

    override fun onFailed(errorCode: Int) {
        val retryText = requireActivity().resources.getString(R.string.retry)
        binding.buttonDownload.text = retryText
        binding.textViewStatus.apply {
            startAnimation(fadeInAnimation)
            text = "Module download failed"
        }

        binding.progressBarDownload.hide()

        binding.buttonDownload.setOnClickListener { retryDownload() }
    }

    override fun onProgress(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        val progress = (bytesDownloaded.toDouble() * 100) / bytesTotal

        binding.progressBarDownload.progress = progress.toInt()
        binding.textViewStatus.apply {
            startAnimation(fadeInAnimation)
            text = "Downloading module"
        }

        binding.buttonDownload.setOnClickListener { cancelDownload() }
    }

    private fun cancelDownload() {

    }

    private fun retryDownload() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}