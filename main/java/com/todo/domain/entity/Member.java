package com.todo.domain.entity;

import com.todo.dto.MemberRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements Persistable<String> {

    @Id
    @Column(name = "member_id")
    private String id;

    private String password;

    private String nickname;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private final List<Todo> todoList = new ArrayList<>();

    @Override
    public boolean isNew () {
        return createDate == null;
    }

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

    public void insertTodoList(Todo todo) {

        this.todoList.add(todo);
    }

    public void removeTodoList(Todo todo) {

        this.todoList.remove(todo);
    }
}
