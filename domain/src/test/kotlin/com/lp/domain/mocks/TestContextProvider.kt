package com.lp.domain.mocks

import com.lp.domain.utils.ThreadContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : ThreadContextProvider() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}