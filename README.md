Job-Search-Engine
-
***
### About
Main purpose of this app is to optimize job search with NLP. The elements of the project are:
- Java and Spring Boot
- Apache Kafka for messaging queue
- Open NLP for Business Layer as in language processing
- JSoup and Selenium for HTML/Web Scraping
- MongoDB for Data Base
- Spring Web for minimal frontend


LinkedIn experience filter is unreliable. It can be seen in the picture below:

<img alt="img.png" height="300" src="src/main/resources/img.png" width="350"/>


It is intended to develop the project with an N-tier architecture.

***
### Architecture

N-Tier layer. Distributed according to:
- Entity/Model/POJO layers
- Controller Layers
- Service Layers
- Database

Overview can be seen below:
![](src/main/resources/architeture.png)

***
### Web Scrapper

Web Scrapping is done under two main dependencies:
- Selenium: For automating web browsing.
- JSoup: For HTML parsing into DOM.

After the DOM has been obtained, it is selected the Job Description, and NLPService it is called to verify.
Probably it will be more like: -> 
1. API call to show jobs to JobController
2. Job Controller calls WebScrappingController
3. WebScrappingController calls NLPService over JobDescriptions
4. NLPService calls JobController to save those with boolean value True, to be saved.
5. JobController calls JobService to show jobs (previously saved).

It can also be an automatized service that enters to DB those jobs who comply with conditions

It can also be created a DB with a document != from 'job'.

***
### Database
**Database Installation and config**

- **install mongodb:** You may want to refer to this website: https://github.com/mongodb/homebrew-brew. I used, 'brew install mongodb-community' since it includes the mongo shell, which
makes interaction easier.
- **Configure application.properties** to connect Spring Boot app to mongodb
- **Configure mongod.conf**. In Mac: go to finder, Comand + Shift +G and paste "/opt/homebrew/etc/mongod.conf". By default, mongodb has no auth enabled, and if you specify it in the application.properties, you may get an error.

**Database commands**
- **_Start db:_** brew services start mongodb-community
- **_Stop db:_** brew services stop mongodb-community
- **_Restart db:_** brew services stop mongodb-community
- **_Check service status:_** brew services list
- **Check if mongodb is listening** on default port 27017: sudo lsof -i :27017
- **_To uninstall:_** (in case you mess it up): brew services uninstall mongodb-community

To check if there is any dbs created: 
- Go to terminal and enter "mongosh" if you have used step 1
- Enter 'show dbs'
  - If you have started application before creating manually the db, you will see that mongodb has created a db in the moment of startup.

More info: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/

****
**Future Enhancements**
- Enhance business logic for NlpService, scoreWords - \n replacements. (count, hasNext, then replace).
- Include other search engines (Firefox, Safari, etc.) to Driver class in WebScrapper Package.
- Implement Lombok library for POJO.


