plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Mvvm"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.compose.bom)
                implementation(libs.androidx.compose.runtime)
                implementation(libs.androidx.lifecycle.viewmodelktx)
                implementation(libs.androidx.lifecycle.savedstate)
                implementation(libs.androidx.lifecycle.compose)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    val minSdkVersion: String by properties
    val targetSdkVersion: String by properties
    val compileSdkVersion: String by properties

    namespace = "com.shmz.mvvm"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
