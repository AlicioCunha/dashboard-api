package com.moneyapi.repository.entry;

import com.moneyapi.model.Entry;
import com.moneyapi.model.QEntry;
import com.moneyapi.repository.filter.EntryFilter;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Entry> filter(EntryFilter entryFilter) {

        QEntry qEntry = QEntry.entry;

        JPAQuery query = new JPAQueryFactory(entityManager).from(qEntry);

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
