package com.example.transaction;

import com.example.model.ModelMessge;
import com.example.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/29 15:19
 **/
@RestController
@RequestMapping("transaction")
public class Controller {

    @Resource
    private TransactionService transactionService;


    @PostMapping("createNonTransaction")
    public ModelMessge<String> createNonTransaction() {
        int i = transactionService.create();
        return new ModelMessge<String>().ok(i + "");
    }

    @PostMapping("createWithSimpleTransaction")
    public ModelMessge<String> createWithSimpleTransaction() {
        int i = transactionService.createWithSimpleTransaction();
        return new ModelMessge<String>().ok(i + "");
    }

    @PostMapping("createWithTransaction")
    public ModelMessge<String> createWithTransaction() {
        int i = transactionService.createWithTransaction();
        return new ModelMessge<String>().ok(i + "");
    }

    @PostMapping("tranSaction")
    public ModelMessge<String> tranSaction() {
        return new ModelMessge<String>().ok(transactionService.tranSaction());
    }


}
