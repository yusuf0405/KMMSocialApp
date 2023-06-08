plugins {
    kotlin("multiplatform")
    id("com.android.library")
    //Kotlinx Serialization
    kotlin("plugin.serialization") version "1.8.20"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    //dependencies versions
    val coroutinesVersion = "1.6.4"
    val koinVersion = "3.3.2"
    val ktorVersion = "2.2.1"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                api("io.insert-koin:koin-core:$koinVersion")
            }
        }


        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }


        val androidMain by getting {
            dependencies {
                api("io.insert-koin:koin-android:$koinVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }


        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting



        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
      
    }
}

android {
    namespace = "com.joseph.kmmsocialapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}