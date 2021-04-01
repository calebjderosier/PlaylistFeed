PlaylistFeed
===========================================================

This is an app that gets a playlist (presently, “Chill Hits”) from the Deezer API and displays it on the main screen, with a drill-down available to view further details about a given song. 


Introduction
-------------

The app implements an MVVM architecture to display and pass the data pulled from the Deezer API, which does not require an API key. 

The Deezer API is called from [`PlaylistRetriever`](app/src/main/java/com/calebderosier/playlistfeed/data/api/PlaylistRetriever.kt) via the [`DeezerService`](app/src/main/java/com/calebderosier/playlistfeed/data/api/DeezerService.kt) interface, and the resulting data conforms to the class definitions within the Playlist class in [`Response`](app/src/main/java/com/calebderosier/playlistfeed/data/models/Response.kt). The [`MainActivity`](app/src/main/java/com/calebderosier/playlistfeed/ui/activities/main/MainActivity.kt) observes the change in the [`MainViewModel`](app/src/main/java/com/calebderosier/playlistfeed/ui/activities/main/MainViewModel.kt) and populates the song list using the [`SongListAdapter`](app/src/main/java/com/calebderosier/playlistfeed/ui/adapters/SongListAdapter.kt), which also enables each item to navigate to the [`DetailsActivity`](app/src/main/java/com/calebderosier/playlistfeed/ui/activities/details/DetailsActivity.kt). 

A superior implementation would’ve used Dagger 2 for dependency injection, but as it stands there was not time to do so. 


Testing 
-------------

The test suite uses a combination of JUnit, Espresso, and Mockito for testing. However, the lack of DI massively limited the thoroughness of the tests, so they are rather simplistic on the whole. 

The androidTest folder contains tests for MainActivity, DetailsActivity, and the API Service/Playlist Retriever, while the test folder contains the tests for MainViewModel. 


Libraries 
-------------

• [AndroidX](https://developer.android.com/jetpack/androidx)

• [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)

• Coroutines for coroutines with Retrofit

• [Espresso](https://developer.android.com/training/testing/espresso) for UI tests

• [JUnit 4](https://junit.org/junit4/)

• [Retrofit2](https://square.github.io/retrofit/) for networking

• [Mockito](https://site.mockito.org) for mocking

• [Picasso](https://square.github.io/picasso/) for image loading
