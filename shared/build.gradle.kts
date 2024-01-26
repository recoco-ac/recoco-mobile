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

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.android.library)

    alias(libs.plugins.kotest)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "RecocoCore"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }

        commonTest.dependencies {
            implementation(libs.bundles.kotest)
            runtimeOnly(libs.kotest.junit5)
        }
    }
}

android {
    namespace = "ac.recoco.mobile"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    compileSdk = (property("android.compileSdk") as String).toInt()
    defaultConfig {
        minSdk = (property("android.minSdk") as String).toInt()
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    dependencies {
        coreLibraryDesugaring(libs.desugarJdk)
    }
}

detekt {
    buildUponDefaultConfig = true
    config.from += "detekt.yml"
}

spotless {
    kotlin {
        ktlint()
    }
}

tasks.getByName("detekt") {
    dependsOn(
        "detektMetadataCommonMain",
        "detektAndroidRelease",
        "detektMetadataIosMain",
    )
}
