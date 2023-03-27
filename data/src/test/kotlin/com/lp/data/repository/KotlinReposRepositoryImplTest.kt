package com.lp.data.repository

import com.lp.data.datasource.local.KotlinReposLocalDataSource
import com.lp.data.datasource.remote.KotlinReposRemoteDataSource
import com.lp.data.mocks.KotlinReposRepositoryFactory.DUMMY_GITHUB_KOTLIN_REPOS_LIST
import com.lp.data.mocks.KotlinReposRepositoryFactory.LANGUAGE_PARAM
import com.lp.data.mocks.KotlinReposRepositoryFactory.PAGE_PARAM
import com.lp.data.mocks.KotlinReposRepositoryFactory.SORT_PARAM
import com.lp.data.mocks.TestContextProvider
import com.lp.data.mocks.testFlow
import com.lp.data.mocks.testModule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class KotlinReposRepositoryImplTest {
    @Mock
    private lateinit var dataSource: KotlinReposRemoteDataSource
    private lateinit var repository: KotlinReposRepositoryImpl

    @Mock
    private lateinit var localDataSource: KotlinReposLocalDataSource

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        repository = KotlinReposRepositoryImpl(dataSource, localDataSource)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `getGithubKotlinRepositories MUST return list of GithubKotlinReposModel when has a success`() {
        stubGetGithubKotlinRepositoriesSuccess()
        repository.getGithubKotlinRepositories(
            LANGUAGE_PARAM,
            SORT_PARAM,
            PAGE_PARAM
        ).testFlow {
            assertEquals(DUMMY_GITHUB_KOTLIN_REPOS_LIST, this)
        }
    }

    @Test(expected = Exception::class)
    fun `getGithubKotlinRepositories MUST throw an Exception when has a error`() {
        stubGetGithubKotlinRepositoriesError()
        repository.getGithubKotlinRepositories(
            LANGUAGE_PARAM,
            SORT_PARAM,
            PAGE_PARAM
        )
    }

    @Test
    fun `getLocalGithubKotlinRepositories MUST return list of GithubKotlinReposModel when has a success`() {
        stubGetLocalGithubKotlinRepositoriesSuccess()
        TestContextProvider().io.run {
            runBlocking(this) {
                repository.getLocalGithubKotlinRepositories(
                    PAGE_PARAM
                ).run {
                    assertEquals(DUMMY_GITHUB_KOTLIN_REPOS_LIST, this)
                }
            }
        }
    }

    @Test(expected = Exception::class)
    fun `getLocalGithubKotlinRepositories MUST throw an Exception when has a error`() {
        stubGetLocalGithubKotlinRepositoriesError()
        TestContextProvider().io.run {
            runBlocking(this) {
                repository.getLocalGithubKotlinRepositories(
                    PAGE_PARAM
                )
            }
        }
    }

    @Test
    fun `saveGithubKotlinRepositories MUST return list of GithubKotlinReposModel when has a success`() {
        stubSaveGithubKotlinRepositoriesSuccess()
        TestContextProvider().io.run {
            runBlocking(this) {
                repository.saveGithubKotlinRepositories(
                    DUMMY_GITHUB_KOTLIN_REPOS_LIST
                ).run {
                    assertEquals(true, this)
                }
            }
        }
    }

    @Test(expected = Exception::class)
    fun `saveGithubKotlinRepositories MUST throw an Exception when has a error`() {
        stubSaveGithubKotlinRepositoriesError()
        TestContextProvider().io.run {
            runBlocking(this) {
                repository.saveGithubKotlinRepositories(
                    DUMMY_GITHUB_KOTLIN_REPOS_LIST
                )
            }
        }
    }

    private fun stubGetGithubKotlinRepositoriesSuccess() {
        whenever(
            dataSource.getGithubKotlinRepositories(
                LANGUAGE_PARAM,
                SORT_PARAM,
                PAGE_PARAM
            )
        ).thenReturn(
            flowOf(DUMMY_GITHUB_KOTLIN_REPOS_LIST)
        )
    }

    private fun stubGetGithubKotlinRepositoriesError() {
        whenever(
            dataSource.getGithubKotlinRepositories(
                LANGUAGE_PARAM,
                SORT_PARAM,
                PAGE_PARAM
            )
        ).thenThrow(Exception())
    }

    private fun stubGetLocalGithubKotlinRepositoriesSuccess() {
        TestContextProvider().io.run {
            runBlocking(this) {
                whenever(
                    localDataSource.getGithubKotlinRepositories(
                        PAGE_PARAM
                    )
                ).thenReturn(
                    DUMMY_GITHUB_KOTLIN_REPOS_LIST
                )
            }
        }
    }

    private fun stubGetLocalGithubKotlinRepositoriesError() {
        TestContextProvider().io.run {
            runBlocking(this) {
                whenever(
                    localDataSource.getGithubKotlinRepositories(
                        PAGE_PARAM
                    )
                ).thenThrow(Exception())
            }
        }
    }

    private fun stubSaveGithubKotlinRepositoriesSuccess() {
        TestContextProvider().io.run {
            runBlocking(this) {
                whenever(
                    localDataSource.saveGithubKotlinRepositories(
                        DUMMY_GITHUB_KOTLIN_REPOS_LIST
                    )
                ).thenReturn(
                    true
                )
            }
        }
    }

    private fun stubSaveGithubKotlinRepositoriesError() {
        TestContextProvider().io.run {
            runBlocking(this) {
                whenever(
                    localDataSource.saveGithubKotlinRepositories(
                        DUMMY_GITHUB_KOTLIN_REPOS_LIST
                    )
                ).thenThrow(Exception())
            }
        }
    }
}