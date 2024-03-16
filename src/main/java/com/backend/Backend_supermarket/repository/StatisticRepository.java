package com.backend.Backend_supermarket.repository;

import org.hibernate.stat.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistics, Long> {
}
