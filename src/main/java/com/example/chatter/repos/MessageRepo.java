package com.example.chatter.repos;

import com.example.chatter.domain.Message;
import com.example.chatter.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
    Page<Message> findAll(Pageable pageable);

    Page<Message> findByTag(String tag, Pageable pageable);

    Page<Message> findByAuthor(User author, Pageable pageable);
}
