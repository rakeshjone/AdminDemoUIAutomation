# AdminDemoUIAutomation
Ui automation test project for admin demo website with java cucumber and junit
1. To be able to read files via Thread.currentThread().getContextClassLoader().getResources("filename") in configurations manager Configurations folder under resources 
also needs to be marked as resources in project structure as shown in below image.
![image](https://user-images.githubusercontent.com/130054374/232771905-afbefaee-ca9e-4c39-b4f5-3afe0e6a1a07.png)


2. Glue option is tricky to setup in runner file when you want to use hooks in a seperate file. When suppliying glue cucumber looks for hooks and glue in same location. 
Combination of glue and extraglue, with and without glue, impacts glue working properly. For this setup leave out glue settings.
3. To be able to run test via both Runner.java and cucumber file keep run config for both as below:
Run>Edit COnfigurations
![image](https://user-images.githubusercontent.com/130054374/232773173-2c7d9458-6e91-4db0-8c3f-957a63b8afdc.png)
![image](https://user-images.githubusercontent.com/130054374/232773282-b52e3692-3be6-455b-903a-43d8eaaa4528.png)

