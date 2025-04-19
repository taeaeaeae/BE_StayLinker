@echo off
cd /d %~dp0
docker-compose down
docker-compose up -d --build
start http://localhost:8080/swagger-ui/index.html
pause