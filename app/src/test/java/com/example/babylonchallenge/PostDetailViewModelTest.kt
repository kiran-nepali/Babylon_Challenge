package com.example.babylonchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.model.comments.Comment
import com.example.babylonchallenge.data.model.users.User
import com.example.babylonchallenge.data.repository.PostRepository
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
class PostUserCommentViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val errorObserver: Observer<String> = mock()
    private val userObserver: Observer<List<User>> = mock()
    private val commentObserver: Observer<Int> = mock()

    @Mock
    lateinit var repository: PostRepository
    private lateinit var postDetailViewModel: PostDetailViewModel

    @Before
    fun setUp() {
        this.postDetailViewModel = PostDetailViewModel(repository)
        postDetailViewModel.usersLiveData.observeForever(userObserver)
        postDetailViewModel.numberOfComments.observeForever(commentObserver)
        postDetailViewModel.errorsLiveData.observeForever(errorObserver)
    }

    @Test
    fun getPostReturnsEmptyList() {
        Mockito.`when`(repository.getPostInfo(0)).thenReturn(Single.just(emptyList()))
        postDetailViewModel.individualPost(0)

        Mockito.verify(postObserver).onChanged(emptyList())
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }


    @Test
    fun getPostReturnsData() {
        val posts = mutableListOf(Post(1, 2, "Test", "Test"))
        Mockito.`when`(repository.getPostInfo(2)).thenReturn(Single.just(posts))
        postDetailViewModel.individualPost(2)

        Mockito.verify(postObserver).onChanged(posts)
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())

    }

    @Test
    fun getPostReturnsError() {
        val errorMessage = "My Error"
        Mockito.`when`(repository.getPostInfo(1))
            .thenReturn(Single.error(RuntimeException(errorMessage)))
        postDetailViewModel.individualPost(1)

        Mockito.verify(postObserver, never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(errorObserver).onChanged(errorMessage)
    }

    @Test
    fun getUserReturnsEmptylist() {
        Mockito.`when`(repository.getUsers(1)).thenReturn(Single.just(emptyList()))
        postDetailViewModel.getUserId(1)

        Mockito.verify(userObserver).onChanged(emptyList())
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun getUserRetunsData() {
        val user = mutableListOf(
            Users(
                1,
                "kiran",
                "test",
                "test",
                (Address("test", "test", "test", "test", Geo(2.2, 2.4))),
                "test",
                "test",
                Company("test", "test", "test")
            )
        )
        Mockito.`when`(repository.getUsers(1)).thenReturn(Single.just(user))
        postDetailViewModel.getUserId(1)
        Mockito.verify(userObserver).onChanged(user)
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun getCommentEmptyList() {
        Mockito.`when`(repository.getComments(1)).thenReturn(Single.just(emptyList()))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentObserver).onChanged(emptyList())
    }

    @Test
    fun getCommentData() {
        val comment = mutableListOf(Comments(1, 2, "test", "test", "test"))
        Mockito.`when`(repository.getComments(1)).thenReturn(Single.just(comment))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentObserver).onChanged(comment)
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun getCommentError() {
        val errormsg = "Error"
        Mockito.`when`(repository.getComments(1))
            .thenReturn(Single.error(java.lang.RuntimeException(errormsg)))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentObserver, never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(errorObserver).onChanged(errormsg)
    }
}