package com.company.details.ui.fragments

import android.os.Bundle
import androidx.navigation.NavArgs
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class MovieDetailsFragmentArgs(
  public val movieId: Int = 0
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("movieId", this.movieId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): MovieDetailsFragmentArgs {
      bundle.setClassLoader(MovieDetailsFragmentArgs::class.java.classLoader)
      val __movieId : Int
      if (bundle.containsKey("movieId")) {
        __movieId = bundle.getInt("movieId")
      } else {
        __movieId = 0
      }
      return MovieDetailsFragmentArgs(__movieId)
    }
  }
}
