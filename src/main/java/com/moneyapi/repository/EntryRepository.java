package com.moneyapi.repository;

import com.moneyapi.model.Entry;
import com.moneyapi.repository.common.RepositoryBase;
import com.moneyapi.repository.entry.EntryRepositoryQuery;

//public interface EntryRepository extends PagingAndSortingRepository<Entry, Long>, EntryRepositoryQuery {
public interface EntryRepository extends RepositoryBase<Entry>, EntryRepositoryQuery {
}
