package com.backend.Backend_supermarket.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.Backend_supermarket.models.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}
