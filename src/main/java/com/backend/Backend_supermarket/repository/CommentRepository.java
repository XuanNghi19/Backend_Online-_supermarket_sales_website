package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
