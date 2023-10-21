package com.heowc;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.linecorp.armeria.common.stream.StreamMessage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.common.HttpHeaderNames;
import com.linecorp.armeria.common.HttpHeaders;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.common.QueryParams;
import com.linecorp.armeria.common.ResponseHeaders;
import com.linecorp.armeria.common.stream.HttpDecoder;
import com.linecorp.armeria.common.stream.StreamDecoderInput;
import com.linecorp.armeria.common.stream.StreamDecoderOutput;

import io.netty.buffer.ByteBuf;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootSseTests {

    @LocalServerPort
    private long port;

    @Test
    void test() {
        final WebClient client = WebClient.of(String.format("http://localhost:%d", port));
        final HttpResponse response = client.get("/members/stream-all",
                                                 QueryParams.of("createdAt",
                                                                LocalDateTime.now().minusHours(1)
                                                                             .format(DateTimeFormatter.ISO_DATE_TIME)));

        final StreamMessage<String> decoded = response.decode(new SimpleServerSentMessageDecoder());
        StepVerifier.create(Flux.from(decoded).log())
                    .expectNextCount(5)
                    .expectComplete()
                    .verify();
    }

    private static class SimpleServerSentMessageDecoder implements HttpDecoder<String> {

        private String buffer;

        @Override
        public void process(StreamDecoderInput in, StreamDecoderOutput<String> out) throws Exception {
            final int readableBytes = in.readableBytes();
            final ByteBuf byteBuf = in.readBytes(readableBytes);
            final String data;
            if (buffer != null) {
                data = buffer + byteBuf.toString(StandardCharsets.UTF_8);
                buffer = null;
            } else {
                data = byteBuf.toString(StandardCharsets.UTF_8);
            }

            int begin = 0;
            for (; ; ) {
                final int delim = data.indexOf("\n\n", begin);
                if (delim < 0) {
                    // not enough data
                    buffer = data.substring(begin);
                    return;
                } else {
                    out.add(data.substring(begin, delim));
                    begin = delim + 2;
                }
            }
        }
    }
}
