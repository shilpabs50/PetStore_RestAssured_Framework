@echo off

set GROUP=%1
set ENV=%2

for /f %%i in ('powershell -NoProfile -Command "Get-Date -Format yyyy-MM-dd-HH-mm"') do set TIMESTAMP=%%i

set REPORT_NAME=%GROUP%-%ENV%-%TIMESTAMP%

docker run --rm ^
-e ENV=%ENV% ^
-e GROUP="-Dgroups=%GROUP%" ^
-v %cd%/docker-allure-report/%REPORT_NAME%:/app/target/site/allure-maven-plugin ^
petstore-framework