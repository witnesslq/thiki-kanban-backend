package org.thiki.kanban.entry;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * Created by xubitao on 04/26/16.
 */
@Service
public class EntryService {
    @Resource
    private EntriesPersistence entriesPersistence;
    private List<Entry> entries;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public Entry create(Integer reporterUserId, final Entry entry) {
        entry.setReporter(reporterUserId);
        entriesPersistence.create(entry);
        return entry;
    }

    public Entry findById(Integer id) {
        return entriesPersistence.findById(id);
    }

    public EntryService loadAll() {
        EntryService entries = new EntryService();
        entries.setEntries(entriesPersistence.loadAll());
        return entries;
    }

    public Entry update(Entry entry) {
        entriesPersistence.update(entry);
        return entriesPersistence.findById(entry.getId());
    }

    public int deleteById(Integer id) {
        return entriesPersistence.deleteById(id);
    }
}