package com.example.mcp.service;

import com.example.mcp.model.PromptTemplate;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class AdvancedPromptService {

    private final Map<String, PromptTemplate> promptRepository = new ConcurrentHashMap<>();
    private final Map<String, List<String>> categoryIndex = new ConcurrentHashMap<>();
    private final Map<String, List<String>> tagIndex = new ConcurrentHashMap<>();

    public AdvancedPromptService() {
        initializeSamplePrompts();
    }

    @Tool(description = "创建高级提示词模板，支持版本控制和标签")
    public String createAdvancedPrompt(
        @ToolParam(description = "模板名称") String name,
        @ToolParam(description = "详细描述") String description,
        @ToolParam(description = "提示词内容") String content,
        @ToolParam(description = "分类标签") String category,
        @ToolParam(description = "关键词标签(逗号分隔)") String tags,
        @ToolParam(description = "创建者") String createdBy
    ) {
        // 检查重复名称
        if (promptRepository.values().stream().anyMatch(p -> p.getName().equals(name))) {
            return "错误: 已存在同名模板 '" + name + "'";
        }

        PromptTemplate template = new PromptTemplate(name, description, content, category);
        template.setId(UUID.randomUUID().toString());
        template.setCreatedBy(createdBy != null ? createdBy : "system");
        
        // 处理标签
        if (tags != null && !tags.trim().isEmpty()) {
            List<String> tagList = Arrays.asList(tags.split(","))
                .stream()
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .collect(Collectors.toList());
            template.setTags(tagList);
            
            // 建立标签索引
            for (String tag : tagList) {
                tagIndex.computeIfAbsent(tag.toLowerCase(), k -> new ArrayList<>()).add(template.getId());
            }
        }

        promptRepository.put(template.getId(), template);
        
        // 建立分类索引
        if (category != null) {
            categoryIndex.computeIfAbsent(category.toLowerCase(), k -> new ArrayList<>()).add(template.getId());
        }

        return String.format("成功创建提示词模板 '%s' (ID: %s)", name, template.getId());
    }

    @Tool(description = "搜索提示词模板")
    public String searchPrompts(
        @ToolParam(description = "搜索关键词") String keyword,
        @ToolParam(description = "分类筛选") String category,
        @ToolParam(description = "标签筛选") String tag
    ) {
        Set<String> resultSet = new HashSet<>(promptRepository.keySet());

        // 按关键词搜索
        if (keyword != null && !keyword.trim().isEmpty()) {
            Set<String> keywordMatches = promptRepository.entrySet().stream()
                .filter(entry -> entry.getValue().getName().toLowerCase().contains(keyword.toLowerCase()) ||
                               entry.getValue().getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                               entry.getValue().getContent().toLowerCase().contains(keyword.toLowerCase()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
            resultSet.retainAll(keywordMatches);
        }

        // 按分类筛选
        if (category != null && !category.trim().isEmpty()) {
            List<String> categoryMatches = categoryIndex.getOrDefault(category.toLowerCase(), new ArrayList<>());
            resultSet.retainAll(new HashSet<>(categoryMatches));
        }

        // 按标签筛选
        if (tag != null && !tag.trim().isEmpty()) {
            List<String> tagMatches = tagIndex.getOrDefault(tag.toLowerCase(), new ArrayList<>());
            resultSet.retainAll(new HashSet<>(tagMatches));
        }

        if (resultSet.isEmpty()) {
            return "未找到匹配的提示词模板";
        }

        StringBuilder result = new StringBuilder("搜索结果:\n");
        for (String id : resultSet) {
            PromptTemplate template = promptRepository.get(id);
            result.append(String.format("- [%s] %s (%s) - v%d\n", 
                template.getCategory(), template.getName(), id, template.getVersion()));
        }

        return result.toString();
    }

    @Tool(description = "获取提示词模板详情")
    public String getPromptDetails(
        @ToolParam(description = "模板ID") String templateId
    ) {
        PromptTemplate template = promptRepository.get(templateId);
        if (template == null) {
            return "未找到ID为 '" + templateId + "' 的提示词模板";
        }

        StringBuilder details = new StringBuilder();
        details.append("=== 提示词模板详情 ===\n");
        details.append(String.format("ID: %s\n", template.getId()));
        details.append(String.format("名称: %s\n", template.getName()));
        details.append(String.format("分类: %s\n", template.getCategory()));
        details.append(String.format("描述: %s\n", template.getDescription()));
        details.append(String.format("版本: %d\n", template.getVersion()));
        details.append(String.format("创建者: %s\n", template.getCreatedBy()));
        details.append(String.format("创建时间: %s\n", template.getCreatedAt()));
        details.append(String.format("更新时间: %s\n", template.getUpdatedAt()));
        
        if (template.getTags() != null && !template.getTags().isEmpty()) {
            details.append(String.format("标签: %s\n", String.join(", ", template.getTags())));
        }
        
        details.append("\n内容:\n").append(template.getContent());

        return details.toString();
    }

    @Tool(description = "更新提示词模板")
    public String updatePrompt(
        @ToolParam(description = "模板ID") String templateId,
        @ToolParam(description = "新内容") String newContent,
        @ToolParam(description = "更新说明") String changeDescription,
        @ToolParam(description = "更新者") String updatedBy
    ) {
        PromptTemplate template = promptRepository.get(templateId);
        if (template == null) {
            return "未找到ID为 '" + templateId + "' 的提示词模板";
        }

        // 保存旧版本
        PromptTemplate.PromptVersion oldVersion = new PromptTemplate.PromptVersion(
            template.getVersion(),
            template.getContent(),
            template.getCreatedBy(),
            "自动保存版本"
        );

        if (template.getVersions() == null) {
            template.setVersions(new ArrayList<>());
        }
        template.getVersions().add(oldVersion);

        // 更新模板
        template.setContent(newContent);
        template.setCreatedBy(updatedBy != null ? updatedBy : "system");

        return String.format("成功更新提示词模板 '%s' 至版本 %d", template.getName(), template.getVersion());
    }

    @Tool(description = "获取模板版本历史")
    public String getPromptVersionHistory(
        @ToolParam(description = "模板ID") String templateId
    ) {
        PromptTemplate template = promptRepository.get(templateId);
        if (template == null) {
            return "未找到ID为 '" + templateId + "' 的提示词模板";
        }

        if (template.getVersions() == null || template.getVersions().isEmpty()) {
            return String.format("模板 '%s' 没有版本历史记录", template.getName());
        }

        StringBuilder history = new StringBuilder(String.format("'%s' 的版本历史:\n", template.getName()));
        
        // 当前版本
        history.append(String.format("当前版本 (v%d):\n", template.getVersion()));
        history.append(String.format("  内容: %s...\n", 
            template.getContent().substring(0, Math.min(50, template.getContent().length()))));
        history.append(String.format("  更新时间: %s\n\n", template.getUpdatedAt()));

        // 历史版本
        for (int i = template.getVersions().size() - 1; i >= 0; i--) {
            PromptTemplate.PromptVersion version = template.getVersions().get(i);
            history.append(String.format("版本 %d:\n", version.getVersionNumber()));
            history.append(String.format("  修改者: %s\n", version.getModifiedBy()));
            history.append(String.format("  修改时间: %s\n", version.getModifiedAt()));
            history.append(String.format("  说明: %s\n", version.getChangeDescription()));
            history.append(String.format("  内容预览: %s...\n\n", 
                version.getContent().substring(0, Math.min(50, version.getContent().length()))));
        }

        return history.toString();
    }

    @Tool(description = "按分类浏览提示词")
    public String browseByCategory() {
        if (categoryIndex.isEmpty()) {
            return "暂无分类数据";
        }

        StringBuilder result = new StringBuilder("提示词分类浏览:\n");
        for (Map.Entry<String, List<String>> entry : categoryIndex.entrySet()) {
            result.append(String.format("\n[%s] (%d个模板):\n", 
                entry.getKey(), entry.getValue().size()));
            
            for (String templateId : entry.getValue()) {
                PromptTemplate template = promptRepository.get(templateId);
                if (template != null) {
                    result.append(String.format("  - %s (v%d)\n", template.getName(), template.getVersion()));
                }
            }
        }

        return result.toString();
    }

    @Tool(description = "获取热门标签")
    public String getPopularTags() {
        if (tagIndex.isEmpty()) {
            return "暂无标签数据";
        }

        // 统计标签使用频率
        Map<String, Integer> tagCount = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : tagIndex.entrySet()) {
            tagCount.put(entry.getKey(), entry.getValue().size());
        }

        // 按使用频率排序
        List<Map.Entry<String, Integer>> sortedTags = tagCount.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(10)
            .collect(Collectors.toList());

        StringBuilder result = new StringBuilder("热门标签:\n");
        for (Map.Entry<String, Integer> entry : sortedTags) {
            result.append(String.format("- %s (%d个模板)\n", entry.getKey(), entry.getValue()));
        }

        return result.toString();
    }

    // 初始化示例数据
    private void initializeSamplePrompts() {
        createAdvancedPrompt(
            "代码审查助手",
            "帮助审查代码质量和最佳实践",
            "请审查以下代码并提供改进建议:\n{{code}}\n\n审查要点:\n1. 代码可读性和结构\n2. 性能优化建议\n3. 安全性检查\n4. 最佳实践遵循情况",
            "development",
            "code_review,quality,programming",
            "system"
        );

        createAdvancedPrompt(
            "产品需求分析",
            "分析和梳理产品需求文档",
            "请分析以下产品需求:\n{{requirements}}\n\n分析维度:\n1. 核心功能点识别\n2. 用户价值评估\n3. 技术可行性分析\n4. 优先级建议\n5. 潜在风险识别",
            "product",
            "requirements,analysis,product_management",
            "system"
        );

        createAdvancedPrompt(
            "会议纪要整理",
            "将会议记录整理成结构化纪要",
            "请整理以下会议记录:\n{{meeting_notes}}\n\n整理要求:\n1. 提取关键决策点\n2. 明确行动项和负责人\n3. 总结主要讨论内容\n4. 标注重要时间节点",
            "business",
            "meeting,documentation,organization",
            "system"
        );
    }
}