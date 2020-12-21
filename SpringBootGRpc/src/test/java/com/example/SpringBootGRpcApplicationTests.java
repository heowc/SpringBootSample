package com.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lognet.springboot.grpc.context.LocalRunningGrpcPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(properties = "grpc.port=0")
class SpringBootGRpcApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootGRpcApplicationTests.class);

    @LocalRunningGrpcPort
    private int port;

    private ManagedChannel channel;

    @BeforeEach
    void init() {
        channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();
    }

    @Test
    void test_success() {
        final MessageSenderGrpc.MessageSenderBlockingStub messageSenderBlockingStub = MessageSenderGrpc.newBlockingStub(channel);

        final MessageSenderProto.MessageRequest messageRequest = MessageSenderProto.MessageRequest.newBuilder()
                .setFrom("wonchul")
                .setTo("naeun")
                .setContents("hello!")
                .build();

        try {
            final MessageSenderProto.MessageResponse messageResponse = messageSenderBlockingStub.send(messageRequest);
            assertThat(messageResponse.getStatus()).isEqualTo(Status.OK.toString());
            assertThat(messageResponse.getReason()).isEqualTo("ok");
        } catch (StatusRuntimeException sr) {
            logger.error("status={}", sr.getStatus()); // Status{code=UNKNOWN, description=null, cause=null}
            logger.error("metadata={}", sr.getTrailers()); // Metadata(content-type=application/grpc)
        }
    }

    @Test
    void test_fail() {
        final MessageSenderGrpc.MessageSenderBlockingStub messageSenderBlockingStub = MessageSenderGrpc.newBlockingStub(channel);

        final MessageSenderProto.MessageRequest messageRequest = MessageSenderProto.MessageRequest.newBuilder()
                .setFrom("wonchul")
                .setTo("naeun")
                .setContents("")
                .build();

        assertThatThrownBy(()-> {
            messageSenderBlockingStub.send(messageRequest);
        }).asInstanceOf(InstanceOfAssertFactories.type(StatusRuntimeException.class)).satisfies(e -> {
            assertThat(e.getStatus()).isEqualTo(Status.UNKNOWN);
        });
    }
}
