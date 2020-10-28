package com.example.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/10/28 23:18
 **/
public class FailureAnalyzerTest extends AbstractFailureAnalyzer<MyException> {

	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, MyException cause) {
		return new FailureAnalysis("测试", "测试", cause);
	}
}
