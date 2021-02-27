# Spring Redis

## Table of Contents
- [What is Redis.](#what-is-redis)
- [Use Case:](#use-case-)
- [Installation:](#installation-)
    + [Two methods:](#two-methods-)
    + [Installed and configure on MAC:](#installed-and-configure-on-mac-)
- [Backup](#backup)
- [Redis Sentinel](#redis-sentinel)
- [Redis Auth](#redis-auth)
- [Steps to run the application:](#steps-to-run-the-application-)
    + [Run both app and redis locally:](#run-both-app-and-redis-locally-)
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


## Use Case:
 - User Sessin Management
 - Caching.
 - Pub/Sub (Queues & Notification)
 - LeaderbOards for gaming apps
 - Geospatial
 
 
## Installation:
#### Two methods:
 - Download and installed.
 - Using package manager like brew (mac).
 

#### Installed and configure on MAC:
 - Open the terminal and type
   ```
   $ brew install redis
   ```
 - Once it done, type **redis-server** to start the redis server with default configuration provided by redis.
   ```
   $ redis-server
   ```   
 - Open another terminal and type **redis-cli**, to open the redis cli.
   ```
   $ redis-cli
   ```  
   You will see the output something like below:
   ```
   $ redis-cli -p 6379
   127.0.0.1:6379> 
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
> **_NOTE:_**  For other options and for other operating systems please use [Redis Download](https://redis.io/download).



### Backup
   Below command will create the dump/backup as per the path specified in redis.conf file. 
   ```
   $ save
   ```
   
### Redis Sentinel
  - It is a system, designed to help managing Redis instances.
  - It is there to provide HA by monitoring, notifying, and providing instances failover. 
  - It check whether master and slave are working properly or not.
  - If Master goes down, it's the sentinel responsibility to make one of the slave to master.  
  
  
### Redis Auth
  - Redis auth help to secure database. We can do that either via redis.conf or via cli.
  - Cli
    ```
    $ config set requirepass ${auth-key}
    $ auth ${auth-key}

    ``` 


Extra links:
 - Use [RedisLabs](https://redislabs.com/) to setup redis on cloud free.
 - Redis GUI - [Redis Desktop](https://rdm.dev/).
 
 
## Steps to run the application:
#### Run both app and redis locally:
 * Follow above steps to run Redis locally.
 * Execute below command to run the boot app
 ```
  $./gradlew bootRun
 ``` 
 
 ## TO DO
 * Add steps to run the app locally
 * Add Docker file to run app and redis both on docker.
 * Add steps to access the app via docker.
 * Add steps to run both container in K8s.