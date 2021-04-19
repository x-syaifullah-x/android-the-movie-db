# android-the-movie-db

[![x-syaifullah-x](https://circleci.com/gh/x-syaifullah-x/android-the-movie-db.svg?style=shield)](https://circleci.com/gh/x-syaifullah-x/android-the-movie-db)

### Api

* ###### [TheMovieDB](https://developers.themoviedb.org/3/getting-started/introduction)

### System Design
* ###### Use Design Pettern
* ###### Architecture Pattern
    *   ###### Use Clean Architecture
        * ###### Ui -> Framework - [:app](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/app)
        * ###### Presenter -> Interface Adapter - [:the_movie_db_domain](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_domain)
        * ###### UseCase -> Application Business Rule - [:the_movie_db_domain](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_domain)
        * ###### Entities -> Enterprise Business Rule - [:the_movie_db_domain](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_domain)
        * ###### Repository -> Interface Adapter - [:the_movie_db_domain](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_domain)
        * ###### DataSource -> Framework - [:the_movie_db_data](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_data)

### System Layer
* ###### Presentation ( UI ) [- :app](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/app)

* ###### Domain ( USE CASE & Entities ) [- :the_movie_db_domain](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_domain)

* ###### Data ( DATA SOURCE ) [- :the_movie_db_data](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_data)

* ###### DI ( Dependencies Injection ) [- :the_movie_db_di](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_di)

### Modularization
* ###### [:the_movie_db_di - android-library](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_di)
* ###### [:the_movie_db_domain - android-library](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_domain)
* ###### [:the_movie_db_data - android-library](https://github.com/x-syaifullah-x/android-the-movie-db/tree/master/the_movie_db_data)

### Reactive Programming
* ###### With Flow

### Dependency Injection
* ###### With Dagger

### Code Coverage
* ###### With jacoco

### Security
* ###### Obfuscation With ProGuard
* ###### Encryption Database
* ###### Use Certificate Pinning And Dns Resolver Connection To Server.