package com.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootGRpcApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootGRpcApplicationTests.class);

    private ManagedChannel channel;

    @Before
    public void init() {
        channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
    }

    @Test
    public void test() {
        MessageSenderGrpc.MessageSenderBlockingStub messageSenderBlockingStub = MessageSenderGrpc.newBlockingStub(channel);

        MessageSenderProto.MessageRequest messageRequest = MessageSenderProto.MessageRequest.newBuilder()
                .setFrom("wonchul")
                .setTo("naeun")
                .setContents("hello!")
                .build();

        try {
            MessageSenderProto.MessageResponse messageResponse = messageSenderBlockingStub.send(messageRequest);
            logger.info("test >> response={}", messageResponse);
        } catch (StatusRuntimeException sr) {
            logger.error("status={}", sr.getStatus()); // Status{code=UNKNOWN, description=null, cause=null}
            logger.error("metadata={}", sr.getTrailers()); // Metadata(content-type=application/grpc)
        }
    }
}
