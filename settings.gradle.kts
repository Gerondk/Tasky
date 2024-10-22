pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Tasky"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:presentation:designsystem")
include(":auth:presentation")
include(":core:network")
include(":core:data")
include(":core:domain")
include(":auth:domain")
include(":auth:data")
include(":core:presentation:ui")
include(":agenda:presentation")
include(":agenda:data")
include(":agenda:domain")
include(":core:database")
