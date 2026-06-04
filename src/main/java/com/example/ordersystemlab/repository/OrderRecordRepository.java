package com.example.ordersystemlab.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ordersystemlab.entity.OrderRecord;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {
	@Query("""
			SELECT o
			FROM OrderRecord o
			WHERE (:productName IS NULL OR o.productName = :productName)
			  AND (:minQuantity IS NULL OR o.quantity >= :minQuantity)
			  AND (:maxQuantity IS NULL OR o.quantity <= :maxQuantity)
			""")
	Page<OrderRecord> searchOrders(@Param("productName") String productName, @Param("minQuantity") Integer minQuantity,
			@Param("maxQuantity") Integer maxQuantity, Pageable pageable);
}
