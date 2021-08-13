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
import com.vickikbt.notflix.util.hide
import com.vickikbt.notflix.util.toMegabytes
import com.vickikbt.notflix.util.toast
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
                binding.progressBarDownload.apply {
                    max = totalByte
                    progress = totalByte
                }
                binding.textViewAction.hide()

            }

            SplitInstallSessionStatus.DOWNLOADING -> {
                val totalByte = state.totalBytesToDownload().toMegabytes()
                val progressBytes = state.bytesDownloaded().toMegabytes()

                val retryText = resources.getString(R.string.retry)
                binding.textViewFeatureTitle.text = "Downloading feature"
                binding.textViewFeatureSize.text = "Downloading ${progressBytes}mb/${totalByte}mb"
                binding.progressBarDownload.apply {
                    max = totalByte
                    progress = progressBytes
                }
                binding.textViewAction.text = retryText

            }

            SplitInstallSessionStatus.DOWNLOADED -> {
                Timber.e("Downloaded")
            }

            SplitInstallSessionStatus.INSTALLING -> {
                val totalByte = state.totalBytesToDownload().toMegabytes()

                binding.progressBarDownload.apply {
                    max = totalByte
                    progress = totalByte
                }

                binding.textViewFeatureTitle.text = "Installing feature"
                binding.textViewFeatureSize.text = "Installing ${totalByte}mb"
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

            SplitInstallSessionStatus.INSTALLED->{
                requireContext().toast("Feature Downloaded")
                this.dismiss()
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