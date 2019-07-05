# /bin/bash

dir='node_modules'
if [[ ! -e $dir ]]; then
    npm install
fi
docker-compose down
docker-compose up --build