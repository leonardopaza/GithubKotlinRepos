package com.lp.data_local.datasource

import android.content.Context
import androidx.room.Room
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lp.data.datasource.local.KotlinReposLocalDataSource
import com.lp.data_local.database.AppDatabase
import com.lp.data_local.mocks.KotlinReposLocalDataSourceFactory.DUMMY_GITHUB_KOTLIN_REPOS_LIST
import com.lp.data_local.mocks.KotlinReposLocalDataSourceFactory.PAGE_PARAM
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KotlinReposLocalDataSourceImplTest {
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dataSource: KotlinReposLocalDataSource

    @Before
    fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dataSource = KotlinReposLocalDataSourceImpl(database)
    }

    @After
    fun after() {
        database.close()
    }

    @Test
    fun saveGithubKotlinRepositories() = runBlocking {
        val result = dataSource.saveGithubKotlinRepositories(DUMMY_GITHUB_KOTLIN_REPOS_LIST)
        Assert.assertEquals(true, result)
    }

    @Test
    fun getGithubKotlinRepositories() = runBlocking {
        val result = dataSource.getGithubKotlinRepositories(PAGE_PARAM)
        Assert.assertEquals(DUMMY_GITHUB_KOTLIN_REPOS_LIST, result)
    }
}