package com.example.mcp.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class McpToolsService {

    @Tool(description = "获取当前时间和日期")
    public String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Tool(description = "执行基本数学运算 (+, -, *, /)")
    public String calculate(
        @ToolParam(description = "数学表达式，如 '2 + 3 * 4'") String expression
    ) {
        try {
            double result = evaluateExpression(expression);
            return String.format("计算结果: %s = %.2f", expression, result);
        } catch (Exception e) {
            return "计算错误: " + e.getMessage();
        }
    }

    @Tool(description = "文本处理工具，支持大小写转换、反转等操作")
    public String processText(
        @ToolParam(description = "要处理的文本") String text,
        @ToolParam(description = "操作类型: uppercase, lowercase, reverse, word_count") String operation
    ) {
        switch (operation.toLowerCase()) {
            case "uppercase":
                return text.toUpperCase();
            case "lowercase":
                return text.toLowerCase();
            case "reverse":
                return new StringBuilder(text).reverse().toString();
            case "word_count":
                return String.valueOf(text.trim().split("\\s+").length);
            default:
                return "不支持的操作: " + operation;
        }
    }

    private double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", "");
        
        if (!expression.matches("[0-9+\\-*/.()]+")) {
            throw new IllegalArgumentException("表达式包含无效字符");
        }
        
        return parseExpression(expression.toCharArray(), new int[]{0});
    }

    private double parseExpression(char[] expr, int[] pos) {
        double result = parseTerm(expr, pos);
        
        while (pos[0] < expr.length && (expr[pos[0]] == '+' || expr[pos[0]] == '-')) {
            char op = expr[pos[0]++];
            if (op == '+') {
                result += parseTerm(expr, pos);
            } else {
                result -= parseTerm(expr, pos);
            }
        }
        
        return result;
    }

    private double parseTerm(char[] expr, int[] pos) {
        double result = parseFactor(expr, pos);
        
        while (pos[0] < expr.length && (expr[pos[0]] == '*' || expr[pos[0]] == '/')) {
            char op = expr[pos[0]++];
            if (op == '*') {
                result *= parseFactor(expr, pos);
            } else {
                double divisor = parseFactor(expr, pos);
                if (divisor == 0) {
                    throw new ArithmeticException("除数不能为零");
                }
                result /= divisor;
            }
        }
        
        return result;
    }

    private double parseFactor(char[] expr, int[] pos) {
        if (pos[0] < expr.length && expr[pos[0]] == '(') {
            pos[0]++;
            double result = parseExpression(expr, pos);
            pos[0]++;
            return result;
        }
        
        return parseNumber(expr, pos);
    }

    private double parseNumber(char[] expr, int[] pos) {
        StringBuilder num = new StringBuilder();
        
        if (pos[0] < expr.length && (expr[pos[0]] == '-' || expr[pos[0]] == '+')) {
            num.append(expr[pos[0]++]);
        }
        
        while (pos[0] < expr.length && 
               (Character.isDigit(expr[pos[0]]) || expr[pos[0]] == '.')) {
            num.append(expr[pos[0]++]);
        }
        
        try {
            return Double.parseDouble(num.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("无效的数字: " + num.toString());
        }
    }
}