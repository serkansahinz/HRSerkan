package com.hrserkan.repository.entity;

import com.hrserkan.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;//yorumu kim yaptı
    private String username;
    private String avatar;
    private String content;
    @ElementCollection
    private List<String> mediaUrls;
    @ElementCollection
    private List<String>likes;
    //birden fazla yorum nasıl yapılabilir



}
