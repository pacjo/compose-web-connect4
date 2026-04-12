plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose.compiler)
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
                // TODO: export to libs.versions.toml
                implementation("org.jetbrains.compose.html:html-core:1.10.3")
                implementation("org.jetbrains.compose.runtime:runtime:1.10.3")
            }
        }
    }
}