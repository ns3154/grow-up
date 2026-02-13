package com.example.mcp;

import org.springframework.stereotype.Component;

// NOTE: MCP 注解包名基于 Spring AI 文档推断，
// 如果 IDE 报错，可根据提示自动优化 import。
import org.springframework.ai.mcp.annotations.McpTool;
import org.springframework.ai.mcp.annotations.McpToolParam;

@Component
public class CalculatorTools {

    @McpTool(name = "add", description = "Add two numbers together")
    public int add(
            @McpToolParam(description = "First number", required = true) int a,
            @McpToolParam(description = "Second number", required = true) int b) {
        return a + b;
    }

    @McpTool(name = "echo", description = "Echo a message back")
    public String echo(
            @McpToolParam(description = "Message to echo", required = true) String message) {
        return "Echo from Spring AI MCP: " + message;
    }
}

