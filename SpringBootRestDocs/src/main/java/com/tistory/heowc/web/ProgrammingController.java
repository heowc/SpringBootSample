package com.tistory.heowc.web;

import com.tistory.heowc.domain.Programming;
import com.tistory.heowc.repository.ProgrammingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("programming")
public class ProgrammingController {

    @Autowired ProgrammingRepository repository;

    @GetMapping()
    public List<Programming> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Programming findById(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @PostMapping
    public Programming save(@RequestBody Programming programming) {
        return repository.save(programming);
    }

    @DeleteMapping
    public Programming deleteById(@RequestBody Programming programming) {
        repository.delete(programming);
        return programming;
    }

    @PutMapping
    public Programming updateById(@RequestBody Programming programming) {
        return repository.save(programming);
    }
}
