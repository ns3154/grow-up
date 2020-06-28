package com.example.demo.io;

import org.junit.Test;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/23 18:46
 **/
public class BufferTest {

    @Test
    public void api() {
        // 初始化
        ByteBuffer buffer = ByteBuffer.allocate(8);
        // 存储
        buffer.put((byte) 'a').put((byte) 'b').put((byte) 'c');
        // 修改
        buffer.put(0, (byte) 'e');
        // 翻转
        //  buffer.limit(buffer.position()).position(0) 等价 buffer.flip();
        buffer.flip();

        buffer.compact();
    }
}
