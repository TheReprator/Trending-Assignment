package com.eventersapp.gojek_trending.domain.useCases

interface Mapper<in InputModal, out OutputModal> {
    suspend fun map(from: InputModal): OutputModal
}

interface MapperFromTo<InputModal, OutputModal> {
    suspend fun mapFrom(from: InputModal): OutputModal
    suspend fun mapTo(from: OutputModal): InputModal
}

interface IndexedMapper<F, T> {
    suspend fun map(index: Int, from: F): T
}