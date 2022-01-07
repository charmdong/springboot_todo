package com.todo.domain.entity;

import com.todo.dto.MemberRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    private String id;

    private String password;

    private String nickname;

    @OneToMany(mappedBy = "member")
    private final List<Todo> todoList = new ArrayList<>();

    // Constructor Method
    public static Member createMember(MemberRequest request) {
        Member member = new Member();

        member.id = request.getId();
        member.password = request.getPassword();
        member.nickname = request.getNickname();

        return member;
    }

    // 연관관계 메서드
    public void changeMemberInfo(MemberRequest request) {

        if(StringUtils.hasText(request.getNickname())) {
            this.nickname = request.getNickname();
        }
    }
}
