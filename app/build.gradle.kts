plugins {
    id(Libs.Plugins.androidApplication)
    kotlin(Libs.Plugins.kotlinAndroid)
    kotlin(Libs.Plugins.kotlinAndroidExtensions)
    kotlin(Libs.Plugins.kotlinKapt)
}

android {

    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        applicationId = AppConstant.applicationPackage

        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        versionCode = AppVersion.versionCode
        versionName = AppVersion.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true

        resConfigs(AndroidSdk.locales)
        buildConfigField("String", AppConstant.hostConstant, "\"${AppConstant.host}\"")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    flavorDimensions("type")

    viewBinding.isEnabled = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin/")
        getByName("test").java.srcDirs("src/test/kotlin/")
        getByName("test").resources.srcDirs("src/test/resources/")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin/")
        getByName("androidTest").resources.srcDirs("src/androidTest/resources/")
    }

    testOptions {
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    packagingOptions.exclude("META-INF/main.kotlin_module")
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(Libs.timber)

    implementation(Libs.Google.materialWidget)
    implementation(Libs.AndroidX.recyclerview)
    implementation(Libs.AndroidX.swiperefresh)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.constraintlayout)

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.android)

    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.Navigation.ui)

    implementation(Libs.AndroidX.Lifecycle.extensions)
    implementation(Libs.AndroidX.Lifecycle.viewmodel)

    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.jacksonConverter)
    implementation(Libs.Retrofit.jacksonKotlinModule)
    debugImplementation(Libs.OkHttp.loggingInterceptor)

    implementation(Libs.Dagger.runtime)
    kapt(Libs.Dagger.compiler)

    implementation(Libs.Coil.coil)


    testImplementation(Libs.TestDependencies.junit)
    testImplementation(Libs.TestDependencies.Mockk.unitTest)
    debugImplementation(Libs.TestDependencies.archCoreTesting)
    debugImplementation(Libs.TestDependencies.coroutinesTesting)

    testImplementation(Libs.OkHttp.mockWebServer)

    androidTestImplementation(Libs.TestDependencies.instrumentedJunit)
    androidTestImplementation(Libs.TestDependencies.Mockk.instrumentedTest)
    androidTestImplementation(Libs.TestDependencies.instrumentedEspressoCore)
    androidTestImplementation(Libs.TestDependencies.instrumentedEspressoIntent)
    androidTestImplementation(Libs.TestDependencies.instrumentedEspressoContrib)
    debugImplementation(Libs.TestDependencies.instrumentedFragment)
    implementation(Libs.TestDependencies.idlingResource)
}