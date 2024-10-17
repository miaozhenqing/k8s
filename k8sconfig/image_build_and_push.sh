#!/bin/bash
# 添加 set -e 使得任何命令失败则停止执行
set -e
echo "=========================Building image starting"
docker build -t myrepository:latest .
echo "=========================Building image completed"
echo "=========================Tagging image"
docker tag myrepository:latest registry.cn-hangzhou.aliyuncs.com/mzqsingle/myrepository:latest
echo "=========================Tagging image completed"
echo "=========================Pushing image"
docker push registry.cn-hangzhou.aliyuncs.com/mzqsingle/myrepository:latest
echo "=========================Pushing image completed"