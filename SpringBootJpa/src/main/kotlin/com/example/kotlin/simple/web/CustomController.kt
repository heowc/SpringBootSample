package com.example.kotlin.simple.web

import com.example.kotlin.simple.domain.Customer
import com.example.kotlin.simple.service.CustomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomController(val service: CustomService) {

    @PostMapping
    fun upsert(@RequestBody customer: Customer): ResponseEntity<*> {
        return ResponseEntity.ok(service.upsert(customer))
    }

    @GetMapping(value = ["/findByName"])
    fun findByName(@RequestParam("name") name: String): ResponseEntity<*> {
        return ResponseEntity.ok(service.findByName(name))
    }

    @GetMapping(value = ["/{id}"])
    fun find(@PathVariable("id") id: Long?): ResponseEntity<*> {
        return ResponseEntity.ok(service.find(id))
    }
}