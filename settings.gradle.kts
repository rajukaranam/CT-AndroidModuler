pluginManagement {
    repositories {
        google()
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

rootProject.name = "HactivateDemo"
include(":Carwale")
include(":Cartrade")
include(":CTCamera")
include(":SharedUtils")
include(":Adroit")
