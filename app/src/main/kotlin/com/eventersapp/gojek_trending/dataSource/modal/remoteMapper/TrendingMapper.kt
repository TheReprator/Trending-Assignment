package com.eventersapp.gojek_trending.dataSource.modal.remoteMapper

import com.eventersapp.gojek_trending.dataSource.modal.TrendingRemoteModal
import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.Mapper
import javax.inject.Inject

class TrendingMapper @Inject constructor(
) : Mapper<TrendingRemoteModal, TrendingModal> {

    override suspend fun map(from: TrendingRemoteModal): TrendingModal {
        return TrendingModal(
            from.author, from.name, from.avatar, from.url, from.description, from.language,
            from.languageColor, from.stars, from.forks
        )
    }
}