package org.example.webphim.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;
    Integer displayOrder;
    Boolean status;
    String videoUrl;
    Integer duration;

    Date createdAt;
    Date updatedAt;
    Date publishedAt;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;
}
