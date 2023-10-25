plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.8.20-1.0.10"
    //Kotlinx Serialization
    kotlin("plugin.serialization") version "1.8.20"
}

android {
    namespace = "com.joseph.kmmsocialapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.joseph.kmmsocialapp.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(buildDir, "generated/ksp/$name/kotlin")
        )
    }
}

dependencies {
    val compose_version = "1.4.3"
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.foundation:foundation:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.animation:animation-graphics:$compose_version")
    implementation("androidx.appcompat:appcompat-resources:1.3.0")

    implementation("io.github.raamcosta.compose-destinations:core:1.8.38-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.8.38-beta")
    implementation("io.insert-koin:koin-androidx-compose:3.4.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    implementation("com.google.accompanist:accompanist-pager:0.13.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.13.0")

    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    implementation("io.coil-kt:coil-compose:2.4.0")

}