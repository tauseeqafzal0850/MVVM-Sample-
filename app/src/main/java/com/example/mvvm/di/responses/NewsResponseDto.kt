package com.example.mvvm.di.responses

data class NewsResponseDto(
    var articles: List<Article>? = listOf(),
    var status: String? = "",
    var totalResults: Int? = 0
) {
    data class Article(
        var author: String? = "",
        var content: String? = "",
        var description: String? = "",
        var publishedAt: String? = "",
        var source: Source? = Source(),
        var title: String? = "",
        var url: String? = "",
        var urlToImage: String? = ""
    ) {
        data class Source(
            var id: String? = "",
            var name: String? = ""
        )
    }
}