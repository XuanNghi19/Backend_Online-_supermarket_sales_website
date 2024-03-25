package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
