Job-Search-Engine
-
***
### About
Main purpose of this app is to optimize job search with NLP. The elements of the project are:
- Java and Spring Boot
- Apache Kafka for messaging queue
- Open NLP for Business Layer as in language processing
- MongoDB for Data Base
- Spring Web for minimal frontend

It is intended to develop the project with an N-tier architecture.
***
### Database
**Database Installation and config**

- install mongodb: You may want to refer to this website: https://github.com/mongodb/homebrew-brew
- I used, 'brew install mongodb-community' since it includes the mongo shell, which
makes interaction easier.
- Configure application.properties to connect Spring Boot app to mongodb
- Configure mongod.conf. In Mac: go to finder, Comand + Shift +G and paste "/opt/homebrew/etc/mongod.conf"
- By default, mongodb has no auth enabled, and if you specify it in the application.properties, you may get an error.

**Database commands**
- _Start db:_ brew services start mongodb-community
- _Stop db:_ brew services stop mongodb-community
- _Restart db:_ brew services stop mongodb-community
- _Check service status:_ brew services list
- Check if mongodb is listening on default port 27017: sudo lsof -i :27017
- _To uninstall:_ (in case you mess it up): brew services uninstall mongodb-community

To check if there is any dbs created: 
- Go to terminal and enter "mongosh" if you have used step 1
- Enter 'show dbs'
  - If you have started application before creating manually the db, you will see that mongodb has created a db in the moment of startup.

More info: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/


**Future Enhancements**
- Implement Lombok library for POJO
