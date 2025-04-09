package com.vickbt.composeApp.domain.utils

object Constants {

    const val BASE_URL = "api.themoviedb.org"
    const val URL_PATH = "3/"

    const val STARTING_PAGE_INDEX = 1
    const val PAGING_SIZE = 5

    const val SOURCE_CODE_URL = "https://github.com/VictorKabata/Notflix"

    const val BUG_REPORT_EMAIL = "victorbro14@gmail.com"
    const val BUG_REPORT_SUBJECT = "Notflix-Bug report or feature request"

    const val KEY_THEME = "theme"
    const val KEY_LANGUAGE = "language"
    const val KEY_IMAGE_QUALITY = "image_quality"

    const val DATABASE_NAME = "notflix.db"

    internal const val DATASTORE_FILE_NAME = "notflix.preferences_pb"
}

expect val sdpSspScalingRatio: Double
