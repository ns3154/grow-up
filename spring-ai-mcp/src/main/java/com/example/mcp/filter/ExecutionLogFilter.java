package com.example.mcp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 执行日志过滤器
 * 记录所有HTTP请求和响应的详细信息，包括：
 * - 请求时间、方法、URL、参数
 * - 请求头信息
 * - 请求体内容（对于POST/PUT等）
 * - 响应状态码、响应时间
 * - 响应体内容
 */
@Component
public class ExecutionLogFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionLogFilter.class);
    
    // 需要记录请求体的Content-Type类型
    private static final String[] LOGGABLE_CONTENT_TYPES = {
        "application/json",
        "application/xml",
        "text/plain",
        "text/html"
    };
    
    // 敏感头信息，不记录具体内容
    private static final String[] SENSITIVE_HEADERS = {
        "authorization",
        "cookie",
        "x-api-key",
        "x-auth-token"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        long startTime = System.currentTimeMillis();
        
        // 包装请求和响应以便能够多次读取内容
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        
        try {
            // 记录请求信息
            logRequest(requestWrapper);
            
            // 继续执行过滤器链
            filterChain.doFilter(requestWrapper, responseWrapper);
            
        } finally {
            // 记录响应信息
            long duration = System.currentTimeMillis() - startTime;
            logResponse(responseWrapper, duration);
            
            // 将响应内容复制回原始响应
            responseWrapper.copyBodyToResponse();
        }
    }
    
    /**
     * 记录请求信息
     */
    private void logRequest(ContentCachingRequestWrapper request) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n==================== REQUEST START ====================\n");
        
        // 基本请求信息
        logMessage.append("Method: ").append(request.getMethod()).append("\n");
        logMessage.append("URI: ").append(request.getRequestURI()).append("\n");
        logMessage.append("Query String: ").append(request.getQueryString()).append("\n");
        logMessage.append("Remote Address: ").append(request.getRemoteAddr()).append("\n");
        logMessage.append("Remote Host: ").append(request.getRemoteHost()).append("\n");
        
        // 请求头信息
        logMessage.append("Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = getHeaderValue(headerName, request.getHeader(headerName));
            logMessage.append("  ").append(headerName).append(": ").append(headerValue).append("\n");
        }
        
        // 请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.isEmpty()) {
            logMessage.append("Parameters:\n");
            parameterMap.forEach((key, values) -> {
                String paramValue = values.length == 1 ? values[0] : String.join(", ", values);
                logMessage.append("  ").append(key).append(": ").append(paramValue).append("\n");
            });
        }
        
        // 请求体（如果是可记录的内容类型）
        if (shouldLogRequestBody(request)) {
            String requestBody = getRequestBody(request);
            if (requestBody != null && !requestBody.isEmpty()) {
                logMessage.append("Request Body:\n").append(requestBody).append("\n");
            }
        }
        
        logMessage.append("==================== REQUEST END ====================\n");
        
        logger.info(logMessage.toString());
    }
    
    /**
     * 记录响应信息
     */
    private void logResponse(ContentCachingResponseWrapper response, long duration) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n==================== RESPONSE START ====================\n");
        
        // 基本响应信息
        logMessage.append("Status: ").append(response.getStatus()).append("\n");
        logMessage.append("Duration: ").append(duration).append(" ms\n");
        
        // 响应头信息
        logMessage.append("Headers:\n");
        response.getHeaderNames().forEach(headerName -> {
            String headerValue = getHeaderValue(headerName, response.getHeader(headerName));
            logMessage.append("  ").append(headerName).append(": ").append(headerValue).append("\n");
        });
        
        // 响应体
        String responseBody = getResponseBody(response);
        if (responseBody != null && !responseBody.isEmpty()) {
            logMessage.append("Response Body:\n").append(responseBody).append("\n");
        }
        
        logMessage.append("==================== RESPONSE END ====================\n");
        
        // 根据响应状态选择日志级别
        if (response.getStatus() >= 500) {
            logger.error(logMessage.toString());
        } else if (response.getStatus() >= 400) {
            logger.warn(logMessage.toString());
        } else {
            logger.info(logMessage.toString());
        }
    }
    
    /**
     * 判断是否应该记录请求体
     */
    private boolean shouldLogRequestBody(ContentCachingRequestWrapper request) {
        String contentType = request.getContentType();
        if (contentType == null) {
            return false;
        }
        
        String method = request.getMethod();
        // 只对POST, PUT, PATCH方法记录请求体
        if (!("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method))) {
            return false;
        }
        
        // 检查Content-Type是否在可记录范围内
        for (String loggableType : LOGGABLE_CONTENT_TYPES) {
            if (contentType.toLowerCase().contains(loggableType)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 获取请求体内容
     */
    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            return new String(content, StandardCharsets.UTF_8);
        }
        return null;
    }
    
    /**
     * 获取响应体内容
     */
    private String getResponseBody(ContentCachingResponseWrapper response) {
        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            return new String(content, StandardCharsets.UTF_8);
        }
        return null;
    }
    
    /**
     * 处理敏感头信息，隐藏敏感内容
     */
    private String getHeaderValue(String headerName, String headerValue) {
        if (headerValue == null) {
            return "null";
        }
        
        String lowerHeaderName = headerName.toLowerCase();
        for (String sensitiveHeader : SENSITIVE_HEADERS) {
            if (lowerHeaderName.contains(sensitiveHeader)) {
                return "***HIDDEN***";
            }
        }
        
        // 对于过长的头信息进行截断
        if (headerValue.length() > 200) {
            return headerValue.substring(0, 200) + "...(truncated)";
        }
        
        return headerValue;
    }
}