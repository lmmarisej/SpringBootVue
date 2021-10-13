echo "==============================当前工作目录==============================="
workdir=$(cd $(dirname $0); pwd)
echo "$workdir"
echo "=============================创建节点配置文件=============================="
for i in {1..9}; do
  conf_file="$workdir/redis-cluster/700${i}.conf"
  conf_content="port 700${i}\r\ndaemonize no\r\ncluster-enabled yes\r\ncluster-config-file cluster-nodes-700${i}.conf\r\ncluster-node-timeout 1000\r\ncluster-replica-validity-factor 5\r\ncluster-migration-barrier 1\r\ncluster-require-full-coverage yes\r\ncluster-replica-no-failover no\r\nappendonly yes"
  echo "创建文件并写入配置数据：$conf_file"
  # 层级文件创建
  mkdir -p "$workdir/redis-cluster"
  # 创建配置文件
  touch "${conf_file}"
  # 避免因为权限而找不到文件
  chmod u+x "${conf_file}"
  # 向配置文件写入数据
  echo "${conf_content}" > "${conf_file}"
done
echo "==============================节点启动信息==============================="
conf_list=""
for i in {1..9}; do
  redis-server "$workdir"/redis-cluster/700"${i}".conf &
  conf_list="$conf_list""[redis-cluster/700${i}.conf]"
done
echo "redis-server run with cluster node:{$conf_list}"
sleep 3s
echo "==============================集群节点信息==============================="
redis-cli -c -h 127.0.0.1 -p 7001 cluster nodes
echo "===============================集群信息=================================="
redis-cli -c -h 127.0.0.1 -p 7001 cluster info
echo "==============================集群槽位信息================================="
redis-cli -c -h 127.0.0.1 -p 7001 cluster slots
echo "==============================集群进程信息================================="
ps aux|grep redis
echo "===============================集群启动完成==============================="
echo "have fun!!!"