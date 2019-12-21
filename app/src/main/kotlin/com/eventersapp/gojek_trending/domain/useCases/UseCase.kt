package com.eventersapp.gojek_trending.domain.useCases

interface UseCase<Type, in Params> {
     suspend fun run(params: Params): Result<Type>
}