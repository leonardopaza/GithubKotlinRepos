package com.lp.data.repository

import com.lp.data.datasource.remote.KotlinReposRemoteDataSource
import com.lp.data.mocks.KotlinReposRepositoryFactory.DUMMY_GITHUB_KOTLIN_REPOS_LIST
import com.lp.data.mocks.KotlinReposRepositoryFactory.LANGUAGE_PARAM
import com.lp.data.mocks.KotlinReposRepositoryFactory.PAGE_PARAM
import com.lp.data.mocks.KotlinReposRepositoryFactory.SORT_PARAM
import com.lp.data.mocks.testFlow
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class KotlinReposRepositoryImplTest {
    @Mock
    private lateinit var dataSource: KotlinReposRemoteDataSource
    private lateinit var repository: KotlinReposRepositoryImpl

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        repository = KotlinReposRepositoryImpl(dataSource)
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
}