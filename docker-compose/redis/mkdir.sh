# 创建创建目录的脚本
#!/bin/bash
echo "正在创建 Redis 目录结构..."

# 定义基础路径
BASE_PATH="/Users/yang/ops/docker/redis"

# 要创建的目录列表
DIRS=("data" "conf" "logs" "work" "pid" "tmp" "backup" "init")

# 创建父目录
echo "创建父目录: $BASE_PATH"
mkdir -p "$BASE_PATH"

# 遍历创建子目录
for dir in "${DIRS[@]}"; do
  full_path="$BASE_PATH/$dir"
  if [ ! -d "$full_path" ]; then
    echo "创建目录: $full_path"
    mkdir -p "$full_path"
  else
    echo "目录已存在: $full_path"
  fi
done

# 设置权限
echo "设置目录权限..."
chmod 755 "$BASE_PATH"
find "$BASE_PATH" -type d -exec chmod 755 {} \;

# 验证创建结果
echo ""
echo "✅ 目录创建完成"
echo "创建的结构:"
tree "$BASE_PATH" 2>/dev/null || ls -la "$BASE_PATH"
EOF
