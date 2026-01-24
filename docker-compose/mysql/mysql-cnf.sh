cat > /Users/yang/ops/docker/mysql/config/my.cnf << 'EOF'
[mysqld]
# 基础配置
port = 3306
socket = /var/run/mysqld/mysqld.sock
datadir = /var/lib/mysql
pid-file = /var/run/mysqld/mysqld.pid

# 字符集设置
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
init-connect = 'SET NAMES utf8mb4'

# 连接配置
max_connections = 1000
max_connect_errors = 1000
wait_timeout = 28800
interactive_timeout = 28800

# 缓冲池配置
innodb_buffer_pool_size = 512M
innodb_buffer_pool_instances = 8

# 日志配置
log_error = /var/log/mysql/error.log
slow_query_log = 1
slow_query_log_file = /var/log/mysql/slow-query.log
long_query_time = 2
log_queries_not_using_indexes = 1

# 二进制日志
server-id = 1
log-bin = mysql-bin
expire_logs_days = 7
binlog_format = row

# 安全配置
skip_name_resolve = 1
lower_case_table_names = 1

[mysql]
default-character-set = utf8mb4

[client]
default-character-set = utf8mb4
port = 3306
EOF