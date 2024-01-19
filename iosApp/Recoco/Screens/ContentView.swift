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
    @State private var text = "Loading..."

    let appVersion = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "Unknown Version"

    var body: some View {
        Text(text)
            .task {
                text = (try? await Greeting().greet()) ?? "Error"
            }

        Button {
            Task.detached {
                do {
                    text = try await Greeting().greet()
                } catch {
                    text = String(localized: "Error: \(error.localizedDescription)")
                }
            }

            AlertKitAPI.present(
                title: String(localized: "Recoco (\(appVersion))"),
                icon: .heart,
                style: .iOS17AppleMusic,
                haptic: .success
            )
        } label: {
            Text("Welcome", comment: "Demo button")
        }
        .buttonStyle(.borderedProminent)
    }
}

// MARK: - Previews

#Preview {
    ContentView()
}
