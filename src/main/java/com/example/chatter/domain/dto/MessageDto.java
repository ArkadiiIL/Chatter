package com.example.chatter.domain.dto;

import com.example.chatter.domain.Message;
import com.example.chatter.domain.User;
import com.example.chatter.domain.util.MessageHelper;

public class MessageDto {
    private Long id;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private Long likes;
    private Boolean meLiked;

    public MessageDto(Message message, Long likes, Boolean meLiked) {
        id = message.getId();
        text = message.getText();
        tag = message.getTag();
        author = message.getAuthor();
        filename = message.getFilename();
        this.likes = likes;
        this.meLiked = meLiked;
    }
    public String getAuthorName()
    {
        return MessageHelper.getAuthorName(author);
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public User getAuthor() {
        return author;
    }

    public String getFilename() {
        return filename;
    }

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}
