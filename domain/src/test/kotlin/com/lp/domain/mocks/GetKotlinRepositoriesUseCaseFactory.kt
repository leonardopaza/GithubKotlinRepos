package com.lp.domain.mocks

import com.lp.domain.usecase.GetKotlinRepositoriesUseCase
import com.lp.domain.usecase.SaveKotlinRepositoriesUseCase

object GetKotlinRepositoriesUseCaseFactory {
    private const val LANGUAGE_PARAM = "language:kotlin"
    private const val SORT_PARAM = "stars"
    private const val PAGE_PARAM = 1

    val PARAMS = GetKotlinRepositoriesUseCase.Params(
        language = LANGUAGE_PARAM,
        sort = SORT_PARAM,
        page = PAGE_PARAM
    )

    val SAVE_PARAMS = SaveKotlinRepositoriesUseCase.Params(
        githubKotlinReposModelList = listOf()
    )
}