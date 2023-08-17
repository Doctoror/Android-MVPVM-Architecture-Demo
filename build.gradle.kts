// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.dagger.hilt.android") version libs.versions.hilt apply false
    id("com.google.devtools.ksp") version libs.versions.ksp apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {

        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.navigation.safe.args.gradle.plugin)
        classpath(libs.tools.build.gradle)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
