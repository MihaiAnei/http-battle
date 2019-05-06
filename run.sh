#/bin/env bash

declare -a projects=("scala-finch-circe" "python-flask-gunicorn")

for project in "${projects[@]}"
do
    echo "Building docker image for project $project!"
    echo "Running: <docker build -t $project $project>"
    
    docker build -t $project $project
    
    sleep 1;
    
    echo "Starting up docker container for $project!"
    
    docker run --name $project -d -p 8080:8080 --memory=1g --cpus=2 $project:latest
    
    sleep 1;
    
    echo "Running warmup for 30s!"
    
    wrk -t4 -c24 -d30s -s post.lua --latency http://localhost:8080/benchmark
    
    echo "Running actual benchmark for 10s!"
    
    WRK_REPORT=${project}-$(date +"%m-%d-%Y-%T").csv wrk -t4 -c24 -d10s --latency -s benchmark.lua http://localhost:8080/benchmark
    
    echo "Cleaning up!"
    docker rm $(docker stop -t 1 ${project})
done
