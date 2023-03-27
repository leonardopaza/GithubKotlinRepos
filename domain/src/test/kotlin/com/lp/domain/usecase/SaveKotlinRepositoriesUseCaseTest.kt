package com.lp.domain.usecase

import com.lp.domain.mocks.GetKotlinRepositoriesUseCaseFactory.SAVE_PARAMS
import com.lp.domain.mocks.TestContextProvider
import com.lp.domain.mocks.testModule
import com.lp.domain.repository.KotlinReposRepository
import junit.framework.TestCase.assertEquals
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

class SaveKotlinRepositoriesUseCaseTest {
    @Mock
    private lateinit var repository: KotlinReposRepository
    private lateinit var subject: SaveKotlinRepositoriesUseCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = SaveKotlinRepositoriesUseCase(
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
    fun `WHEN succeed MUST return Boolean value`() {
        stubOnSuccess()
        TestContextProvider().io.run {
            runBlocking(this) {
                subject.run(
                    SAVE_PARAMS
                ).run {
                    assertEquals(true, this)
                }
            }
        }
    }

    private fun stubOnSuccess() {
        TestContextProvider().io.run {
            runBlocking(this) {
                whenever(
                    repository.saveGithubKotlinRepositories(any())
                ).thenReturn(true)
            }
        }
    }
}