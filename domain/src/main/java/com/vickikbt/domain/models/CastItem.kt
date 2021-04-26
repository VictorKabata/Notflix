package com.vickikbt.domain.models

data class CastItem(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)