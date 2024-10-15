https://kubernetes.io/zh-cn/docs/home/


获取集群信息
kubectl cluster-info: 显示集群的信息，如 API server 地址和可用服务。
管理命名空间
kubectl get namespaces: 列出所有命名空间。
kubectl create namespace <namespace-name>: 创建一个新的命名空间。
kubectl delete namespace <namespace-name>: 删除一个命名空间。
资源操作
kubectl apply -f <file.yaml>: 应用配置文件中的定义来创建或更新资源。
kubectl delete -f <file.yaml>: 根据配置文件删除资源。
kubectl get <resource-type> [-o wide]: 获取指定类型的资源列表。添加 -o wide 可以显示更多列信息。
kubectl describe <resource-type> <resource-name>: 查看指定资源的详细信息。
kubectl edit <resource-type> <resource-name>: 编辑指定资源的配置。
Pods相关操作
kubectl logs <pod-name> [--container <container-name>]: 获取 Pod 中容器的日志输出。
kubectl exec <pod-name> -- <command>: 在 Pod 内执行命令。
kubectl port-forward <pod-name> <local-port>:<remote-port>: 端口转发，将本地端口映射到 Pod 的远程端口。
kubectl top pod <pod-name>: 查看 Pod 的 CPU 和内存使用情况。
服务与部署
kubectl expose deployment <deployment-name> --type=NodePort --port=<port>: 将 Deployment 暴露为 NodePort 类型的服务。
kubectl scale deployment <deployment-name> --replicas=<number>: 设置 Deployment 的副本数。
kubectl rollout status deployment <deployment-name>: 查看 Deployment 的滚动更新状态。
故障排查
kubectl get events: 查看集群中的事件，帮助诊断问题。
kubectl describe nodes: 查看节点详细信息，包括条件、地址、配置源、磁盘压力等。
kubectl top node: 查看节点的资源使用情况。




