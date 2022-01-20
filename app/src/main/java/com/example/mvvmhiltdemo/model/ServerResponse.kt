package com.example.mvvmhiltdemo.model

data class ServerResponse(
    val page: Int,
    val results: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)