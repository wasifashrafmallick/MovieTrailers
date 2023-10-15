package com.example.movietrailers.data.models



enum class MovieType( val type: String){
    Popular("popular"),
    TopRated("top_rated"),
    NowPlaying("now_playing"),
    UpComing("upcoming")
}