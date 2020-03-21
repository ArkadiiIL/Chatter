package com.example.chatter.domain.util;

import com.example.chatter.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author)
    {
        return author == null?"<none>":author.getUsername();
    }
}
