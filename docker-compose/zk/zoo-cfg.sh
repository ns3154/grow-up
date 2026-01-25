# 创建 ZooKeeper 配置文件
cat > /Users/yang/ops/docker/zk/conf/zoo.cfg << 'EOF'
# ZooKeeper 配置文件
# 生成时间: $(date)

# 基本配置
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/data
dataLogDir=/datalog
clientPort=2181
admin.enableServer=true
admin.serverPort=8080

# 自动清理
autopurge.snapRetainCount=3
autopurge.purgeInterval=0

# 高级配置
maxClientCnxns=60
minSessionTimeout=4000
maxSessionTimeout=40000
standaloneEnabled=true
reconfigEnabled=true
skipACL=yes
4lw.commands.whitlist=*

# 集群配置（单机模式不需要）
# server.1=zookeeper1:2888:3888;2181
# server.2=zookeeper2:2888:3888;2182
# server.3=zookeeper3:2888:3888;2183

# 日志配置
zookeeper.console.threshold=INFO
zookeeper.log.dir=/logs
zookeeper.log.file=zookeeper.log
zookeeper.log.threshold=DEBUG
zookeeper.tracelog.dir=/logs
EOF