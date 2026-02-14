# MCP提示词服务使用指南

## 概述
本MCP服务提供了丰富的提示词管理功能，包括基础提示词操作和高级提示词管理。

## 可用工具

### 基础提示词服务 (PromptService)

#### 1. 获取所有提示词模板
```
方法: getAllPromptTemplates()
描述: 获取所有可用的提示词模板列表
```

#### 2. 根据名称获取提示词
```
方法: getPromptTemplateByName(templateName)
参数: templateName - 提示词模板名称
描述: 获取特定名称的提示词模板详情
```

#### 3. 创建新提示词模板
```
方法: createPromptTemplate(name, description, content, category)
参数: 
- name: 模板名称
- description: 模板描述  
- content: 模板内容
- category: 模板类别(可选)
描述: 创建新的提示词模板
```

#### 4. 按类别获取提示词
```
方法: getPromptsByCategory(category)
参数: category - 类别名称
描述: 获取指定类别下的所有提示词模板
```

#### 5. 优化提示词
```
方法: optimizePrompt(originalPrompt, optimizationGoal)
参数:
- originalPrompt: 原始提示词内容
- optimizationGoal: 优化目标(clarity, conciseness, effectiveness)
描述: 根据指定目标优化提示词内容
```

#### 6. 生成任务提示词
```
方法: generateTaskPrompt(taskType, taskDescription, outputFormat)
参数:
- taskType: 任务类型(translation, summarization, analysis, creative_writing)
- taskDescription: 任务具体描述
- outputFormat: 期望的输出格式
描述: 为特定任务生成专业的提示词
```

### 高级提示词服务 (AdvancedPromptService)

#### 1. 创建高级提示词模板
```
方法: createAdvancedPrompt(name, description, content, category, tags, createdBy)
参数:
- name: 模板名称
- description: 详细描述
- content: 提示词内容
- category: 分类标签
- tags: 关键词标签(逗号分隔)
- createdBy: 创建者
描述: 创建支持版本控制和标签的高级提示词模板
```

#### 2. 搜索提示词模板
```
方法: searchPrompts(keyword, category, tag)
参数:
- keyword: 搜索关键词
- category: 分类筛选
- tag: 标签筛选
描述: 多维度搜索提示词模板
```

#### 3. 获取提示词详情
```
方法: getPromptDetails(templateId)
参数: templateId - 模板ID
描述: 获取提示词模板的详细信息
```

#### 4. 更新提示词模板
```
方法: updatePrompt(templateId, newContent, changeDescription, updatedBy)
参数:
- templateId: 模板ID
- newContent: 新内容
- changeDescription: 更新说明
- updatedBy: 更新者
描述: 更新提示词模板并保存版本历史
```

#### 5. 获取版本历史
```
方法: getPromptVersionHistory(templateId)
参数: templateId - 模板ID
描述: 查看提示词模板的版本变更历史
```

#### 6. 按分类浏览
```
方法: browseByCategory()
描述: 浏览所有分类及其包含的提示词模板
```

#### 7. 获取热门标签
```
方法: getPopularTags()
描述: 查看使用频率最高的标签
```

## 使用示例

### 示例1: 创建翻译提示词
```
createPromptTemplate(
    "英语翻译助手",
    "将英文内容翻译成中文的专业提示词",
    "请将以下英文内容翻译成中文:\n{{english_text}}\n\n翻译要求:\n1. 保持原文意思准确\n2. 语言自然流畅\n3. 符合中文表达习惯\n4. 专业术语处理得当",
    "translation"
)
```

### 示例2: 优化现有提示词
```
optimizePrompt(
    "写一篇关于AI的文章",
    "clarity"
)
```

### 示例3: 生成代码审查提示词
```
generateTaskPrompt(
    "analysis",
    "审查这段Python代码的质量和最佳实践",
    "以要点列表形式输出审查结果"
)
```

### 示例4: 高级搜索
```
searchPrompts(
    "代码",
    "development",
    "programming"
)
```

## 预设提示词模板

系统初始化时会自动创建以下模板:

1. **中英翻译** - 标准翻译提示词模板
2. **文章摘要** - 文章摘要生成模板  
3. **内容分析** - 内容结构分析模板
4. **代码审查助手** - 代码质量审查模板
5. **产品需求分析** - 产品需求文档分析模板
6. **会议纪要整理** - 会议记录整理模板

## 最佳实践

1. **命名规范**: 使用清晰、具体的模板名称
2. **分类管理**: 合理使用分类标签便于查找
3. **版本控制**: 重要提示词应使用高级服务进行版本管理
4. **标签系统**: 使用相关标签提高搜索效率
5. **定期优化**: 根据使用效果持续优化提示词内容

## 错误处理

所有工具方法都会返回明确的成功或失败消息，便于调试和问题排查。