package com.todo.domain.entity;

import com.todo.dto.TodoRequest;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Todo {

    @Id
    @GeneratedValue
    @Column(name = "TODO_SEQ")
    private Long seq;

    private String content;

    private LocalDateTime expireDate;

    private Boolean completeYn;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "todo")
    private final List<Comment> commentList = new ArrayList<>();

    // Constructor Method
    public Todo createTodo(TodoRequest request) {

        Todo todo = new Todo();

        todo.content = request.getContent();
        todo.expireDate = request.getExpireDate();
        todo.member = request.getMember();

        return todo;
    }

    // 연관 관계 메서드
    public void changeTodoInfo(TodoRequest request) {

        if(StringUtils.hasText(request.getContent())) {
            this.content = request.getContent();
        }

        if(request.getExpireDate() != null) {
            this.expireDate = request.getExpireDate();
        }
    }
}
