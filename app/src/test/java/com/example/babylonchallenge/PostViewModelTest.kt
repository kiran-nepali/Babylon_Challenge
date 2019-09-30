package com.example.babylonchallenge

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.babylonchallenge.model.Post
import com.example.babylonchallenge.network.GetRequest
import com.nhaarman.mockitokotlin2.doReturn
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {


    @Mock
    lateinit var clientInterface:GetRequest
    lateinit var postViewModel: PostViewModel

    @Mock
    lateinit var list:Observable<List<Post>>

    @Mock
    lateinit var postmutabledata:MutableLiveData<List<Post>>



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.postViewModel = PostViewModel(clientInterface)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getPostObserver() {
    }

    @Test
    fun getCompositeDisposable() {
    }

    @Test
    fun postInfo() {
        `when`(postViewModel.clientInterface.getPostRequest()).thenReturn(list)
//        val postobserver = mock(Observer::class.java) as Observer<List<Post>>
//        this.postViewModel.postData?.observeForever(postobserver)
//        this.postViewModel.postInfo()
//        assertNotNull(this.postViewModel.postData?.value)
    }

    @Test
    fun postData() {
    }

    @Test
    fun getClientInterface() {
    }
}