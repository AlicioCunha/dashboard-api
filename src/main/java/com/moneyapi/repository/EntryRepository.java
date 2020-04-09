package com.moneyapi.repository;

import com.moneyapi.model.Entry;
import com.moneyapi.repository.entry.EntryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long>, EntryRepositoryQuery {
}
