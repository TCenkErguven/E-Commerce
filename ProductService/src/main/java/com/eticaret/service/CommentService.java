package com.eticaret.service;

import com.eticaret.repository.ICommentRepository;
import com.eticaret.repository.entity.Comment;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends ServiceManager<Comment,String> {
    private final ICommentRepository repository;
    public CommentService(ICommentRepository repository){
        super(repository);
        this.repository = repository;
    }
}
