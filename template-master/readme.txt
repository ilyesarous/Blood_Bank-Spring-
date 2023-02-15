Follow this steps to get your project started ASAP:
 I) 	Create an empty directory with the name of your project and copy/past the content of this directory into it( without the readme.txt file and the cached svn folder).
 II)    Click on the newly crated directory with right button of the mouse and go TortoisesSVN > Import. Specify The URL of your repo and click Ok.
 III)   ClicK on your directory with right button of the mouse and click SVN Checkout.
 IV)    Change your pom.xml:
    		1- Change groupId. 
    		2- Change artifactId.
    		3- Change name.
    		4- Change description.
    		5- Save changes
    		
 V)    	Rename the folder "template" in your newely created directory wihch you will find it under src\main\java\com\csys and under test\main\java\com\csys and under. Go to your project in your IDE and replace com.csys.template with com.csys.yourprojectname under the source and test packages directory then clean and build your project.
 VI)    Change application-dev/application-prod:
            1-liquibase.default-schema.	
            2-spring.datasource (change the 3 properties).
            3-spring.jpa.properties.hibernate.default_schema.
            4-server.contextPath.
 VII)   Change server.application.name in bootstrap.yml.
 VIII)  Change main class's name.
 IX)    Change logger names in both logback.xml files ( under other sources and other test sources)
  X)    Run your project with the spring-boot maven plugin. If it runs clicK on your directory with right button of the mouse and click SVN Checkout.