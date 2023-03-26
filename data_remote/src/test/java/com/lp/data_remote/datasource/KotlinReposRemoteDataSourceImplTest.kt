package com.lp.data_remote.datasource

import com.lp.data.mapper.GithubKotlinReposMapper
import com.lp.data_remote.mocks.KotlinReposRemoteDataSourceFactory.DUMMY_GITHUB_KOTLIN_REPOS_RESPONSE
import com.lp.data_remote.mocks.KotlinReposRemoteDataSourceFactory.LANGUAGE_PARAM
import com.lp.data_remote.mocks.KotlinReposRemoteDataSourceFactory.PAGE_PARAM
import com.lp.data_remote.mocks.KotlinReposRemoteDataSourceFactory.SORT_PARAM
import com.lp.data_remote.service.SearchRepoWebService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class KotlinReposRemoteDataSourceImplTest {

    private lateinit var dataSource: KotlinReposRemoteDataSourceImpl

    private var webService: SearchRepoWebService = mockk(relaxed = true)
    private val testModule = module {
        single { webService }
    }

    @Before
    fun before() {
        startKoin { modules(testModule) }
        dataSource = KotlinReposRemoteDataSourceImpl(webService)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getGithubKotlinRepositoriesMustReturnGithubKotlinReposResponseWhenCall() = runBlocking {
        stubGetGithubKotlinRepositoriesSuccess()

        val data = GithubKotlinReposMapper.toDomain(DUMMY_GITHUB_KOTLIN_REPOS_RESPONSE)
        val result = dataSource.getGithubKotlinRepositories(
            LANGUAGE_PARAM,
            SORT_PARAM,
            PAGE_PARAM
        ).first()

        Assert.assertEquals(data, result)
    }

    private suspend fun stubGetGithubKotlinRepositoriesSuccess() {
        coEvery {
            webService.getKotlinRepos(
                LANGUAGE_PARAM,
                SORT_PARAM,
                PAGE_PARAM
            )
        } returns DUMMY_GITHUB_KOTLIN_REPOS_RESPONSE
    }
}