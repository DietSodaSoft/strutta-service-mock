#!/bin/bash

result=$(curl -s http://localhost:8080/health)

if [[ "$result" =~ "UP" ]]; then
    exit 0
else
    exit 1
fi