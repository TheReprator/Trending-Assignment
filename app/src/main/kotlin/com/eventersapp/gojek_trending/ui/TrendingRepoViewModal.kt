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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingRepoViewModal @Inject constructor(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val trendingUseCase: TrendingUseCase
) : ViewModel() {

    private var selectedRecyclerPosition = -1

    private val _trendingList = MutableLiveData<List<TrendingModal>>()
    val trendingList: LiveData<List<TrendingModal>>
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

        getTrendingUseCaseWithPullToRefresh()
    }

    fun getTrendingUseCaseWithPullToRefresh() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = trendingUseCase.run(Unit)
            withContext(Dispatchers.Main) {
                _isLoading.value = Event(false)

                when (result) {
                    is Success -> {
                        _trendingList.value = result.data
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

    fun positionClicked(selectedPosition: Int) {
        when (selectedPosition) {
            -1 -> {
                this.selectedRecyclerPosition = selectedPosition

                val originalList = _trendingList.value!!.toMutableList()
                originalList[selectedPosition] =
                    originalList[selectedPosition].copy(isCollapsed = false)

                _trendingList.value = originalList
            }
            this.selectedRecyclerPosition -> {
                val originalList = _trendingList.value!!.toMutableList()
                val item = originalList[selectedPosition]
                originalList[selectedPosition] = item.copy(isCollapsed = !item.isCollapsed)

                _trendingList.value = originalList
            }
            else -> {
                val originalList = _trendingList.value!!
                val newSelectedItem = originalList[selectedPosition].copy(isCollapsed = false)

                val listData = _trendingList.value!!.toMutableList()
                listData[selectedPosition] = newSelectedItem

                val previous = this.selectedRecyclerPosition
                this.selectedRecyclerPosition = selectedPosition

                if (previous != -1) {
                    val previousItem = originalList[previous].copy(isCollapsed = true)
                    listData[previous] = previousItem
                }
                _trendingList.value = listData
            }
        }
    }
}
