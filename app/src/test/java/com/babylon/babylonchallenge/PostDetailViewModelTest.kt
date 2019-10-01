package com.babylon.babylonchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.babylon.babylonchallenge.data.model.comments.Comment
import com.babylon.babylonchallenge.data.model.users.User
import com.babylon.babylonchallenge.data.repository.PostRepository
import com.nhaarman.mockitokotlin2.any
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostDetailViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val errorObserver: Observer<String> = mock()
    private val userNameObserver: Observer<String> = mock()
    private val commentsCountObserver: Observer<Int> = mock()

    @Mock
    lateinit var repository: PostRepository
    private lateinit var postDetailViewModel: PostDetailViewModel

    @Before
    fun setUp() {
        this.postDetailViewModel = PostDetailViewModel(repository)
        postDetailViewModel.usersLiveData.observeForever(userNameObserver)
        postDetailViewModel.numberOfComments.observeForever(commentsCountObserver)
        postDetailViewModel.errorsLiveData.observeForever(errorObserver)
    }


    @Test
    fun getUserReturnsEmptyList() {
        Mockito.`when`(repository.getUsers(1)).thenReturn(Single.just(emptyList()))
        postDetailViewModel.getUserName(1)

        Mockito.verify(userNameObserver, never()).onChanged(any())
        Mockito.verify(errorObserver).onChanged("No User Found")
    }

    @Test
    fun getUserReturnsData() {
        val user = mutableListOf(
            User(
                1,
                "kiran",
                "test"
            )
        )
        Mockito.`when`(repository.getUsers(1)).thenReturn(Single.just(user))
        postDetailViewModel.getUserName(1)
        Mockito.verify(userNameObserver).onChanged("kiran")
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun getCommentEmptyList() {
        Mockito.`when`(repository.getComments(1)).thenReturn(Single.just(emptyList()))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentsCountObserver).onChanged(0)
    }

    @Test
    fun getCommentData() {
        val comment = mutableListOf(Comment(1, 2, "test", "test", "test"))
        Mockito.`when`(repository.getComments(1)).thenReturn(Single.just(comment))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentsCountObserver).onChanged(1)
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun getCommentError() {
        val errorMessage = "Error"
        Mockito.`when`(repository.getComments(1))
            .thenReturn(Single.error(java.lang.RuntimeException(errorMessage)))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentsCountObserver, never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(errorObserver).onChanged(errorMessage)
    }
}