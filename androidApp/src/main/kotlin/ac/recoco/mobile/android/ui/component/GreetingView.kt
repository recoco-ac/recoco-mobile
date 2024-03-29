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

package ac.recoco.mobile.android.ui.component

import ac.recoco.mobile.android.R
import ac.recoco.mobile.android.ui.theme.RecocoTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GreetingView(text: String) {
    Column {
        Text(text = stringResource(R.string.greeting))
        Text(text = text)
    }
}

@Preview
@Composable
private fun GreetingViewPreview() = RecocoTheme {
    GreetingView("Hello, Android!")
}
