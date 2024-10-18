#!/bin/bash
# 添加 set -e 使得任何命令失败则停止执行
set -e

# 定义版本号
VERSION="latest"

echo "=========================Building image starting"
docker build -t k8stest:$VERSION .
echo "=========================Building image completed"
echo "=========================Tagging image starting"
docker tag k8stest:$VERSION registry.cn-hangzhou.aliyuncs.com/mzqsingle/k8stest:$VERSION
echo "=========================Tagging image completed"
echo "=========================Pushing image starting"
docker push registry.cn-hangzhou.aliyuncs.com/mzqsingle/k8stest:$VERSION
echo "=========================Pushing image completed"
