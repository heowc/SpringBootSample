package com.example;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class SpringBootGRpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGRpcApplication.class, args);
	}
}

@GRpcService
class MessageSenderImpl extends MessageSenderGrpc.MessageSenderImplBase {

	private static final Logger logger = LoggerFactory.getLogger(MessageSenderImpl.class);

	@Override
	public void send(MessageSenderProto.MessageRequest request, StreamObserver<MessageSenderProto.MessageResponse> responseObserver) {

		logger.info("send >> request={}", request);

		try {
			if (StringUtils.isEmpty(request.getContents())) {
				throw new IllegalArgumentException("content is empty");
			}

			MessageSenderProto.MessageResponse response = MessageSenderProto.MessageResponse.newBuilder()
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