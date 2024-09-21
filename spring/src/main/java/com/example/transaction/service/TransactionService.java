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

    @Operation(summary = "update", description = "update description", method = "PUT", requestBody = @RequestBody(content = {@Content(schema = @Schema(implementation = Dto.class))}), responses = @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(schema = @Schema(implementation = ResponseDto.class))}))
    int createWithTransaction();

    String tranSaction();
}
