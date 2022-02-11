package com.ntr.api;

public enum Index{
    BRANCH("branch");

    private final String indexName;

    Index(String indexName){
        this.indexName = indexName;
    }

    public String getIndexName() {
        return this.indexName;
    }
}
