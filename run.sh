#/bin/env bash

declare -a projects=("scala-finch-circe" "python-flask-gunicorn" "python-flask-uwsgi" "fastapi-uvicorn")
# declare -a projects=("fastapi-uvicorn")

RUN_TIME=60s
CONNECTIONS=100

for project in "${projects[@]}"
do
    echo "Building docker image for project $project!"
    echo "Running: <docker build -t $project $project>"
    
    docker build -t $project $project
    
    sleep 1;
    
    echo "Starting up docker container for $project!"
    
    docker run --name $project -d -p 8080:8080 --memory=1g --cpus=1 $project:latest

    sleep 5;
    
    echo "Running actual benchmark for ${RUN_TIME}s!"
    
    ./bombardier-linux-amd64 -c ${CONNECTIONS} -d ${RUN_TIME} -l http://localhost:8080/benchmark
    
    echo "Cleaning up!"
    docker rm $(docker stop -t 1 ${project})
done
