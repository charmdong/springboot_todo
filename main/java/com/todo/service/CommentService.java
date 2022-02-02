package com.todo.service;

import com.todo.domain.entity.Comment;
import com.todo.domain.entity.Todo;
import com.todo.domain.repository.CommentRepository;
import com.todo.domain.repository.TodoRepository;
import com.todo.dto.CommentDto;
import com.todo.dto.CommentRequest;
import com.todo.exception.comment.CommentNotFoundException;
import com.todo.exception.comment.DeleteCommentException;
import com.todo.exception.comment.InsertCommentException;
import com.todo.exception.todo.TodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public Long insert (Long todoSeq, CommentRequest request) {

        Todo todo = todoRepository.findBySeq(todoSeq)
                        .orElseThrow(() -> new InsertCommentException(
                                new CommentNotFoundException("댓글 정보가 존재하지 않습니다.")
                        ));
        request.setTodo(todo);
        Comment comment = Comment.createComment(request);
        todo.insertCommentList(comment);

        try {
            commentRepository.save(comment);
        }
        catch (IllegalArgumentException ie) {
            throw new InsertCommentException(ie);
        }

        return comment.getSeq();
    }

    @Transactional(readOnly = true)
    public CommentDto findOne (Long seq) {

        Comment comment = commentRepository.findBySeq(seq)
                .orElseThrow(() -> new CommentNotFoundException("댓글 정보가 존재하지 않습니다."));

        return new CommentDto(comment);
    }

    public CommentDto update (Long commentSeq, CommentRequest request) {

        Comment comment = commentRepository
                .findBySeq(commentSeq)
                .orElseThrow(() -> new CommentNotFoundException("댓글 정보가 존재하지 않습니다."));
        comment.changeCommentInfo(request);

        return new CommentDto(comment);
    }

    public void delete (Long todoSeq, Long commentSeq) {

        Todo todo = todoRepository
                .findBySeq(todoSeq)
                .orElseThrow(() -> new DeleteCommentException(
                        new TodoNotFoundException("Todo 정보가 존재하지 않습니다.")
                ));

        Comment comment = commentRepository
                .findBySeq(commentSeq)
                .orElseThrow(() -> new CommentNotFoundException("댓글 정보가 존재하지 않습니다."));

        todo.removeCommentList(comment);

        commentRepository.deleteBySeq(commentSeq);
    }
}
