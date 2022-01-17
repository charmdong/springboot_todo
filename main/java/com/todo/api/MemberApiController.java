package com.todo.api;

import com.todo.domain.entity.Member;
import com.todo.dto.MemberDto;
import com.todo.dto.MemberRequest;
import com.todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping
    public String hello (@RequestParam("param") String param) {

        return "hello " + param;
    }

    @PostMapping
    public ResponseEntity join (MemberRequest request) {

        String id = memberService.insert( request );

        return new ResponseEntity( id, HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity findMember (@PathVariable("id") String id) {

        MemberDto findMember = memberService.findOne( id );

        return new ResponseEntity( findMember, HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember (@PathVariable("id") String id) {

        memberService.delete( id );

        return new ResponseEntity( HttpStatus.OK );
    }
}
