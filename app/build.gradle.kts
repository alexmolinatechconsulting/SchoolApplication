import java.util.Properties

var properties = Properties()
var baseUrl : String = ""
var endpointSchools : String = ""
var endpointSchoolSatScores : String = ""
var selectSchoolColumnQuery : String = ""
var selectSchoolSatScoresColumnQuery : String = ""
var appToken : String = ""

if (File("local.properties").exists()) {
    properties = Properties().apply { load(project.rootProject.file("local.properties").inputStream()) }
    baseUrl = properties.getProperty("BASE_URL")
    endpointSchools = properties.getProperty("ENDPOINT_SCHOOLS")
    endpointSchoolSatScores = properties.getProperty("ENDPOINT_SCHOOL_SAT_SCORES")
    selectSchoolColumnQuery = properties.getProperty("SELECT_SCHOOL_COLUMN_QUERY")
    selectSchoolSatScoresColumnQuery = properties.getProperty("SELECT_SCHOOL_SAT_SCORES_COLUMN_QUERY")
    appToken = properties.getProperty("APP_TOKEN")
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.schoolapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.schoolapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    buildTypes {
        debug{
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
            buildConfigField("String", "ENDPOINT_SCHOOLS", "\"$endpointSchools\"")
            buildConfigField("String", "ENDPOINT_SCHOOL_SAT_SCORES", "\"$endpointSchoolSatScores\"")
            buildConfigField("String", "SELECT_SCHOOL_COLUMN_QUERY", "\"$selectSchoolColumnQuery\"")
            buildConfigField("String", "SELECT_SCHOOL_SAT_SCORES_COLUMN_QUERY", "\"$selectSchoolSatScoresColumnQuery\"")
            buildConfigField("String", "APP_TOKEN", "\"$appToken\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
            buildConfigField("String", "ENDPOINT_SCHOOLS", "\"$endpointSchools\"")
            buildConfigField("String", "ENDPOINT_SCHOOL_SAT_SCORES", "\"$endpointSchoolSatScores\"")
            buildConfigField("String", "SELECT_SCHOOL_COLUMN_QUERY", "\"$selectSchoolColumnQuery\"")
            buildConfigField("String", "SELECT_SCHOOL_SAT_SCORES_COLUMN_QUERY", "\"$selectSchoolSatScoresColumnQuery\"")
            buildConfigField("String", "APP_TOKEN", "\"$appToken\"")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.material:material:1.6.1")

    val bomPlatform = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(bomPlatform)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
    implementation("androidx.navigation:navigation-compose:2.7.6")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.compose.runtime:runtime-livedata:1.4.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("io.insert-koin:koin-android:3.5.3")
}