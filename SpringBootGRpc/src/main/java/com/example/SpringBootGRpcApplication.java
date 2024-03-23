package com.example;

import com.linecorp.armeria.server.grpc.GrpcService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class SpringBootGRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGRpcApplication.class, args);
    }
}

@Configuration
class ArmeriaConfig {

    @Bean
    ArmeriaServerConfigurator armeriaServerConfigurator(MessageSenderService service,
                                                        @Value("${grpc.port}") int port) {
        return builder -> {
            builder.http(port)
                   .service(GrpcService.builder()
                                       .addService(service)
                                       .build());
        };
    }
}

@Service
class MessageSenderService extends MessageSenderGrpc.MessageSenderImplBase {

    private static final Logger logger = LoggerFactory.getLogger(MessageSenderService.class);

    @Override
    public void send(MessageSenderProto.MessageRequest request,
                     StreamObserver<MessageSenderProto.MessageResponse> responseObserver) {

        logger.info("send >> request={}", request);

        try {
            if (!StringUtils.hasText(request.getContents())) {
                throw new IllegalArgumentException("content is empty");
            }

            final MessageSenderProto.MessageResponse response =
                    MessageSenderProto.MessageResponse.newBuilder()
                                                      .setStatus(Status.OK.toString())
                                                      .setReason("ok")
                                                      .build();

            responseObserver.onNext(response);
        } catch (Exception ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }

    }
}