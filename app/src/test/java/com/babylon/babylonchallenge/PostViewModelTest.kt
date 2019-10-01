package com.babylon.babylonchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.babylon.babylonchallenge.data.model.Post
import com.babylon.babylonchallenge.data.repository.PostRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val postsObserver: Observer<List<Post>> = mock()
    private val errorObserver: Observer<String> = mock()

    @Mock
    lateinit var repository: PostRepository
    private lateinit var postViewModel: PostViewModel


    @Before
    fun setUp() {
        this.postViewModel = PostViewModel(repository)
        postViewModel.postData.observeForever(postsObserver)
        postViewModel.errors.observeForever(errorObserver)
    }


    @Test
    fun getPostReturnsEmptyList() {
        `when`(repository.getPosts()).thenReturn(Single.just(emptyList()))
        postViewModel.getPosts()

        verify(postsObserver).onChanged(emptyList())
        verify(errorObserver, never()).onChanged(ArgumentMatchers.any())

    }


    @Test
    fun getPostReturnsData() {
        val posts = mutableListOf(Post(1, 2, "Test", "Test"))
        `when`(repository.getPosts()).thenReturn(Single.just(posts))
        postViewModel.getPosts()

        verify(postsObserver).onChanged(posts)
        verify(errorObserver, never()).onChanged(ArgumentMatchers.any())

    }


    @Test
    fun getPostReturnsError() {
        val errorMessage = "My Error"
        `when`(repository.getPosts()).thenReturn(Single.error(RuntimeException(errorMessage)))
        postViewModel.getPosts()

        verify(postsObserver, never()).onChanged(ArgumentMatchers.any())
        verify(errorObserver).onChanged(errorMessage)

    }

}