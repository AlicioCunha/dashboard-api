package com.moneyapi.repository.entry;

import com.moneyapi.model.Entry;
import com.moneyapi.repository.filter.EntryFilter;

import java.util.List;

public interface EntryRepositoryQuery {

    public List<Entry> filter(EntryFilter entryFilter);
}
