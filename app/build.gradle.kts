import java.io.FileInputStream
import java.io.InputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)

}

// Load from local.properties
val localProps = Properties()
val localPropsFile = File(rootProject.projectDir, "local.properties")
if (localPropsFile.exists() && localPropsFile.isFile) {
    localPropsFile.inputStream().use { input: InputStream ->
        localProps.load(input)
    }
}
val openAPIKey = (localProps.getProperty("OPENAI_API_KEY")
    ?: project.findProperty("OPENAI_API_KEY") as String?)
    .orEmpty()
    .trim()
    .removeSurrounding("\"")
val supabaseUrl = (localProps.getProperty("SUPABASE_URL")
    ?: project.findProperty("SUPABASE_URL") as String?)
    .orEmpty()
    .trim()
    .removeSurrounding("\"")
val supabaseKey = (localProps.getProperty("SUPABASE_KEY")
    ?: project.findProperty("SUPABASE_KEY") as String?)
    .orEmpty()
    .trim()
    .removeSurrounding("\"")


android {
    namespace = "com.indianathe3rd.identity"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.indianathe3rd.identity"
        minSdk = 29
        targetSdk = 37
        versionCode = 2
        versionName = "1.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "OPENAI_API_KEY", "\"$openAPIKey\"")
        buildConfigField("String", "SUPABASE_URL", "\"$supabaseUrl\"")
        buildConfigField("String", "SUPABASE_KEY", "\"$supabaseKey\"")

    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.clerk.android.ui)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.voyager.navigator)

    implementation(platform(libs.supabase.bom))
    implementation(libs.supabase.postgrest)
    implementation(libs.supabase.auth)
    implementation(libs.supabase.storage)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.material.icons.extended)

    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    implementation(libs.coil.compose)

    implementation(libs.openai)


}


