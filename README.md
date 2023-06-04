
**Database Installation and config**

1- install mongodb: You may want to refer to this website: https://github.com/mongodb/homebrew-brew

2- I personally used, 'brew install mongodb-community' since it includes the mongo shell, which
makes interaction easier.
2-configure application.properties to connect Spring Boot app to mongodb

3- configure mongod.conf. In Mac: go to finder, Comand + Shift +G and paste "/opt/homebrew/etc/mongod.conf"

4- By default, mongodb has no auth enabled, and if you specify it in the application.properties, you may get an error.

**Database commands**
- Start db: brew services start mongodb-community
- Stop db: brew services stop mongodb-community
- Restart db: brew services stop mongodb-community
- Check service status: brew services list
- Check if mongodb is listening on default port 27017: sudo lsof -i :27017
- To uninstall (in case you mess it up): brew services uninstall mongodb-community

To check if there is any dbs created: 
- Go to terminal and enter "mongosh" if you have used step 1
- Enter 'show dbs'
  - If you have started application before creating manually the db, you will see that mongodb has created a db in the moment of startup.

More info: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/