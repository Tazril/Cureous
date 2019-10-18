# Cureous
Medical App

## Table of contents

- [Quick start](#quick-start)
- [Description](#description)
- [Technologies Used](#technologies-used)
- [Bugs and feature requests](#bugs-and-feature-requests)
- [Created By](#created-by)
- [Thanks](#thanks)


## Quick start

To build:

```bash
$ git clone git@github.com:Tazril/Cureous.git
$ cd Cureous/
$ ./gradlew build
```

## Description
A Medical Android App which serves the purpose of showing possible diseases as per the symptoms entered.
Upon entering symptoms followed by age and gender takes user to result activity where the user is given a list of possible diseases 
with accuracy, and a list of possible specialisation under tab Layout. On selecting recycler view (list) item gives detail description 
of the issue.


## Technologies Used
- MVP Architecture for clean code
- Material Design for UI
- ApiMedic Api as server for data fetching
- Android Studio (Kotlin with Gradle build) as IDE
- RxJava for threading
- Retrofit for networking
- Latest Android Jetpack Components(Constraint Layout, Navigation Component )
- Room Persistence Library for local database communication



## Bugs and feature requests
For bugs, feature requests, and discussion please use [GitHub Issues][issues].


## Thanks

Thank You for reading this documentation.



 [issues]: https://github.com/Tazril/Cureous/issues

