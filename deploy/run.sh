#!/bin/bash
cd "$(dirname "$0")"
docker-compose down
docker-compose up -d --build
open http://localhost:8080/swagger-ui/index.html
echo "✅ Staylinker 서버가 실행되었습니다!"