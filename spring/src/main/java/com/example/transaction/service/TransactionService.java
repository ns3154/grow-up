package com.example.transaction.service;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/29 15:20
 **/
public interface TransactionService {

    int create();

    int createWithSimpleTransaction();

    int createWithTransaction();

    String tranSaction();
}
