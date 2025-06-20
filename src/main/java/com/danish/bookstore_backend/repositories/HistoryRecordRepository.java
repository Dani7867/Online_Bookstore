package com.danish.bookstore_backend.repositories;

import com.danish.bookstore_backend.entities.HistoryRecord;
import com.danish.bookstore_backend.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long> {

    Page<HistoryRecord> findByHistoryRecordHolder(Person person, Pageable pageable);
}
