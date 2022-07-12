#!/bin/bash

gradle assemble
docker-compose build --no-cache 
docker-compose up
