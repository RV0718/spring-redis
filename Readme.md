# Spring Redis

## Table of Contents
- [What is Redis.](#what-is-redis)
- [Use Case](#use-case)
- [Installation](#installation)
    + [Two methods](#two-methods)
    + [Installed and configure on MAC](#installed-and-configure-on-mac)
- [Backup](#backup)
- [Monitor](#monitor)
- [Redis Sentinel](#redis-sentinel)
- [Redis Auth](#redis-auth)
- [Steps to run the application](#steps-to-run-the-application)
    + [Run both app and redis locally](#run-both-app-and-redis-locally)
    + [Run both app and redis inside a container](#run-both-app-and-redis-inside-a-container)
    + [Run both app and redis inside a container using docker-compose](#run-both-app-and-redis-inside-a-container-using-docker-compose)
- [OpenAPI](#OpenAPI)    
- [API Endpoint to access the app](#api-endpoint-to-access-the-app)
- [TO DO](#to-do)

## What is Redis.

 - Redis is a powerful and extremely fast in memory db.   
 - Stores data in key-value.   
 - Persistence.  
 - Optionally you can save the data to disk.   
 - Master slave replication.  
 - Highly Available.   
 - Open source.  
 - Indexing (Still can create own indexing).  
 - No query language.  


## Use Case
 - User Session Management
 - Caching.
 - Pub/Sub (Queues & Notification)
 - Leaderboards for gaming apps
 - Geospatial
 
 
## Installation
#### Two methods
 - Download and installed. (Please use [Redis Download](https://redis.io/download) to see the steps to configure the redis in other OS)
 - Using package manager like brew (mac). (I will be using this to configure redis)
 

#### Installed and configure on MAC
 - Open the terminal and type
   ```
   $ brew install redis
   ```
 - Once it done, type **redis-server** to start the redis server with default configuration provided by redis.
   ```
   $ redis-server {path to the redis.conf}
   ```   
 - Open another terminal and type **redis-cli**, to open the redis cli.
   ```
   $ redis-cli -p 6379
   ```  
   You will see the output something like below:
   ```
   $ redis-cli -p 6379
   127.0.0.1:6379> 
   ```
   Checking redis status execute below
   ```
   $ redis-cli ping
   PONG
   ```
   killing redis server execute below
   ```
   127.0.0.1:6379> shutdown
   ```
   
 - Some sample commands on redis-cli
   ```
    127.0.0.1:6379> set color red   #setting a value in color (as key) and red (as value)
    OK
    127.0.0.1:6379> keys *   # verifying total number of keys
    1) "\xac\xed\x00\x05t\x00\x04Book"
    2) "color"
    127.0.0.1:6379> 
    127.0.0.1:6379> 
    127.0.0.1:6379> get color  # getting the value assigned to the key - color
    "red"
    127.0.0.1:6379> 
   ```  

## Backup
   Below command will create the dump/backup as per the path specified in redis.conf file. 
   ```
   $ save
   ```
   
## Monitor
   Run the below command from terminal and you'll be abe to see everything happening on redis
   ```
    $ redis-cli -p 6379
    127.0.0.1:6379> Monitor
    OK
   ```
    
## Redis Sentinel
  - It is a system, designed to help managing Redis instances.
  - It is there to provide HA by monitoring, notifying, and providing instances failover. 
  - It check whether master and slave are working properly or not.
  - If Master goes down, it's the sentinel responsibility to make one of the slave to master.  
  
  
## Redis Auth
  - Redis auth help to secure database. We can do that either via redis.conf or via cli.
  - Cli
    ```
    $ config set requirepass ${auth-key}
    $ auth ${auth-key}

    ``` 


Extra links:
 - Use [RedisLabs](https://redislabs.com/) to setup redis on cloud free.
 - Redis GUI - [Redis Desktop](https://rdm.dev/).
 
 
## Steps to run the application
#### Run both app and redis locally
 * Follow [these steps](#installed-and-configure-on-mac) to configure and run Redis locally.
 * Execute below command to run the boot app from apps directory
 ```
  $./gradlew bootRun
 ``` 
 * Alternatively, first build the jar and then run **java -jar** command as below
 ```
  $./gradlew build
 ```
 ```
  $ java -jar {name of the generated jar}
 ```
#### Run both app and redis inside a container
  * Execute the below command from redis directory
  ```
  $ docker network create rmoff_services
  $ docker build -t redis .
  $ docker run -d -p 6379:6379 --network=rmoff_services --name redis -h redis redis
  ```
  * Build an image of the app
  ```
  $ docker build -t app .
  ```
  * Verify the image
  ```
  $ docker image ls -a
  ```
  * Run the app
  ```
  $ docker run -d --name app -e "REDIS_HOST=redis" -p 8080:8080 --network=rmoff_services spring-redis
  ```
#### Run both app and redis inside a container using docker-compose
  ```
  $ docker-compose -f docker-compose-stack.yaml up -d
  ```
#### Run both in K8s
  * Execute below command to deploy the application in k8s and expose the same via service using nodeport.
  ```
  $ kubectl apply -f k8s-deployment.yaml
  ```  
  
## OpenAPI
  * http://localhost:8080/swagger-ui.html
  * http://localhost:8080/api-docs  

## API Endpoint to access the app
  - http://localhost:8080/api/health
  - http://localhost:8080/api/book/save
  - http://localhost:8080/api/book/all
  - http://localhost:8080/api/book/delete/{id}     
 
 ## TO DO

 * Add steps to run both container in K8s.
  
  