/*
 * Copyright 2024 Recoco
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

package ac.recoco.mobile

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class Greeting {

    private val platform: Platform = getPlatform()

    private val client = HttpClient()

    suspend fun greet(): String = withContext(Dispatchers.IO) {
        Logger.i { "fetching api data" }
        val resp = client.get("https://api.open-meteo.com/v1/forecast?latitude=1.29&longitude=103.77&hourly=temperature_2m&forecast_days=1")

        "Hello, ${platform.name}!\n${resp.bodyAsText()}!"
    }
}
