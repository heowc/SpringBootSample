package com.example.kotlin.web

import com.example.kotlin.service.BasicService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class BasicController (val service: BasicService) {

    private val logger = LoggerFactory.getLogger(BasicController::class.java)

    private val webClient = WebClient.builder()
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
            .build()

    @GetMapping("/async")
    fun goAsync(): Mono<String> {
        service.onAsync()
        val str = "Hello Spring Boot Async!!"
        logger.info(str)
        logger.info("==================================")
        return Mono.just(str)
    }

    @GetMapping("/sync")
    fun goSync(): Mono<String> {
        service.onSync()
        val str = "Hello Spring Boot Sync!!"
        logger.info(str)
        logger.info("==================================")
        return Mono.just(str)
    }

    @GetMapping("/{userName}")
    fun showGithubReposByUserName(@PathVariable userName: String): Mono<List<*>> {
        return webClient.get()
                .uri(String.format("/users/%s/repos", userName))
                .retrieve()
                .bodyToMono(List::class.java)
    }
}
