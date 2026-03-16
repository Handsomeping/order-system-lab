package com.example.ordersystemlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ordersystemlab.entity.OrderRecord;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {
}
