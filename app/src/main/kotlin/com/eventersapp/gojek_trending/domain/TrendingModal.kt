package com.eventersapp.gojek_trending.domain

data class TrendingModal (
    val author: String,
    val name: String,
    val avatar: String,
    val url: String,
    val description: String,
    val language: String,
    val languageColor: String,
    val stars: Int,
    val forks: Int,
    val isCollapsed: Boolean = true)