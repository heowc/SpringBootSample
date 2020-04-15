package com.example.web;

import com.example.domain.Member;
import com.example.domain.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member for API")
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository repository;

    public MemberController(MemberRepository repository) {
        this.repository = repository;
    }

    @Operation(
        summary = "getId",
        description = "아이디 조회",
        method = "GET"
    )
    @GetMapping("/{id}")
    public Member getId(@PathVariable("id") String id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return repository.save(member);
    }

    @PutMapping
    public Member updateMember(@RequestBody Member member) {
        return repository.save(member);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") String id) {
        try {
            repository.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }
}
