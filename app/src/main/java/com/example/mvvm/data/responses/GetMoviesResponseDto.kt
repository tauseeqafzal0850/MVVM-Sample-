package com.example.mvvm.data.responses

data class GetMoviesResponseDto(
    var page: Int? = 0,
    var results: List<Result>? = listOf(),
    var total_pages: Int? = 0,
    var total_results: Int? = 0
) {
    data class Result(
        var adult: Boolean? = false,
        var backdrop_path: String? = "",
        var genre_ids: List<Int?>? = listOf(),
        var id: Int? = 0,
        var original_language: String? = "",
        var original_title: String? = "",
        var overview: String? = "",
        var popularity: Double? = 0.0,
        var poster_path: String? = "",
        var release_date: String? = "",
        var title: String? = "",
        var video: Boolean? = false,
        var vote_average: Double? = 0.0,
        var vote_count: Int? = 0
    )
}