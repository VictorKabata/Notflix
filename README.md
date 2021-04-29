<p align="center"><img src="assets/logo_n.png" alt="NotFlix" height="200px"></p>
<p align="center"><img src="assets/logo_notflix.png" alt="NotFlix" height="31px"></p>

<p align="center">
<img  src="https://img.shields.io/badge/-KOTLIN-E50914?logo=kotlin&logoColor=white&style=for-the-badge">&nbsp;
<img  src="https://img.shields.io/badge/-ANDROID-E50914?logo=android&logoColor=white&style=for-the-badge">&nbsp;
<img  src="https://img.shields.io/badge/-LICENSE:%20MIT-E50914?logo=licenselogoColor=white&style=for-the-badge">&nbsp;
</p>


# Notflix  🛠️Work In Progress🛠

 An android app built using Kotlin that consumes [TMDB API]("https://developers.themoviedb.org/3") to display current trending, upcoming and popular movies🍿 and tvshows🎬. It has been built following Clean Architecture Principle, Repository Pattern, MVVM Architecture in the presentation layer as well as jetpack components.

 I created this repository for a few reasons:
 1. To learn the approch of implementing clean architecture and [SOLID principles](https://en.wikipedia.org/wiki/SOLID#:~:text=The%20SOLID%20concepts%20are%3A,%2C%20but%20closed%20for%20modification.%22) in an android app.
 2. To learn libraries/tools supported by Google and most of the android development communities.
 3. To experiment with modularization and dynamic feature modules.
 4. To learn implementation of Picture-in-Picture.
 5. Demonstrate best developement practices by utilizing up to date tech-stack .

 ## Table Of Content.

- [Prerequisite](#prerequisite)
    - [Disclaimer](##disclaimer)
- [Architecture](#architecture)
    - [What is Clean Architecture](##why-clean-architecture)
    - [Why Clean Architecture](##why-clean-architecture)
    - [S.O.L.I.D Principles](##s.o.l.i.d-principles)
    - [Layers](##layers)
        - [Domain](###domain)
        - [Data](###data)
        - [Presentation](###presentation)
- [Tech Stack](#techstack)
    - [Patterns](##patterns)
    - [Libraries](##libraries)
    - [Plugins](##plugins)
- [Related Resources](#related-resources)
- [Other Helpful Resources](#other_helpful_posts)
- [Demo](#demo)
- [Work In Progress](#work-in-progress)

## Prerequisite.

In order to be able to build the application you'll need to change the api key in [`gradle.properties`](link_to_gradle.properties_file). First and formost you need to generate your own api key by [creating](https://www.themoviedb.org/signup) an IMDB account and [generating](https://www.themoviedb.org/settings/api) an api key.

## Disclaimer.

Complex architectures like the pure clean architecture can also increase code complexity since decoupling your code also means creating lots of data transformations(mappers) and models,that may end up increasing the learning curve of your code to a point where it would be better to use a simpler architecture like MVVM.

So let's get started ...

## Architecture.

### What is Clean Architecture?

A well planned architecture is extremely important for an app to scale and all architectures have one common goal- to manage complexity of your app. This isn't something to be worried about in smaller apps however it may prove very useful when working on apps with longer development lifecycle and a bigger team.

Clean architecture was proposed by [Robert C. Martin](https://en.wikipedia.org/wiki/Robert_C._Martin) in 2012 in the [Clean Code Blog](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and it follow the SOLID principle.

<p align="center"><img src="assets/clean_arch.png" alt="Clean Architecture Diagram"></p>

The circles represent different layers of your app. Note that:

- The center circle is the most abstract, and the outer circle is the most concrete. This is called the [Abstraction Principle](https://en.wikipedia.org/wiki/Abstraction_principle_(computer_programming)). The Abstraction Principle specifies that inner circles should contain business logic, and outer circles should contain implementation details.

- Another principle of Clean Architecture is the [Dependency Inversion](https://en.wikipedia.org/wiki/Dependency_inversion_principle). This rule specifies that each circle can depend only on the nearest inward circle ie. low-level modules do not depend on high-level modules but the other way around.

### Why Clean Architecture?
- Loose coupling between the code - The code can easily be modified without affecting any or a large part of the app's codebase.
- Easier to test code.
- Separation of Concern - Different modules have specific responsibilities making it easier for modification and maintenance.

### S.O.L.I.D Principles.

- [__Single Responsibility__](https://en.wikipedia.org/wiki/Single-responsibility_principle): Each software component should have only one reason to change – one responsibility.

- [__Open-Closed__](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle#:~:text=In%20object%2Doriented%20programming%2C%20the,without%20modifying%20its%20source%20code.): You should be able to extend the behavior of a component, without breaking its usage, or modifying its extensions.

- [__Liskov Substitution__](https://en.wikipedia.org/wiki/Liskov_substitution_principle): If you have a class of one type, and any subclasses of that class, you should be able to represent the base class usage with the subclass, without breaking the app.

- [__Interface Segregation__](https://en.wikipedia.org/wiki/Interface_segregation_principle): It’s better to have many smaller interfaces than a large one, to prevent the class from implementing the methods that it doesn’t need.

- [__Dependency Inversion__](https://en.wikipedia.org/wiki/Dependency_inversion_principle): Components should depend on abstractions rather than concrete implementations. Also higher level modules shouldn’t depend on lower level modules.

## Layers.

### 1. Domain.
This is the core layer of the application. The ```domain``` layer is independent of any other layers thus ] domain models and business logic can be independent from other layers.This means that changes in other layers will have no effect on domain layer eg.  screen UI (presentation layer) or changing database (data layer) will not result in any code change withing domain layer.

Components of domain layer include:
- __Models__: Defines the core structure of the data that will be used within the application.

- __Repositories__: Interfaces used by the use cases. Implemented in the data layer.

- __Use cases/Interactors__: They enclose a single action, like getting data from a database or posting to a service. They use the repositories to resolve the action they are supposed to do. They usually override the operator “invoke”, so they can be called as a function.

### 2. Data.
The ```data``` layer is responsibile for selecting the proper data source for the domain layer. It contains the implementations of the repositories declared in the domain layer. 

Components of data layer include:
- __Models__

    -__Dto Models__: Defines POJO of network responses.

    -__Entity Models__: Defines the schema of SQLite database.

- __Repositories__: Responsible for exposing data to the domain layer.

- __Mappers__: They perform data transformation between ```domain```, ```dto``` and ```entity``` models.

- __Network__: This is responsible for performing network operations eg. defining API endpoints using [Retrofit](https://square.github.io/retrofit/).

- __Cache__: This is responsible for performing caching operations using [Room](https://developer.android.com/training/data-storage/room).

- __Data Source__:  Responsible for deciding which data source (network or cache) will be used when fetching data.



### 3. Presentation.
The ```presentation``` layer contains components involved in showing information to the user. The main part of this layer are the views and viewModels.

## Tech Stack.
This project uses many of the popular libraries, plugins and tools of the android ecosystem.

### Patterns.
- [Observer Pattern](https://medium.com/android-news/observer-pattern-how-does-it-work-82dfd81305a4) - The observer pattern is a software design pattern that defines a one to many dependencies between objects so that one object changes state, all of its dependents are notified and updated automatically.

- [Repository Pattern](https://medium.com/swlh/repository-pattern-in-android-c31d0268118c): The repository pattern is a software design pattern that restricts us from work directly with the data in the application and creates new layers for database operations, business logic, and the application's UI.

- [UseCase Pattern](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576) - UseCase pattern is a software design pattern that is used to bridge the gap between business and system models without altering requirements contents nor preempting architectural options.

### Libraries.

- [Hilt](https://github.com/google/hilt) - Dependency Injection library.
- [Jetpack](https://developer.android.com/jetpack)
  -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
     - [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - Allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
    - [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite used for offline data caching.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)-Component that allows easier implementation of navigation from simple button clicks to more complex patterns.

- [Retrofit](https://square.github.io/retrofit/) - Type-safe http client 
and supports coroutines out of the box.  
- [GSON](https://github.com/square/gson) - JSON Parser,used to parse 
requests on the data layer for Entities and understands Kotlin non-nullable 
and default parameters.
- [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
- [Timber](https://github.com/JakeWharton/timber)-Library for easier logging.
- [Material Design](https://material.io/develop/android/docs/getting-started/) - Build awesome beautiful UIs.
- [Glide](https://github.com/bumptech/glide)- Image Library from loading images from the database and cacheing in memory.


## Related Resources.
In this section i've included some resources ie. articles and GitHub reposirtories that i used to learn about Clean Architecture:

1. [The clean code blog](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) by Robert C. Martin.
2. [A detailed guide on developing android apps using clean architecture pattern](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029) Medium article.
3. [Clean Architecture Component Boilerplater](https://github.com/bufferapp/clean-architecture-components-boilerplate) GitHub repo .
4. [The Force](https://github.com/odaridavid/Clean-MVVM-ArchComponents) GitHub repo by [David Odari](https://twitter.com/_davidodari) demonstrating using clean architecture pattern  
5. [Clean architecture tutorial for android](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started) article by Raywenderlich which is really beginner friendly.
6. [Clean architecture in android](https://medium.com/swlh/clean-architecture-in-android-a-beginner-approach-be0ce00d806b) Medium article.
7. [Intro to app architecture](https://proandroiddev.com/intro-to-app-architecture-922b392b21b2) and [Intro to app modularization](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e) articles by ProAndroidDev.

## Other Helpful Resources.
In this section i've included resources that are not related to clean architecture but were really helpful in learning other android components and tools:

1. [Pokedex](https://github.com/ronnieotieno/PokeApi-Pokedex) GitHub repo by [Ronnie Otieno](https://twitter.com/ronnieonly) demonstrating how to use various jetpack components.
2. [Fundamentals of testing](https://developer.android.com/training/testing/fundamentals) from the official [android developers](https://developer.android.com/) site.


