package com.example.babylonchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.model.comments.Comments
import com.example.babylonchallenge.data.model.users.Address
import com.example.babylonchallenge.data.model.users.Company
import com.example.babylonchallenge.data.model.users.Geo
import com.example.babylonchallenge.data.model.users.Users
import com.example.babylonchallenge.data.repository.PostRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostUserCommentViewModelTest {

    @Rule
    @JvmField
    var rule:TestRule = InstantTaskExecutorRule()

    private val postObserver:Observer<List<Post>> = mock()
    private val errorObserver:Observer<String> = mock()
    private val userObserver:Observer<List<Users>> = mock()
    private val commentObserver:Observer<List<Comments>> = mock()

    @Mock
    lateinit var repository:PostRepository
    private lateinit var postDetailViewModel: PostDetailViewModel

    @Before
    fun setUp() {
        this.postDetailViewModel = PostDetailViewModel(repository)
        postDetailViewModel.userpostinfo.observeForever(postObserver)
        postDetailViewModel.userIdinfo.observeForever(userObserver)
        postDetailViewModel.commentinfo.observeForever(commentObserver)
        postDetailViewModel.errors.observeForever(errorObserver)
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
        Mockito.`when`(repository.getPostInfo(1)).thenReturn(Single.error(RuntimeException(errorMessage)))
        postDetailViewModel.individualPost(1)

        Mockito.verify(postObserver, never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(errorObserver).onChanged(errorMessage)
    }

    @Test
    fun getUserReturnsEmptylist(){
        Mockito.`when`(repository.getUserInfo(1)).thenReturn(Single.just(emptyList()))
        postDetailViewModel.getUserId(1)

        Mockito.verify(userObserver).onChanged(emptyList())
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun getUserRetunsData(){
        val user = mutableListOf(Users(1,"kiran","test","test", (Address("test","test","test","test", Geo(2.2,2.4))),"test","test",
            Company("test","test","test")
        ))
        Mockito.`when`(repository.getUserInfo(1)).thenReturn(Single.just(user))
        postDetailViewModel.getUserId(1)
        Mockito.verify(userObserver).onChanged(user)
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun getCommentEmptyList(){
        Mockito.`when`(repository.getNumOfComments(1)).thenReturn(Single.just(emptyList()))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentObserver).onChanged(emptyList())
    }

    @Test
    fun getCommentData(){
        val comment = mutableListOf(Comments(1,2,"test","test","test"))
        Mockito.`when`(repository.getNumOfComments(1)).thenReturn(Single.just(comment))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentObserver).onChanged(comment)
        Mockito.verify(errorObserver, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun getCommentError(){
        val errormsg = "Error"
        Mockito.`when`(repository.getNumOfComments(1)).thenReturn(Single.error(java.lang.RuntimeException(errormsg)))
        postDetailViewModel.getComments(1)
        Mockito.verify(commentObserver, never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(errorObserver).onChanged(errormsg)
    }
}