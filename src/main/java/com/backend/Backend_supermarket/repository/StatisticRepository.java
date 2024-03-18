package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}
