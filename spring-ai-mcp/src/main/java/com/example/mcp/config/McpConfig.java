package com.example.mcp.config;

import com.example.mcp.service.McpToolsService;
import com.example.mcp.service.PromptService;
import com.example.mcp.service.AdvancedPromptService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

    @Bean
    public ToolCallbackProvider mcpToolsProvider(McpToolsService mcpToolsService, 
                                               PromptService promptService,
                                               AdvancedPromptService advancedPromptService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(mcpToolsService, promptService, advancedPromptService)
                .build();
    }
}