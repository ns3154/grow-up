package com.example.common.api;

import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;

import java.util.concurrent.CompletableFuture;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/26 11:27
 **/
public interface AsyncDubboServiceTestApi {

    CompletableFuture<ModelMessage<UserDTO>> getUserIdByAsync(Long userId);

    CompletableFuture<Void> noVoidAsync(Long userId);

    CompletableFuture<ModelMessage<UserDTO>> getUserByAsyncForMyThread(Long userId);
}
