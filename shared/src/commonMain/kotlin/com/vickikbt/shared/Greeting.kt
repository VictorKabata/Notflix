package com.vickikbt.shared

import com.vickikbt.shared.domain.utils.Platform

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}