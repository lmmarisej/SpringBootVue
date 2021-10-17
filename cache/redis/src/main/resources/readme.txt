1. 推荐切换到脚本所在目录，再执行。
2. 推荐open finder执行脚本。在idea执行，idea会有bug (工作目录问题)。

./redis-cli --cluster create 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006 127.0.0.1:7007 127.0.0.1:7008 127.0.0.1:7009 --cluster-replicas 1


bind 192.168.120.4
port 7000
daemonize yes
appendonly yes
protected-mode no
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 15000
pidfile "/opt/redis-cluter/7000/pidfile/redis.pid"
logfile "/opt/redis-cluter/7000/logfile/redis.log"
dir "/opt/redis-cluter/7000/data"
