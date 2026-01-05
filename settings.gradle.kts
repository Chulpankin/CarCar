pluginManagement {
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

rootProject.name = "CarCar"
include(":app")
include(":core")
include(":core:common")
include(":core:data")
include(":core:data:api")
include(":core:data:impl")
include(":core:data:impl:firebase")
include(":core:data:impl:network")
include(":core:ui")
include(":features")
include(":features:signin")
include(":features:signup")
include(":features:carlist")
include(":features:carlist:api")
include(":features:carlist:impl")
include(":features:signin:api")
include(":features:signin:impl")
include(":features:signup:api")
include(":features:signup:impl")
include(":features:carsearch:api")
include(":features:carsearch:impl")
include(":features:favorites:api")
include(":features:favorites:impl")
