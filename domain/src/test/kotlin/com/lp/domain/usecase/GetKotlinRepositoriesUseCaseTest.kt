package com.lp.domain.usecase

import com.lp.domain.mocks.GetKotlinRepositoriesUseCaseFactory.PARAMS
import com.lp.domain.mocks.TestContextProvider
import com.lp.domain.mocks.testFlow
import com.lp.domain.mocks.testModule
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.repository.KotlinReposRepository
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
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class GetKotlinRepositoriesUseCaseTest {
    @Mock
    private lateinit var response: List<GithubKotlinReposModel>

    @Mock
    private lateinit var repository: KotlinReposRepository
    private lateinit var subject: GetKotlinRepositoriesUseCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = GetKotlinRepositoriesUseCase(
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test(expected = RuntimeException::class)
    fun `WHEN don't receive params MUST throw RuntimeException`() {
        TestContextProvider().io.run {
            runBlocking(this) {
                subject.run()
            }
        }
    }

    @Test
    fun `WHEN succeed MUST return list of GithubKotlinReposModel values`() {
        stubLocalOnSuccess()
        stubOnSuccess()
        TestContextProvider().io.run {
            runBlocking(this) {
                subject.run(
                    PARAMS
                ).testFlow {
                    assertEquals(response, this)
                }
            }
        }
    }

    private fun stubLocalOnSuccess() {
        TestContextProvider().io.run {
            runBlocking(this) {
                whenever(
                    repository.getLocalGithubKotlinRepositories(any())
                ).thenReturn(response)
            }
        }
    }

    private fun stubOnSuccess() {
        whenever(
            repository.getGithubKotlinRepositories(any(), any(), any())
        ).thenReturn(flowOf(response))
    }
}