# Spring AI MCP 执行日志过滤器说明

## 功能概述

为 Spring AI MCP 项目增加了执行日志过滤器，用于记录所有 HTTP 请求和响应的详细信息。

## 组件说明

### 1. ExecutionLogFilter (执行日志过滤器)
**位置**: `src/main/java/com/example/mcp/filter/ExecutionLogFilter.java`

**功能特性**:
- 记录所有 HTTP 请求的基本信息（方法、URI、查询参数等）
- 记录请求头信息（自动隐藏敏感头如 Authorization、Cookie 等）
- 记录请求体内容（针对 POST/PUT/PATCH 方法的 JSON/XML 数据）
- 记录请求参数
- 记录响应状态码、响应时间和响应体
- 根据响应状态选择不同的日志级别（5xx 错误用 ERROR，4xx 警告用 WARN，其他用 INFO）

### 2. FilterConfig (过滤器配置)
**位置**: `src/main/java/com/example/mcp/config/FilterConfig.java`

**功能**:
- 注册执行日志过滤器
- 设置过滤器为最高优先级，确保捕获所有请求
- 配置过滤器拦截所有 URL 模式

### 3. 日志配置更新
**位置**: `src/main/resources/application.yml`

**新增配置**:
```yaml
logging:
  level:
    com.example.mcp.filter: INFO  # 执行日志过滤器日志级别
    org.springframework.web: DEBUG # Spring Web 调试日志
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/mcp-server.log
    max-size: 10MB
    max-history: 30
```

## 日志输出示例

### 请求日志格式:
```
==================== REQUEST START ====================
Method: POST
URI: /api/tools/calculate
Query String: null
Remote Address: 127.0.0.1
Remote Host: localhost
Headers:
  content-type: application/json
  user-agent: PostmanRuntime/7.29.2
  authorization: ***HIDDEN***
Parameters:
  param1: value1
Request Body:
{"expression": "2 + 3 * 4"}
==================== REQUEST END ====================
```

### 响应日志格式:
```
==================== RESPONSE START ====================
Status: 200
Duration: 45 ms
Headers:
  content-type: application/json
Response Body:
{"result": "计算结果: 2 + 3 * 4 = 14.00"}
==================== RESPONSE END ====================
```

## 敏感信息安全处理

过滤器会自动隐藏以下敏感头信息的内容：
- Authorization
- Cookie
- X-API-Key
- X-Auth-Token

这些头信息在日志中会显示为 `***HIDDEN***`

## 性能考虑

- 使用 `ContentCachingRequestWrapper` 和 `ContentCachingResponseWrapper` 来缓存请求/响应内容
- 只对特定的 Content-Type 记录请求体（避免二进制文件等大体积内容）
- 过长的头信息会被截断（超过200字符）
- 异步处理确保不影响主业务流程性能

## 使用说明

1. 过滤器会自动生效，无需额外配置
2. 日志默认输出到控制台和文件 `logs/mcp-server.log`
3. 可以通过调整 `application.yml` 中的日志级别来控制详细程度
4. 生产环境中建议将日志级别调整为 WARN 或 ERROR 以减少日志量

## 注意事项

⚠️ **Lombok 兼容性问题**:
当前项目使用 Java 25，可能存在与 Lombok 1.18.36 的兼容性问题。
如果遇到编译错误，请考虑：
1. 升级到最新版 Lombok
2. 临时移除 Lombok 依赖
3. 使用传统 getter/setter 方式

⚠️ **生产环境部署**:
- 建议在生产环境中降低日志级别
- 定期清理日志文件防止磁盘空间不足
- 考虑将敏感信息完全从日志中排除

## 故障排除

如果过滤器不工作，请检查：
1. 确认 `FilterConfig` 类被正确加载
2. 检查是否有其他过滤器干扰
3. 验证日志配置是否正确
4. 查看应用启动日志中的过滤器注册信息