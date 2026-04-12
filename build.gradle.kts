plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")

            dependencies {
                implementation(libs.compose.runtime)
                implementation(libs.compose.html.core)
                implementation(libs.kotlin.serialization.json)
            }
        }

        val jsTest by getting {
            kotlin.srcDir("src/test/kotlin")

            dependencies {
                implementation(libs.kotlin.test)
            }

        }
    }
}