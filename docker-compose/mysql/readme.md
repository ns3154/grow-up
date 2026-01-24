# 创建必要目录

mkdir -p /Users/yang/ops/docker/mysql
mkdir -p /Users/yang/ops/docker/mysql/data
mkdir -p /Users/yang/ops/docker/mysql/config
mkdir -p /Users/yang/ops/docker/mysql/init
mkdir -p /Users/yang/ops/docker/mysql/backup
mkdir -p /Users/yang/ops/docker/mysql/logs


# 创建mysql配置
mysql-cnf.sh

# 使用完整路径启动
docker-compose -f /Users/yang/project/java/grow-up/docker-compose/mysql/mysql-compose.yml up -d

# 查看容器状态
docker-compose -f /Users/yang/project/java/grow-up/docker-compose/mysql/mysql-compose.yml ps


# 只停止不删除
docker-compose -f /Users/yang/project/java/grow-up/docker-compose/mysql/mysql-compose.yml stop



