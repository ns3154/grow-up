package com.example.mcp.config;

import com.example.mcp.service.McpToolsService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

    @Bean
    public ToolCallbackProvider mcpToolsProvider(McpToolsService mcpToolsService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(mcpToolsService)
                .build();
    }
}