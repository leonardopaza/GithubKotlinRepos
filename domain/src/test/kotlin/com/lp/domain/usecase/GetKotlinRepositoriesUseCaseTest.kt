package com.lp.domain.usecase

import com.lp.domain.mocks.GetKotlinRepositoriesUseCaseFactory.PARAMS
import com.lp.domain.mocks.testFlow
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.repository.KotlinReposRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
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
        subject = GetKotlinRepositoriesUseCase(
            repository
        )
    }

    @Test(expected = RuntimeException::class)
    fun `WHEN don't receive params MUST throw RuntimeException`() {
        subject.run()
    }

    @Test
    fun `WHEN succeed MUST return list of GithubKotlinReposModel values`() {
        stubOnSuccess()
        subject.run(
            PARAMS
        ).testFlow {
            assertEquals(response, this)
        }
    }

    private fun stubOnSuccess() {
        whenever(
            repository.getGithubKotlinRepositories(any(), any(), any())
        ).thenReturn(flowOf(response))
    }
}