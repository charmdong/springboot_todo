package com.todo.api;

import com.todo.domain.entity.Member;
import com.todo.domain.repository.MemberRepository;
import com.todo.dto.MemberDto;
import com.todo.dto.joinForm;
import com.todo.exception.login.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;

    @PostMapping("/login")
    public ResponseEntity login(joinForm form, HttpServletResponse response) throws LoginException {

        Optional<Member> member = memberRepository.findById(form.getId()).filter(m -> m.getPassword().equals(form.getPassword()));

        // 아이디 혹은 비밀번호가 다름.
        if(member.isEmpty()) {
            throw new LoginException("아이디 또는 비밀번호가 잘못됐습니다.");
        }

        // Cookie
        Cookie cookie = new Cookie("memberId", member.get().getId());
        response.addCookie(cookie);

        return new ResponseEntity(member.map(MemberDto::new), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ResponseEntity("LOGOUT", HttpStatus.OK);
    }
}
