package com.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class SpringBootGRpcApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootGRpcApplicationTests.class);

    private ManagedChannel channel;

    @BeforeEach
    void init() {
        channel = ManagedChannelBuilder.forAddress("localhost", 6565)
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
