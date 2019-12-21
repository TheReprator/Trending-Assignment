package com.eventersapp.gojek_trending.domain.baseUseCase

interface UseCase<Type, in Params> {
     suspend fun run(params: Params): Result<Type>
}