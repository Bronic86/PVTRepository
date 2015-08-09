MKDIR c:\temp\
cd /D C:\temp\

git clone -b developer https://github.com/Bronic86/PVTRepository.git

cd /D C:\temp\PVTRepository\dry_cleaner
call mvn clean install
