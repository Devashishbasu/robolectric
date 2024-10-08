plugins { alias(libs.plugins.robolectric.java.module) }

dependencies {
  api(project(":robolectric"))
  api(libs.junit4)
  compileOnly(AndroidSdk.MAX_SDK.coordinates)

  testRuntimeOnly(AndroidSdk.MAX_SDK.coordinates)
  testImplementation(libs.truth)
  testImplementation(libs.libphonenumber)
}
