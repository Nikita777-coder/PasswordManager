# Password Manager

![img.png](readme_images/img.png)

## Description
Password Manager - password storage REST service

## Features
- all requests are secured except auth-controller requests. That's why, firstly, you need authorize and enter a jwt token received from one of the auth-controller requests

## What you can do in it
+ Create a new account
> <img src="readme_images/img_1.png" alt="drawing" width="700"/>
+ Sign in
> <img src="readme_images/img_2.png" alt="drawing" width="700"/>
+ Create a new password record
> <img src="readme_images/img_3.png" alt="drawing" width="700"/>
+ Get your own list of records
> <img src="readme_images/img_4.png" alt="drawing" width="700"/>
+ Get a specific record by its id
> <img src="readme_images/img_5.png" alt="drawing" width="700"/>
+ Get info about password record directory
> <img src="readme_images/img_10.png" alt="drawing" width="700"/>
+ Update a record by id - **переделать входные параметры (должен принимать не entity, а dto)**
> <img src="readme_images/img7.png" alt="drawing" width="700"/>
+ Create a directory for records and other directories
> <img src="readme_images/img_6.png" alt="drawing" width="700"/>

> You can skip parametr 'parentDirectoryId' or set null if parent directory is root
+ Get short info about all records (id and name of record) in directory
> <img src="readme_images/img_11.png" alt="drawing" width="700"/>
+ Get all nested directories in directory
> <img src="readme_images/img_12.png" alt="drawing" width="700"/>
+ Get all attachments (nested records and directories) in directory
> <img src="readme_images/img_13.png" alt="drawing" width="700"/>
+ Delete a directory
> <img src="readme_images/img_7.png" alt="drawing" width="700"/>
+ Make sharing on record
> <img src="readme_images/img_8.png" alt="drawing" width="700"/>
+ Get a sharing record from a person
> <img src="readme_images/img_9.png" alt="drawing" width="700"/>
