# AdminDemoUIAutomation
Ui automation test project for admin demo website with java cucumber and junit
Run tests via terminal: 
mvn test -D"cucumber.filter.tags=@login"
mvn test
mvn clean test
1. To be able to read files via Thread.currentThread().getContextClassLoader().getResources("filename") in configurations manager Configurations folder under resources 
also needs to be marked as resources in project structure as shown in below image.
![image](https://user-images.githubusercontent.com/130054374/232771905-afbefaee-ca9e-4c39-b4f5-3afe0e6a1a07.png)


2. Glue option is tricky to setup in runner file when you want to use hooks in a seperate file. When suppliying glue cucumber looks for hooks and glue in same location. 
Combination of glue and extraglue, with and without glue, impacts glue working properly. For this setup leave out glue settings as it is.
3. To be able to run test via both Runner.java and cucumber file keep run config for both as below:
Run>Edit COnfigurations
![image](https://user-images.githubusercontent.com/130054374/232773173-2c7d9458-6e91-4db0-8c3f-957a63b8afdc.png)
![image](https://user-images.githubusercontent.com/130054374/232773282-b52e3692-3be6-455b-903a-43d8eaaa4528.png)

https://github.com/SeleniumHQ/docker-selenium
docker ls:
docker network ls




---------create node------------------
$ docker network create grid
$ docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:4.16.1-20231208

Check hub and nodes locally: http://localhost:4444/ui

-----------------------------------------
remove already used containers:
docker container prune

----create hubs-----------------------------

$ docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub `
    --shm-size="2g" `
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 `
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 `
    selenium/node-chrome:4.16.1-20231208
	
$ docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub `
    --shm-size="2g" `
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 `
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 `
    selenium/node-edge:4.16.1-20231208
	
$ docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub `
    --shm-size="2g" `
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 `
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 `
    selenium/node-firefox:4.16.1-20231208

-----------------run tests----------------------------------------------------
mvn tests
----------------------------------------------------
	docker network inspect --format '{{range $cid,$v := .Containers}}{{printf "%s: %s\n" $cid $v.Name}}{{end}}' grid
	https://github.com/SeleniumHQ/docker-selenium

 latest:-------------------------------------------
 ## Pre Requisites:
1. Install Java and maven:
Download and install java in C:\Dev\Apps\Java folder on your machine from: https://www.oracle.com/in/java/technologies/downloads/#jdk21-windows
Download and export maven in C:\Dev\Apps\Maven from: https://maven.apache.org/download.cgi

2. Set environment variables:
Add Java home and maven home in environment variables:
JAVA_HOME: C:\Dev\Apps\Java
M2_HOME: C:\Dev\Apps\Maven\apache-maven-3.9.6

Add below in path variable:
%M2_HOME%\bin
%JAVA_HOME%\bin

3. Install intelliJ idea community addition.
4. Install git (Follow below links to use chocolatey for installing git)
https://chocolatey.org/install
https://community.chocolatey.org/packages/git.install#individual
5. Install docker desktop
https://docs.docker.com/get-docker/

## Setup local work repo
1. Create folder structure below and clone this project:
C:\Dev\Projects
2. Install cucumber plugin for intelliJ idea from marketplace.

Use these steps to clone from SourceTree, our client for using the repository command-line free. Cloning allows you to work on your files locally. If you don't yet have SourceTree, [download and install first](https://www.sourcetreeapp.com/). If you prefer to clone from the command line, see [Clone a repository](https://confluence.atlassian.com/x/4whODQ).

1. You’ll see the clone button under the **Source** heading. Click that button.
2. Now click **Check out in SourceTree**. You may need to create a SourceTree account or log in.
3. When you see the **Clone New** dialog in SourceTree, update the destination path and name if you’d like to and then click **Clone**.

## Running tests locally on Docker
1. Make sure Docker variable is set to True in Default.properties.
2. Run docker-compose -f docker-compose.yml up from project root folder.
3. Navigate to http://localhost:4444/ui/ in your browser and check multiple browser nodes are running.
4. Rum mvn clean test command in another powershell.
5. Once tests are finished running go back to previous powershell and press ctrl+C and run docker-compose -f docker-compose.yml down to release resources.
6. Verify test results report in target folder.
4. Open the directory you just created to see your repository’s files.

Now that you're more familiar with your Bitbucket repository, go ahead and add a new file locally. You can [push your change back to Bitbucket with SourceTree](https://confluence.atlassian.com/x/iqyBMg), or you can [add, commit,](https://confluence.atlassian.com/x/8QhODQ) and [push from the command line](https://confluence.atlassian.com/x/NQ0zDQ).
 
