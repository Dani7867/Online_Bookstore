package com.danish.bookstore_backend.repositories;

import com.danish.bookstore_backend.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByDescription(String description);

    List<Genre> findByDescriptionIn(List<String> descriptions);
}
