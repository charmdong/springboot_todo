package com.todo.domain.entity;

import com.todo.dto.CommentRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    @DisplayName("Comment 생성 테스트")
    public void createCommentTest() throws Exception {
        // given
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setContent("hello comment");

        // when
        Comment comment = Comment.createComment(commentRequest);

        // then
        assertThat(comment.getContent()).isEqualTo("hello comment");
    }
}