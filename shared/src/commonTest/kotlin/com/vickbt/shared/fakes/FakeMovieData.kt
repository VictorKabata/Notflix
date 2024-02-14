package com.vickbt.shared.fakes

import com.vickbt.shared.domain.models.Movie

object FakeMovieData {

    private val movie1 = Movie(
        adult = false,
        backdropPath = "/ pWsD91G2R1Da3AKM3ymr3UoIfRb.jpg",
        id = 933131,
        originalLanguage = "ko",
        originalTitle = "황야",
        overview = "After a deadly earthquake turns Seoul into a lawless badland, a fearless huntsman springs into action to rescue a teenager abducted by a mad doctor.",
        popularity = 2574.303,
        posterPath = "/zVMyvNowgbsBAL6O6esWfRpAcOb.jpg",
        releaseDate = "2024-01-26",
        title = "Badland Hunters",
        video = false,
        voteAverage = 6.7,
        voteCount = 344,
        category = null,
        isFavorite = null,
        cacheId = 0,
        mediaType = null
    )
    private val movie2 = Movie(
        adult = false,
        backdropPath = "/unvtbkgxh47BewQ8pENvdOdme0r.jpg",
        id = 1212073,
        originalLanguage = "de",
        originalTitle = "60 Minuten",
        overview = "Desperate to keep custody of his daughter, a mixed martial arts fighter abandons a big match and races across Berlin to attend her birthday party.",
        popularity = 1548.958,
        posterPath = "/aajCqg315CoJPu1NmgPCkbRjnl6.jpg",
        releaseDate = "2024-01-19",
        title = "Sixty Minutes",
        video = false,
        voteAverage = 6.966,
        voteCount = 298,
        category = null,
        isFavorite = null,
        cacheId = 0,
        mediaType = null
    )
    private val movie3 = Movie(
        adult = false,
        backdropPath = "/yyFc8Iclt2jxPmLztbP617xXllT.jpg",
        id = 787699,
        originalLanguage = "en",
        originalTitle = "Wonka",
        overview = "Willy Wonka – chock-full of ideas and determined to change the world one delectable bite at a time – is proof that the best things in life begin with a dream, and if you’re lucky enough to meet Willy Wonka, anything is possible.",
        popularity = 1244.994,
        posterPath = "/qhb1qOilapbapxWQn9jtRCMwXJF.jpg",
        releaseDate = "2023-12-06",
        title = "Wonka",
        video = false,
        voteAverage = 7.207,
        voteCount = 2059,
        category = null,
        isFavorite = null,
        cacheId = 0,
        mediaType = null
    )

    val movies = listOf<Movie>(movie1, movie2, movie3)
}
