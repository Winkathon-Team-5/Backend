package com.github.winkathon.tripee.domain.user.schema;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.winkathon.tripee.common.schema.BaseSchema;
import com.github.winkathon.tripee.domain.post.schema.Post;
import com.github.winkathon.tripee.domain.upload.schema.Image;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User extends BaseSchema {

    private String userId;

    @JsonIgnore
    private String password;

    private String name;

    private Image avatar;

    @JsonIgnore
    private List<Post> paidPosts;

    @JsonIgnore
    private List<Post> savedPosts;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof User user))
            return false;

        return getId().equals(user.getId());
    }
}
