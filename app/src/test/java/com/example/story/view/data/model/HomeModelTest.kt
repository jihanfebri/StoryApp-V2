package com.example.story.view.data.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.story.livedata.DataTest
import com.example.story.livedata.MainDispatcher
import com.example.story.livedata.getOrAwaitValue
import com.example.story.livedata.preference.repo.DataStoryRepo
import com.example.story.livedata.respon.ListStoryItem
import com.example.story.view.data.adapter.HomeAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)


class HomeModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcher = MainDispatcher()

    @Mock
    private lateinit var dataStoryRepository: DataStoryRepo

    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }

    @Test
    fun `when getStory Should Not Null and return Success`() = runTest {
        val dummyHomeResponse = DataTest.generateDummyStoryResponse()
        val data: PagingData<ListStoryItem> = PagingData.from(dummyHomeResponse.listStory)
        val expectedStoryItem = MutableLiveData<PagingData<ListStoryItem>>().apply { value = data }
        Mockito.`when`(dataStoryRepository.getStory()).thenReturn(expectedStoryItem)

        val listHomeViewModel = HomeModel(dataStoryRepository)
        val actualStory: PagingData<ListStoryItem> = listHomeViewModel.getStory.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = HomeAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(actualStory)

        assertNotNull(differ.snapshot())
        assertEquals(dummyHomeResponse.listStory, differ.snapshot())
        assertEquals(dummyHomeResponse.listStory.size, differ.snapshot().size)
        assertEquals(dummyHomeResponse.listStory[0], differ.snapshot()[0])
    }

    @Test
    fun `when getStory Empty Should Return No Data`() = runTest {
        val data: PagingData<ListStoryItem> = PagingData.empty()
        val expectedStoryItem = MutableLiveData<PagingData<ListStoryItem>>().apply { value = data }
        Mockito.`when`(dataStoryRepository.getStory()).thenReturn(expectedStoryItem)

        val listHomeViewModel = HomeModel(dataStoryRepository)
        val actualStory: PagingData<ListStoryItem> = listHomeViewModel.getStory.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = HomeAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(actualStory)

        assertEquals(0, differ.snapshot().size)
    }
}

