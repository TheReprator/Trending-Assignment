package com.eventersapp.gojek_trending.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.ErrorResult
import com.eventersapp.gojek_trending.domain.baseUseCase.Success
import com.eventersapp.gojek_trending.domain.trendingUseCase.TrendingUseCase
import com.eventersapp.gojek_trending.util.CoroutineDispatcherProvider
import com.eventersapp.gojek_trending.util.event.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingRepoViewModal @Inject constructor(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val trendingUseCase: TrendingUseCase
) : ViewModel() {

    private val _trendingList = MutableLiveData<MutableList<TrendingModal>>()
    val trendingList: LiveData<MutableList<TrendingModal>>
        get() = _trendingList

    private val _isLoading = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>>
        get() = _isLoading

    private val _isError = MutableLiveData<Event<Boolean>>()
    val isError: LiveData<Event<Boolean>>
        get() = _isError

    init {
        getTrendingUseCase()
    }

    private fun getTrendingUseCase() {
        _isLoading.value = Event(true)

        viewModelScope.launch(coroutineDispatcherProvider.io) {
            val result = trendingUseCase.run(Unit)
            withContext(coroutineDispatcherProvider.main)
            {
                _isLoading.value = Event(false)

                when (result) {
                    is Success -> {
                        _trendingList.value = result.data.toMutableList()
                    }

                    is ErrorResult -> {
                        _isError.value = Event(true)
                    }
                }
            }
        }
    }

    fun retry() {
        _isError.value = Event(false)

        getTrendingUseCase()
    }
}