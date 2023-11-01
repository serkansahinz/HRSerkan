package com.hrserkan.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
public class Comment extends BaseEntity{
    @Id
    private Long id;
    private String userId;//yorumu kim yaptı
    private String username;
    private String avatar;
    private String comment;//yapılan yorum
    private List<String> subCommentId; //commentId
    private List<String> commentLikes; //userId
    //birden fazla yorum nasıl yapılabilir



}
