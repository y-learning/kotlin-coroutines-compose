plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.lifecycle)
    implementation(Libs.AndroidX.vmLifecycle)

    implementation(Libs.AndroidMaterial.material)

    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.uiTooling)
    implementation(Libs.Compose.foundation)
    implementation(Libs.Compose.iconsCore)
    implementation(Libs.Compose.iconsExt)
    implementation(Libs.Compose.material)

    implementation(Libs.Y.core)
    implementation(Libs.Y.collections)

    implementation(Libs.Ktor.core)
    implementation(Libs.Ktor.engine)
    implementation(Libs.Ktor.gson)

    implementation(Libs.WorkManager.work)

    testImplementation(Libs.Kotest.runner)
    testImplementation(Libs.Kotest.assertions)
    testImplementation(Libs.Kotest.property)

    testImplementation(Libs.Compose.uiTestJUnit)

    testImplementation(Libs.Kotlinx.coroutinesTest)

    androidTestImplementation(Libs.Kotest.runner)
    androidTestImplementation(Libs.Kotest.assertions)
    androidTestImplementation(Libs.Kotest.property)
    androidTestImplementation(Libs.WorkManager.workTest)
    androidTestImplementation(Libs.Kotlinx.coroutinesTest)
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.why.kotlin.coroutines"
        minSdkVersion(22)
        targetSdkVersion(30)
        versionCode = 1
        versionName = Ci.publishVersion
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = Libs.jvmTarget
        useIR = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.version
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
