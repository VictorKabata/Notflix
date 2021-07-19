package com.company.home.ui.fragments

import android.os.Bundle
import androidx.navigation.NavDirections
import com.company.home.R
import kotlin.Int

public class HomeFragmentDirections private constructor() {
  private data class HomeToDetails(
    public val movieId: Int = 0
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.home_to_details

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putInt("movieId", this.movieId)
      return result
    }
  }

  public companion object {
    public fun homeToDetails(movieId: Int = 0): NavDirections = HomeToDetails(movieId)
  }
}
