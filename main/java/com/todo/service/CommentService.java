package com.todo.service;

import com.todo.domain.entity.Comment;
import com.todo.domain.entity.Todo;
import com.todo.domain.repository.CommentRepository;
import com.todo.domain.repository.TodoRepository;
import com.todo.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public Long insert(Long todoSeq, CommentRequest request) {

        Todo todo = todoRepository.findOne(todoSeq);
        request.setTodo(todo);
        Comment comment = Comment.createComment(request);
        todo.insertCommentList(comment);
        commentRepository.insert(comment);

        return comment.getSeq();
    }

    @Transactional(readOnly = true)
    public Comment findOne(Long seq) {

        return commentRepository.findOne(seq);
    }

    public Comment update(Long commentSeq, CommentRequest request) {

        Comment comment = commentRepository.findOne( commentSeq );
        comment.changeCommentInfo( request );

        return comment;
    }

    public void delete(Long todoSeq, Long commentSeq) {

        Todo todo = todoRepository.findOne(todoSeq);
        Comment comment = commentRepository.findOne(commentSeq);
        todo.removeCommentList(comment);

        commentRepository.delete(commentSeq);
    }
}
