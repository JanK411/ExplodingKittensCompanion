rootProject.name = "ExplodingKittensCompanion"

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":shared")
include(":webApp")

// The Docker image (see Dockerfile) hosts the web app and nothing else, so it builds
// with -PwebOnlyBuild: :androidApp never configures and its sources stay out of the
// build context. Normal builds are unaffected.
if (!providers.gradleProperty("webOnlyBuild").isPresent) {
    include(":androidApp")
}