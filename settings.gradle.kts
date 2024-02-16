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
    versionCatalogs {
        create("ctlibs") {
            from(files("gradle/ctlibs.versions.toml"))
        }
    }
}
rootProject.name = "CT-AndroidModuler"
include(":Carwale")
include(":Cartrade")
include(":CTCamera")
include(":SharedUtils")
include(":Adroit")
