# Introduction
The Twitter CRUD App is an application that allows the user to post, read and delete Tweets on a Twitter Developer Account via the official Twitter REST APIs. This app is designed following the well-known MVC architecture, consisting of a model, controller layer, service layer and data access (DAO) layer. Then, it is tested using JUnit and Mockito tests. This app has been dockerized and is available to pull as an image on the DockerHub. The technologies used in this application are: Maven, Java, HTTP & JSON, JUnit & Mockito, Spring, Docker and Twitter REST APIs

# Quick Start
Environment variables are required in order to access from the Twitter Developer App: 
- consumerKey: API Key
- consumerSecret: API Secret
- accessToken: Access Token
- tokenSecret: Access Secret

# Usage

**POST**
- Allows the user to post a tweet with a location using geotag

```
- TwitterApp post tweet_text longitude:latitude
```

**SHOW**
- Allows the user to retrieve and read a tweet by using the tweet's id

```
- TwitterApp show tweet_id [field1, field2,...]
```

**DELETE**
- Allows the user to delete one or multiple tweets

```
- TwitterApp delete [id1, id2 ...]
```

# 1. Running using Maven

```
mvn clean package -Dmaven.test.skip=true
java -jar target/twitter-1.0-SNAPSHOT.jar post|show|delete [options]
```

# 2. Running using Docker

```
docker pull anthonypham017/twitter

docker run --rm \
-e consumerKey=YOUR_KEY \
-e consumerSecret=YOUR_KEY \
-e accessToken=YOUR_TOKEN \
-e tokenSecret=YOUR_TOKEN \
anthonypham017/twitter post|show|delete [options]
```

# Design
## UML Diagram

![twitteruml](assets/twitter_uml.png?raw=true)

# Application

**TwitterCLIApp**

This is the component where the user interacts with in order to use the Twitter CRUD Application. It takes user input from the Linux CLI and calls the post, show, or delete methods in the Controller layer with respects to the CLI arguments. The application also retrieves the keys and tokens set in the system environment to be used.

# Controller Layer

**TwitterController**

This The controller layer retrieves and verify the CLI arguments set by the user to ensure the there is the correct amount of arguments and that they are all formatted correctly. The layer then calls the service layer to post, show, or delete.

# Service Layer

**TwitterService**

The service layer performs the business logic of the application. It ensures that the tweet text is no greater than 280 characters. The layer verifies that the tweet id is in the correct numerical format. It ensures that the latitude is in between -90 and 90, and the longitude is between -180 and 80.

# Data Access Object (DAO) Layer

**TwitterDAO**

The DAO layer handles the HTTP requests and responses, and sends and retrieves information to and from the Twitter Website. This layer uses the TwitterHttpHelper and URI statements in order to execute POST and GET requests.

# Models

**Tweet**

The Twitter CRUD Application uses a simplified version of the complete Tweet Model. The simplified version excludes attributes that are not needed for the Application to run. The Tweet object consist of attributes, and other objects including Coordinates, Entities, Hashtag, and UserMention.

![twitterer](assets/twitter_er.png?raw=true)

# Spring

The Spring framework was used in order to handle all the dependencies in the Twitter CRUD Application. Beans were implemented in the TwitterCLISpringBoot class and were indicated throughout the application using ```@Component```, ```@Repository```, ```@Controller```, ```@Service```. The TwitterCLIComponentScan class, a replication of the ```@ComponentScan``` Spring function, was used to scan the beans.

# Test

The Twitter CRUD Application was tested using Integration and Unit testing from JUnit 4. The Integration tests require the dependencies to be correct and complete before testing, to confirm that the previous written programs are correct before testing the current program. Unit testing uses Mockito to create mock objects instead of using dependencies. This allows the testing of individual components during the development of that application, rather than relying on other previous written programs.

# Deployment

The Twitter CRUD Application was deployed using Docker to the DockerHub. An image of the application was built using the docker command ```docker build -t anthonypham017/twitter``` and pushed by using ```docker push anthonypham017/twitter```. The Docker image can be viewed using the command ```docker pull anthonypham017/twitter``` or on https://hub.docker.com/repository/docker/anthonypham017/twitter.

# Improvements

- Allow the user to schedule an automated tweet with a given time and date
- Allow replies for tweets or like/retweet information
- Allow user to preview the tweet before posting


