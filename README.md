
**Database related**

1- install mongodb: brew install mongodb/brew/mongodb-community@5.0
(Note: if there is a current version you may use that, and in this case 'brew' its being used. One can also run a docker container)

2-configure application.properties to connect Spring Boot app to mongodb

**Database related**

Start db: brew services start mongodb-community@5.0
Stop db: brew services stop mongodb-community@5.0
Check service status: brew services list

More info: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/