package com.todo.api;

import com.todo.constant.SessionConst;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;

    @PostMapping("/login")
    public ResponseEntity login(joinForm form, HttpServletRequest request) throws LoginException {

        Optional<Member> member = memberRepository.findById(form.getId())
                .filter(m -> m.getPassword().equals(form.getPassword()));

        // 아이디 혹은 비밀번호가 다름.
        if(member.isEmpty()) {
            throw new LoginException("아이디 또는 비밀번호가 잘못됐습니다.");
        }

        HttpSession session = request.getSession(false);
        session.setAttribute(SessionConst.LOGIN_MEMBER, member.get());

        return new ResponseEntity(member.map(MemberDto::new), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity("LOGOUT", HttpStatus.OK);
    }
}
