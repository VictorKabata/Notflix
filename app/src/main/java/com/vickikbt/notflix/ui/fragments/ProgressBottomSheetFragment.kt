package com.vickikbt.notflix.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.FragmentProgressBottomSheetBinding
import com.vickikbt.notflix.util.toMegabytes
import timber.log.Timber

class ProgressBottomSheetFragment constructor(private val state: SplitInstallSessionState) :
    BottomSheetDialogFragment() {

    private var _binding: FragmentProgressBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBottomSheetBinding.inflate(inflater, container, false)

        initUI()

        return binding.root
    }

    private fun initUI() {
        when (state.status()) {

            SplitInstallSessionStatus.PENDING -> {
                val totalByte = state.totalBytesToDownload().toMegabytes()
                val progressBytes = state.bytesDownloaded().toMegabytes()

                binding.textViewFeatureTitle.text = "Downloading feature"
                binding.textViewFeatureSize.text = "Downloading ${progressBytes}mb/${totalByte}mb"
                binding.progressBarDownload.progress = progressBytes
                binding.textViewAction.visibility=View.GONE

            }

            SplitInstallSessionStatus.DOWNLOADING -> {
                val totalByte = state.totalBytesToDownload().toMegabytes()
                val progressBytes = state.bytesDownloaded().toMegabytes()

                val retryText = resources.getString(R.string.retry)
                binding.textViewFeatureTitle.text = "Downloading feature"
                binding.textViewFeatureSize.text = "Downloading ${progressBytes}mb/${totalByte}mb"
                binding.progressBarDownload.max = totalByte
                binding.progressBarDownload.progress = progressBytes
                binding.textViewAction.text = retryText

            }

            SplitInstallSessionStatus.DOWNLOADED -> {
                Timber.e("Downloaded")
            }

            SplitInstallSessionStatus.PENDING -> {
                val totalByte = state.totalBytesToDownload()
                val progressBytes = state.bytesDownloaded()

                Timber.e("Pending: ${progressBytes.toMegabytes()}")

            }

            SplitInstallSessionStatus.INSTALLING -> {
                val totalByte = state.totalBytesToDownload()
                val progressBytes = state.bytesDownloaded()

                Timber.e("Installing: ${progressBytes.toMegabytes()}")

            }

            SplitInstallSessionStatus.CANCELED -> {
                Timber.e("Downloaded")
            }

            SplitInstallSessionStatus.CANCELING -> {
                Timber.e("Cancelling")
            }

            SplitInstallSessionStatus.FAILED -> {
                val totalByte = state.totalBytesToDownload().toMegabytes()
                val progressBytes = state.bytesDownloaded().toMegabytes()

                val retryText = resources.getString(R.string.retry)
                binding.textViewFeatureTitle.text = "Download Failed"
                binding.textViewFeatureSize.text = "Failed ${progressBytes}mb/${totalByte}mb"
                binding.progressBarDownload.hide()
                binding.textViewAction.text = retryText
            }
        }

    }

    private fun cancelFeatureDownload() {

    }

    private fun retryFeatureDownload() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}