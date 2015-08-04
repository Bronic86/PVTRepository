MKDIR c:\temp\
cd /D C:\temp\

git clone -b developer https://github.com/Bronic86/PVTRepository.git

cd /D C:\temp\PVTRepository\dao
call mvn clean install

cd /D C:\temp\PVTRepository\dao\target\
java -jar dao-1.0.jar