# PFJ Android Coding Challenge

As a mobile developer for the PFJ Android team, highly performant and idiomatic Kotlin code is expected from all team members. All code is expected to function without any resource leaks, crashes, or battery drain. Unit tests are an expected result of the process.

Attached with this challenge you will find the template to get started. Please import this template to Android Studio and begin your work. The purpose of this application is to load a set of locations from a JSON file and display the locations as a pin on the map.

The developer must use the following libraries:

- -  KotlinX Serialization
- -  Retrofit &amp; OKHttp
- -  Kotlin Coroutines
- -  Live Data (AndroidX)
- -  ViewModel (AndroidX)
- -  GoogleMaps
- -  Dagger Hilt

The architecture should be MVVM.
 The acceptance criteria for this program is as follows:

- -  Application shows a map on launch and requests locations
- -  Locations loaded are sent to the map as markers
- -  On rotation, the API is not called again

 The developer must not override the system&#39;s default behavior for configuration change

- -  The app is written entirely in Kotlin
- -  All libraries required above are used
- -  Dependency injection is used, where appropriate
- -  Unit testing is performed on the API layer
- -  Pin markers show up for every entry the API provides
- -  Coroutines are used, no RxJava or other async methods should be used

GET endpoint: https://drive.google.com/file/d/14IG3sYCHRAU5WyKcfgTUw9LS1Oo0DBF4/view?usp=sharing
