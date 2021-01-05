# softwareEngineering - Session Management 
- Final project Software Engineering COMP-4960-02 ( Group of 9 )
- Can create - edit - remove Session from homepage
- A session may contain speaker name, room information, and time
- A speaker contain name, day of contact, phone number, and email

- Set Up
  -	[Download Apache NetBeans 12 LTS:](https://netbeans.apache.org/download/nb120/nb120.html) 
    +	[Mac version](https://www.apache.org/dyn/closer.cgi/netbeans/netbeans/12.0/Apache-NetBeans-12.0-bin-macosx.dmg)  or
    + [Window version (64bit)](https://www.apache.org/dyn/closer.cgi/netbeans/netbeans/12.0/Apache-NetBeans-12.0-bin-windows-x64.exe)
  -	[Download Tomcat server](https://tomcat.apache.org/download-90.cgi)
    +	[Mac version](https://mirrors.sonic.net/apache/tomcat/tomcat-9/v9.0.40/bin/apache-tomcat-9.0.40.zip)
    +	[Window version (64bit)](https://mirrors.sonic.net/apache/tomcat/tomcat-9/v9.0.40/bin/apache-tomcat-9.0.40-windows-x64.zip)
  -	[MySQL Workbench](https://dev.mysql.com/downloads/mysql/) (select operating system) if mac choose the one show dmg

- Install
  -	Install NetBeans
  -	Unzip tomcat sever, put it somewhere
  -	Go to NetBeans, click tool then add server, 
    - Click add server
    - Choose apache tomcat
    - At (Server location), browse to folder tomcat that you just put somewhere
    - Then set username password anything you want (remember these username and password)
    - Then hit finish, then hit close 
    - If you run tomcat on mac, and there is a problem can’t execute *sh, open terminal, directory of Catalina.sh chmod 755 *.sh, example                                     /Users/Downloads/apache-tomcat-9.0.40/bin/catalina.sh chmod 755 *.sh

  - Install mysql, remember username and password that you set while installing


- Configure:
  - Locate DBContext.java in project and edit serverName, dbName, portNumber, userId, password to the same as your database that you just set up.
