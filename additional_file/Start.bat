MKDIR c:\temp\
cd /D C:\temp\

git clone -b developer https://github.com/Bronic86/PVTRepository.git

cd /D C:\temp\PVTRepository\dry_cleaner
call mvn clean install

move C:\temp\PVTRepository\dry_cleaner\webapp\target\dry_cleaner.war   %CATALINA_HOME%\webapps

cd /D %CATALINA_HOME%\bin
call tomcat7