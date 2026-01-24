cat > /Users/yang/ops/docker/redis/conf/redis.conf << 'EOF'
# Redis 6.0.20 配置文件
# 注意: 6.0.x 版本不支持 set-proc-title 配置

# 基本配置
daemonize no
pidfile /var/run/redis/redis-server.pid
port 6379
bind 0.0.0.0
timeout 0
tcp-keepalive 300
protected-mode yes

# 数据持久化
dir /data
dbfilename dump.rdb
save 900 1
save 300 10
save 60 10000
stop-writes-on-bgsave-error yes
rdbcompression yes
rdbchecksum yes

# AOF 持久化
appendonly yes
appendfilename "appendonly.aof"
appendfsync everysec
no-appendfsync-on-rewrite no
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
aof-load-truncated yes
aof-use-rdb-preamble yes

# 安全设置
requirepass root  # 取消注释并设置密码
rename-command FLUSHDB ""
rename-command FLUSHALL ""
rename-command CONFIG ""

# 日志设置
loglevel notice
logfile "/data/redis.log"
syslog-enabled no

# 内存管理
maxmemory 1gb
maxmemory-policy allkeys-lru
maxmemory-samples 5

# 慢查询日志
slowlog-log-slower-than 10000
slowlog-max-len 128

# 客户端设置
maxclients 10000

# 性能优化
hz 10
activerehashing yes
aof-rewrite-incremental-fsync yes
rdb-save-incremental-fsync yes
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit replica 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60

# 集群配置（如需要）
# cluster-enabled no
# cluster-config-file nodes.conf
# cluster-node-timeout 5000

# Lua 脚本
lua-time-limit 5000
EOF