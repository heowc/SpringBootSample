package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.MessageSenderGrpc.MessageSenderBlockingStub;

import com.linecorp.armeria.client.grpc.GrpcClients;
import com.linecorp.armeria.spring.LocalArmeriaPort;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

@SpringBootTest(properties = "grpc.port=0")
class SpringBootGRpcApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootGRpcApplicationTests.class);

    @LocalArmeriaPort
    private int port;

    private MessageSenderBlockingStub blockingStub;

    @BeforeEach
    void init() {
        blockingStub = GrpcClients.newClient(String.format("http://localhost:%d", port),
                                             MessageSenderBlockingStub.class);
    }

    @Test
    void test_success() {
        final MessageSenderProto.MessageRequest messageRequest =
                MessageSenderProto.MessageRequest.newBuilder()
                                                 .setFrom("wonchul")
                                                 .setTo("naeun")
                                                 .setContents("hello!")
                                                 .build();

        try {
            final MessageSenderProto.MessageResponse messageResponse = blockingStub.send(messageRequest);
            assertThat(messageResponse.getStatus()).isEqualTo(Status.OK.toString());
            assertThat(messageResponse.getReason()).isEqualTo("ok");
        } catch (StatusRuntimeException sr) {
            logger.error("status={}", sr.getStatus()); // Status{code=UNKNOWN, description=null, cause=null}
            logger.error("metadata={}", sr.getTrailers()); // Metadata(content-type=application/grpc)
        }
    }

    @Test
    void test_fail() {
        final MessageSenderProto.MessageRequest messageRequest =
                MessageSenderProto.MessageRequest.newBuilder()
                                                 .setFrom("wonchul")
                                                 .setTo("naeun")
                                                 .setContents("")
                                                 .build();

        assertThatThrownBy(() -> {
            blockingStub.send(messageRequest);
        }).asInstanceOf(InstanceOfAssertFactories.type(StatusRuntimeException.class)).satisfies(e -> {
            assertThat(e.getStatus()).isEqualTo(Status.UNKNOWN);
        });
    }
}
