package net.openwebinars.springboot.errorhandling.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Note {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String author;
    private boolean important;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();



}
