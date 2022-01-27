package com.todo.service;

import com.todo.domain.entity.Comment;
import com.todo.domain.entity.Todo;
import com.todo.domain.repository.CommentRepository;
import com.todo.domain.repository.TodoRepository;
import com.todo.dto.CommentDto;
import com.todo.dto.CommentRequest;
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

        Todo todo = todoRepository.findBySeq( todoSeq );
        request.setTodo( todo );
        Comment comment = Comment.createComment( request );
        todo.insertCommentList( comment );
        commentRepository.save( comment );

        return comment.getSeq();
    }

    @Transactional(readOnly = true)
    public CommentDto findOne (Long seq) {

        return new CommentDto( commentRepository.findBySeq( seq ) );
    }

    public CommentDto update (Long commentSeq, CommentRequest request) {

        Comment comment = commentRepository.findBySeq( commentSeq );
        comment.changeCommentInfo( request );

        return new CommentDto(comment);
    }

    public void delete (Long todoSeq, Long commentSeq) {

        Todo todo = todoRepository.findBySeq( todoSeq );
        Comment comment = commentRepository.findBySeq( commentSeq );
        todo.removeCommentList( comment );

        commentRepository.deleteBySeq( commentSeq );
    }
}
