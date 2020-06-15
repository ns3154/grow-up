package com.example.dubbo.consumer.mock;

import com.example.common.api.DubboTestServiceApi;
import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;
import org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/25 16:54
 **/
public class DubboServiceTestMock implements DubboTestServiceApi {

    public DubboServiceTestMock() {
        // nothing
    }

    @Override
    public ModelMessage<UserDTO> userById(Long userId) {
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setData(UserDTO.newBuilder().withSex(1).withUserName("mock").withAge(111).build());
        model.setCode(200);
        model.setMessage("mock");
        return model;
    }

    @Override
    public ModelMessage<UserDTO> byZero(Long userId) {
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setData(UserDTO.newBuilder().withSex(1).withUserName("mock").withAge(111).build());
        model.setCode(200);
        model.setMessage("mock");
        return model;
    }

    @Override
    public ModelMessage<UserDTO> timeOut(Long userId) {
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setData(UserDTO.newBuilder().withSex(1).withUserName("mock").withAge(111).build());
        model.setCode(200);
        model.setMessage("mock");
        return model;
    }

    @Override
    public ModelMessage<UserDTO> randomTimeOut(Long userId) {
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setData(UserDTO.newBuilder().withSex(1).withUserName("mock").withAge(111).build());
        model.setCode(200);
        model.setMessage("mock");
        return model;
    }

    @Override
    public ModelMessage<UserDTO> timeWait(Long time) {
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setData(UserDTO.newBuilder().withSex(1).withUserName("mock").withAge(111).build());
        model.setCode(200);
        model.setMessage("mock");
        return model;
    }
}
