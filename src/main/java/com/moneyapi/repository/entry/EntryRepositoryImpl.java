package com.moneyapi.repository.entry;

import com.moneyapi.model.Entry;
import com.moneyapi.model.QEntry;
import com.moneyapi.repository.common.RepositoryBaseBean;
import com.moneyapi.repository.filter.EntryFilter;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.util.StringUtils;

import java.util.List;

public class EntryRepositoryImpl extends RepositoryBaseBean implements EntryRepositoryQuery {

    @Override
    public List<Entry> filter(EntryFilter entryFilter) {

        QEntry qEntry = QEntry.entry;

        JPAQuery query = select(qEntry).from(qEntry);

        if (!StringUtils.isEmpty(entryFilter.getDescription())) {
            query.where(qEntry.description.containsIgnoreCase(entryFilter.getDescription()));
        }

        if (!StringUtils.isEmpty(entryFilter.getValueDateIn())) {
            query.where(qEntry.valueDate.goe(entryFilter.getValueDateIn()));
        }

        if (!StringUtils.isEmpty(entryFilter.getValueDateUntil())) {
            query.where(qEntry.valueDate.loe(entryFilter.getValueDateUntil()));
        }

        return  query.fetch();
    }

}
