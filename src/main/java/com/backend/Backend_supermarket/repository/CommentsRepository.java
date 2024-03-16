package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
