/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.matrix.android.auth

import androidx.test.annotation.UiThreadTest
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.zhuinden.monarchy.Monarchy
import im.vector.matrix.android.InstrumentedTest
import im.vector.matrix.android.OkReplayRuleChainNoActivity
import im.vector.matrix.android.api.auth.Authenticator
import im.vector.matrix.android.internal.auth.AuthModule
import im.vector.matrix.android.internal.di.MatrixModule
import im.vector.matrix.android.internal.di.NetworkModule
import okreplay.*
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest


@RunWith(AndroidJUnit4::class)
internal class AuthenticatorTest : InstrumentedTest, KoinTest {

    init {
        Monarchy.init(context())
        val matrixModule = MatrixModule(context()).definition
        val networkModule = NetworkModule().definition
        val authModule = AuthModule().definition
        loadKoinModules(listOf(matrixModule, networkModule, authModule))
    }

    private val authenticator: Authenticator by inject()
    private val okReplayInterceptor: OkReplayInterceptor by inject()

    private val okReplayConfig = OkReplayConfig.Builder()
            .tapeRoot(AndroidTapeRoot(
                    context(), javaClass))
            .defaultMode(TapeMode.READ_WRITE) // or TapeMode.READ_ONLY
            .sslEnabled(true)
            .interceptor(okReplayInterceptor)
            .build()

    @get:Rule
    val testRule = OkReplayRuleChainNoActivity(okReplayConfig).get()

    @Test
    @UiThreadTest
    @OkReplay(tape = "auth", mode = TapeMode.READ_WRITE)
    fun auth() {

    }

    companion object {
        @ClassRule
        @JvmField
        val grantExternalStoragePermissionRule: GrantPermissionRule =
                GrantPermissionRule.grant(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }


}