package com.moneyapi.repository.entry;

import com.moneyapi.model.Entry;
import com.moneyapi.repository.filter.EntryFilter;

import java.util.List;

public class EntryRepositoryImpl implements EntryRepositoryQuery {
    @Override
    public List<Entry> filter(EntryFilter entryFilter) {
        return null;
    }
}
