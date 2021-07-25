package com.vickikbt.notflix.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.FragmentProgressBinding

class ProgressFragment : AbstractProgressFragment(R.layout.fragment_progress) {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProgressBinding.bind(view)

    }

    override fun onCancelled() {
        val retryText = requireActivity().resources.getString(R.string.retry)
        binding.buttonDownload.text = retryText
        binding.textViewStatus.text = "Module download cancelled"

        binding.buttonDownload.setOnClickListener { retryDownload() }
    }

    override fun onFailed(errorCode: Int) {
        val retryText = requireActivity().resources.getString(R.string.retry)
        binding.buttonDownload.text = retryText
        binding.textViewStatus.text = "Module download failed"

        binding.buttonDownload.setOnClickListener { retryDownload() }
    }

    override fun onProgress(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        val progress = (bytesDownloaded.toDouble() * 100) / bytesTotal

        binding.progressBarDownload.progress = progress.toInt()
        binding.textViewStatus.text = "Downloading module"

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