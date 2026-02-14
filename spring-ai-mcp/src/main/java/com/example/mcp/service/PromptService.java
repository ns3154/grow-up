package com.example.mcp.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PromptService {

    // 提示词模板存储
    private final Map<String, PromptTemplate> promptTemplates = new ConcurrentHashMap<>();
    
    // 预定义的提示词类别
    private final Map<String, List<String>> promptCategories = new ConcurrentHashMap<>();

    public PromptService() {
        initializeDefaultPrompts();
    }

    @Tool(description = "获取所有可用的提示词模板")
    public String getAllPromptTemplates() {
        if (promptTemplates.isEmpty()) {
            return "暂无提示词模板";
        }
        
        StringBuilder result = new StringBuilder("可用的提示词模板:\n");
        for (Map.Entry<String, PromptTemplate> entry : promptTemplates.entrySet()) {
            result.append(String.format("- %s: %s\n", entry.getKey(), entry.getValue().getDescription()));
        }
        return result.toString();
    }

    @Tool(description = "根据名称获取特定提示词模板")
    public String getPromptTemplateByName(
        @ToolParam(description = "提示词模板名称") String templateName
    ) {
        PromptTemplate template = promptTemplates.get(templateName);
        if (template == null) {
            return "未找到名为 '" + templateName + "' 的提示词模板";
        }
        
        return String.format("提示词模板 '%s':\n描述: %s\n内容: %s", 
            templateName, template.getDescription(), template.getContent());
    }

    @Tool(description = "创建新的提示词模板")
    public String createPromptTemplate(
        @ToolParam(description = "模板名称") String name,
        @ToolParam(description = "模板描述") String description,
        @ToolParam(description = "模板内容") String content,
        @ToolParam(description = "模板类别(可选)") String category
    ) {
        if (promptTemplates.containsKey(name)) {
            return "已存在同名的提示词模板: " + name;
        }
        
        PromptTemplate template = new PromptTemplate(name, description, content, category);
        promptTemplates.put(name, template);
        
        // 添加到类别中
        if (category != null && !category.trim().isEmpty()) {
            promptCategories.computeIfAbsent(category, k -> new ArrayList<>()).add(name);
        }
        
        return "成功创建提示词模板: " + name;
    }

    @Tool(description = "根据类别获取提示词模板")
    public String getPromptsByCategory(
        @ToolParam(description = "类别名称") String category
    ) {
        List<String> templates = promptCategories.get(category);
        if (templates == null || templates.isEmpty()) {
            return "该类别下没有提示词模板: " + category;
        }
        
        StringBuilder result = new StringBuilder(String.format("类别 '%s' 下的提示词模板:\n", category));
        for (String templateName : templates) {
            PromptTemplate template = promptTemplates.get(templateName);
            if (template != null) {
                result.append(String.format("- %s: %s\n", templateName, template.getDescription()));
            }
        }
        return result.toString();
    }

    @Tool(description = "优化提示词内容")
    public String optimizePrompt(
        @ToolParam(description = "原始提示词内容") String originalPrompt,
        @ToolParam(description = "优化目标，如: clarity(清晰度), conciseness(简洁性), effectiveness(有效性)") String optimizationGoal
    ) {
        StringBuilder optimizedPrompt = new StringBuilder();
        
        switch (optimizationGoal.toLowerCase()) {
            case "clarity":
                optimizedPrompt.append("请使以下提示词更加清晰明确:\n")
                              .append(originalPrompt)
                              .append("\n\n优化建议:\n")
                              .append("1. 使用具体而非模糊的词汇\n")
                              .append("2. 明确指出期望的输出格式\n")
                              .append("3. 添加必要的上下文信息");
                break;
                
            case "conciseness":
                optimizedPrompt.append("请精简以下提示词，保持核心要求不变:\n")
                              .append(originalPrompt)
                              .append("\n\n优化建议:\n")
                              .append("1. 删除冗余表述\n")
                              .append("2. 合并相似的要求\n")
                              .append("3. 使用更简洁的表达方式");
                break;
                
            case "effectiveness":
                optimizedPrompt.append("请提升以下提示词的有效性:\n")
                              .append(originalPrompt)
                              .append("\n\n优化建议:\n")
                              .append("1. 明确指定任务目标\n")
                              .append("2. 提供具体的执行步骤\n")
                              .append("3. 设定清晰的成功标准");
                break;
                
            default:
                optimizedPrompt.append("通用优化建议:\n")
                              .append("1. 确保指令明确具体\n")
                              .append("2. 提供足够的上下文\n")
                              .append("3. 指明期望的输出格式\n")
                              .append("4. 包含必要的约束条件\n\n")
                              .append("原始提示词:\n")
                              .append(originalPrompt);
        }
        
        return optimizedPrompt.toString();
    }

    @Tool(description = "生成针对特定任务的提示词")
    public String generateTaskPrompt(
        @ToolParam(description = "任务类型，如: translation, summarization, analysis, creative_writing") String taskType,
        @ToolParam(description = "任务具体内容描述") String taskDescription,
        @ToolParam(description = "期望的输出格式") String outputFormat
    ) {
        StringBuilder prompt = new StringBuilder();
        
        switch (taskType.toLowerCase()) {
            case "translation":
                prompt.append("请将以下内容翻译成中文:\n")
                      .append(taskDescription)
                      .append("\n\n要求:\n")
                      .append("1. 保持原文意思准确\n")
                      .append("2. 语言自然流畅\n")
                      .append("3. 符合中文表达习惯");
                break;
                
            case "summarization":
                prompt.append("请对以下内容进行摘要:\n")
                      .append(taskDescription)
                      .append("\n\n要求:\n")
                      .append("1. 提取核心要点\n")
                      .append("2. 保持逻辑清晰\n")
                      .append("3. 字数控制在100-200字");
                break;
                
            case "analysis":
                prompt.append("请分析以下内容:\n")
                      .append(taskDescription)
                      .append("\n\n分析维度:\n")
                      .append("1. 主要观点和论据\n")
                      .append("2. 逻辑结构\n")
                      .append("3. 潜在问题或改进空间");
                break;
                
            case "creative_writing":
                prompt.append("请创作一段文字:\n")
                      .append(taskDescription)
                      .append("\n\n创作要求:\n")
                      .append("1. 内容生动有趣\n")
                      .append("2. 语言富有表现力\n")
                      .append("3. 符合指定主题");
                break;
                
            default:
                prompt.append("请完成以下任务:\n")
                      .append(taskDescription)
                      .append("\n\n执行要求:\n")
                      .append("1. 仔细理解任务要求\n")
                      .append("2. 按步骤完成任务\n")
                      .append("3. 确保输出质量");
        }
        
        if (outputFormat != null && !outputFormat.trim().isEmpty()) {
            prompt.append("\n\n输出格式: ").append(outputFormat);
        }
        
        return prompt.toString();
    }

    // 初始化默认提示词模板
    private void initializeDefaultPrompts() {
        // 翻译类提示词
        createPromptTemplate("中英翻译", "中英文互译的标准提示词", 
            "请将以下内容翻译成{{target_language}}:\n{{content}}\n\n翻译要求:\n1. 保持原意准确\n2. 语言自然流畅\n3. 符合目标语言表达习惯", 
            "translation");
            
        // 总结类提示词
        createPromptTemplate("文章摘要", "生成文章摘要的提示词",
            "请为以下文章生成摘要:\n{{article}}\n\n摘要要求:\n1. 提取核心观点\n2. 字数控制在150字以内\n3. 保持逻辑清晰完整",
            "summarization");
            
        // 分析类提示词
        createPromptTemplate("内容分析", "分析内容结构和要点的提示词",
            "请分析以下内容:\n{{content}}\n\n分析要求:\n1. 识别主要观点\n2. 分析论证逻辑\n3. 指出优缺点\n4. 提出改进建议",
            "analysis");
    }

    // 提示词模板内部类
    private static class PromptTemplate {
        private final String name;
        private final String description;
        private final String content;
        private final String category;
        private final Date createdAt;

        public PromptTemplate(String name, String description, String content, String category) {
            this.name = name;
            this.description = description;
            this.content = content;
            this.category = category;
            this.createdAt = new Date();
        }

        // Getters
        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getContent() { return content; }
        public String getCategory() { return category; }
        public Date getCreatedAt() { return createdAt; }
    }
}