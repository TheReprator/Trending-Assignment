object AndroidSdk {
    const val min = 14
    const val compile = 29
    const val target = compile

    val locales = listOf("en")
}

object AppConstant {
    const val applicationPackage = "com.eventersapp.gojek_trending"
    const val name = "Eventers"
    const val host = "https://github-trending-api.now.sh/"
    const val hostConstant = "HOST"
}

object AppVersion {
    const val versionCode = 1
    const val versionName = "1.0"
}

object AppModules {
    const val moduleApp = ":app"
}

object Libs {

    object Plugins {
        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val javaLibrary = "java-library"
        const val kotlinLibrary = "kotlin"
        const val kotlinAndroid = "android"
        const val kotlinAndroidExtensions = "android.extensions"
        const val kotlinKapt = "kapt"
        const val kotlinNavigation = "androidx.navigation.safeargs.kotlin"
        const val realm = "realm-android"
    }

    const val realm = "io.realm:realm-gradle-plugin:6.0.1"
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.0-alpha06"

    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val facebook = "com.facebook.android:facebook-login:[5,6)"
    const val countryPicker = "com.hbb20:ccp:2.3.2"
    const val mvRx = "com.airbnb.android:mvrx:1.3.0"
    const val inject = "javax.inject:javax.inject:1"

    object Google {
        const val materialWidget = "com.google.android.material:material:1.2.0-alpha01"
        const val firebaseCore = "com.google.firebase:firebase-core:17.2.1"
        const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
        const val gmsGoogleServices = "com.google.gms:google-services:4.3.3"
        const val fabricPlugin = "io.fabric.tools:gradle:1.31.2"

        const val openSourceLicensesPlugin = "com.google.android.gms:oss-licenses-plugin:0.10.0"
        const val openSourceLicensesLibrary = "com.google.android.gms:play-services-oss-licenses:17.0.0"
    }

    object Firebase {
        const val core = "com.google.firebase:firebase-core:17.2.1"
        const val iid = "com.google.firebase:firebase-iid:20.0.1"
        const val messaging = "com.google.firebase:firebase-messaging:20.0.1"
        const val authFirebase = "com.google.firebase:firebase-auth:19.2.0"
        const val authPlayServices = "com.google.android.gms:play-services-auth:17.0.0"
        const val authPlayPhoneServices =
            "com.google.android.gms:play-services-auth-api-phone:17.1.0"
        const val authUI = "com.firebaseui:firebase-ui-auth:4.3.1"
        const val remoteConfig = "com.google.firebase:firebase-config:19.0.3"
    }

    object Kotlin {
        private const val version = "1.3.61"
        const val kotlinStdLib = "stdlib-jdk8"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.3.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val rx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }
    /*object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0-beta04"
        const val cardView = "androidx.cardview:cardview:1.0.0"
        const val annotation = "androidx.annotation:annotation:1.1.0"
        const val multidex = "androidx.multidex:multidex:2.0.1"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta3"
        const val coreKtx = "androidx.core:core-ktx:1.2.0-alpha04"
        const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0-beta04"

        object Fragment {
            private const val version = "1.1.0-alpha03"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.2.0-rc02"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        }

        object SavedState {
            const val savedState = "androidx.savedstate:savedstate:1.0.0"
            const val savedState_viewModal =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:1.0.0-rc02"

            const val savedState_bundle = "androidx.savedstate:savedstate-bundle:1.0.0-alpha01"
        }

        object Navigation {
            private const val version = "2.2.0-rc02"
            const val runtime = "androidx.navigation:navigation-runtime:$version"
            const val runtimeKtx = "androidx.navigation:navigation-runtime-ktx:$version"
            const val fragment = "androidx.navigation:navigation-fragment:$version"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val navigationPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Room {
            private const val version = "2.1.0-beta01"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        object Work {
            private const val version = "1.0.1"
            const val runtimeKtx = "android.arch.work:work-runtime-ktx:$version"
            const val runtime = "android.arch.work:work-runtime:$version"
        }
    }*/


    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val browser = "androidx.browser:browser:1.0.0"
        const val collection = "androidx.collection:collection-ktx:1.1.0"
        const val palette = "androidx.palette:palette:1.0.0"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        const val emoji = "androidx.emoji:emoji:1.0.0"
        const val swiperefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-alpha03"

        object Navigation {
            private const val version = "2.2.0-rc02"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.2.0-rc01"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }

        const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"

        object Paging {
            private const val version = "2.1.0"
            const val common = "androidx.paging:paging-common-ktx:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
        }

        const val preference = "androidx.preference:preference:1.1.0"

        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta3"

        const val coreKtx = "androidx.core:core-ktx:1.2.0-rc01"

        object Lifecycle {
            private const val version = "2.2.0-rc02"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Room {
            private const val version = "2.2.2"
            const val common = "androidx.room:room-common:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val testing = "androidx.room:room-testing:$version"
        }

        object Work {
            private const val version = "2.2.0"
            const val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
        }
    }

    object Retrofit {
        private const val version = "2.6.2"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val jacksonConverter = "com.squareup.retrofit2:converter-jackson:$version"
        const val jacksonKotlinModule = "com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1"
    }

    object Dagger {
        private const val version = "2.25.2"
        const val runtime = "com.google.dagger:dagger:$version"
        const val android = "com.google.dagger:dagger-android:$version"
        const val android_support = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val android_support_compiler = "com.google.dagger:dagger-android-processor:$version"
    }


    object TestDependencies {
        object Mockk {
            private const val version = "1.9.3"
            const val unitTest = "io.mockk:mockk:$version"
            const val instrumentedTest = "io.mockk:mockk-android:$version"
        }

        const val assertj = "org.assertj:assertj-core:3.13.2"
        const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:5.5.2"
        const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:5.5.2"
        const val junit = "junit:junit:4.12"
        const val junitInstrumented = "androidx.test.ext:junit:1.1.1"
    }

    //https://github.com/satoshun-android-example/ViewModelSavedState/blob/master/app/build.gradle
    object AssistedInject {
        private const val version = "0.5.2"
        const val annotationDagger2 = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        const val processorDagger2 = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
    }

    object Coil {
        private const val version = "0.8.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object OkHttp {
        private const val version = "4.2.2"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Epoxy {
        private const val version = "3.8.0"
        const val epoxy = "com.airbnb.android:epoxy:$version"
        const val paging = "com.airbnb.android:epoxy-paging:$version"
        const val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
        const val processor = "com.airbnb.android:epoxy-processor:$version"
    }

}


/*// Instrumentation tests
androidTestImplementation "androidx.test.espresso:espresso-core:$rootProject.espressoVersion"
androidTestImplementation "androidx.test.espresso:espresso-contrib:$rootProject.espressoVersion"
androidTestImplementation "androidx.test.ext:junit:$rootProject.testExtVersion"
androidTestImplementation "androidx.test:runner:$rootProject.runnerVersion"
androidTestImplementation "androidx.test:rules:$rootProject.rulesVersion"

// Local unit tests
testImplementation "junit:junit:$rootProject.junitVersion"
testImplementation "org.mockito:mockito-core:$rootProject.mockitoVersion"
testImplementation "com.nhaarman:mockito-kotlin:$rootProject.mockitoKotlinVersion"
testImplementation "org.hamcrest:hamcrest-library:$rootProject.hamcrestVersion"*/