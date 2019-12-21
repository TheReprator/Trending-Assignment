package com.eventersapp.gojek_trending.domain.baseUseCase

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

fun <F, T> Mapper<F, T>.toListMapper(): suspend (List<F>) -> List<T> {
    return { list -> list.map { item -> map(item) } }
}