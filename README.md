# Password Manager

![img.png](readme_images/img.png)

## Description
Password Manager - password storage REST service

## Features
- all requests are secured except auth-controller requests. That's why, firstly, you need authorize and enter a jwt token received from one of the auth-controller requests

## What you can do in it
+ Create a new account
  ![img_1.png](readme_images/img_1.png)
+ Sign in
  ![img_2.png](readme_images/img_2.png)
+ Create a new password record
  ![img_3.png](readme_images/img_3.png)
+ Get your own list of records
![img_4.png](readme_images/img_4.png)
+ Get a specific record by its id
![img_5.png](readme_images/img_5.png)
+ Get info about password record directory
![img_10.png](readme_images/img_10.png)
+ Update a record by id - **переделать входные параметры (должен принимать не entity, а dto)**
+ Create a directory for records and other directories
![img_6.png](readme_images/img_6.png)

you can skip parametr 'parentDirectoryId' or set null if parent directory is root
+ Get short info about all records (id and name of record) in directory
![img_11.png](readme_images/img_11.png)
+ Get all nested directories in directory
![img_12.png](readme_images/img_12.png)
+ Get all attachments (nested records and directories) in directory
![img_13.png](readme_images/img_13.png)
+ Delete a directory
![img_7.png](readme_images/img_7.png)
+ Make sharing on record
![img_8.png](readme_images/img_8.png)
+ Get a sharing record from a person
![img_9.png](readme_images/img_9.png)