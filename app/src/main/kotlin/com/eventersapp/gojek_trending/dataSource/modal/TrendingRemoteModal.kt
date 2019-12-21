package com.eventersapp.gojek_trending.dataSource.modal

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "author",
    "name",
    "avatar",
    "url",
    "description",
    "language",
    "languageColor",
    "stars",
    "forks"
)
class TrendingRemoteModal(
    @JsonProperty("author")
    val author: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("avatar")
    val avatar: String,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("language")
    val language: String,
    @JsonProperty("languageColor")
    val languageColor: String,
    @JsonProperty("stars")
    val stars: Int,
    @JsonProperty("forks")
    val forks: Int
)