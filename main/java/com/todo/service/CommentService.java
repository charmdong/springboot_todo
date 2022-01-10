package com.todo.service;

import com.todo.domain.entity.Comment;
import com.todo.domain.repository.CommentRepository;
import com.todo.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Long insert(CommentRequest request) {

        Comment comment = Comment.createComment(request);

        commentRepository.insert(comment);

        return comment.getSeq();
    }

    public void delete(Long seq) {

        commentRepository.delete(seq);
    }
}
