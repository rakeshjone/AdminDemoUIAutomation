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
 
