import buildlogic.Utils

plugins {
    id("build.fabric")
    id("build.publish")
}

Utils.setupResources(project, rootProject, "fabric.mod.json")

dependencies {

    minecraft("com.mojang:minecraft:${project.findProperty("minecraft-version")}")
    implementation("net.fabricmc:fabric-loader:${project.findProperty("fabric-loader-version")}")

    compileOnly(libs.jetbrains.annotations)

    listOf(
        "fabric-api-base"
    ).forEach { mod ->
        api(include(fabricApi.module(mod, "${project.properties["fabric-api-version"]}"))!!)
    }

    gametestRuntimeOnly("dev.jwall64:databridge:0.12.1")

    // Gametest API modules
    val testApiModules = listOf(
        "fabric-gametest-api-v1",
        "fabric-registry-sync-v0"
    )
    for(mod in testApiModules) {
        gametestImplementation(fabricApi.module(mod, "${project.properties["fabric-api-version"]}"))
    }
}
