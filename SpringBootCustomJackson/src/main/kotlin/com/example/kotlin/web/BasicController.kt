package com.example.kotlin.web

import com.example.kotlin.domain.Model
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class BasicController {

    companion object {
        private val logger = LoggerFactory.getLogger(BasicController::class.java)
    }

    @GetMapping("/se")
    fun index(): List<Model> {
        val list = ArrayList<Model>()
        list.add(Model("abc", 1))
        list.add(Model("def", 2))
        list.add(Model("ghi", 3))
        list.add(Model("jkl", 4))
        list.add(Model("nmo", 5))
        list.add(Model("pqr", 6))
        list.add(Model("stu", 7))
        list.add(Model("vwx", 8))
        list.add(Model("yz", 9))

        return list
    }

    @PostMapping("/de")
    fun deIndex(@RequestBody deList: List<Model>): List<Model> {
        logger.info(deList.toString())
        return deList
    }
}
