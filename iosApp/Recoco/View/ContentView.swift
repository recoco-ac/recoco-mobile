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

import AlertKit
import SwiftUI

import RecocoCore

struct ContentView: View {
    let greet = Greeting().greet()
    let appVersion = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "(Unknown Version)"

    var body: some View {
        Text("Recoco \(appVersion)")

        Button(action: {
            AlertKitAPI.present(
                title: greet,
                icon: .heart,
                style: .iOS17AppleMusic,
                haptic: .success
            )
        }) {
            Text("Welcome")
        }
        .buttonStyle(.borderedProminent)
    }
}

#Preview {
    ContentView()
}
