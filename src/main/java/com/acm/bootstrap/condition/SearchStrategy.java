package com.acm.bootstrap.condition;

public enum SearchStrategy {

    /**
     * Search only the current context
     */
    CURRENT,

    /**
     * Search all parents and ancestors, but not the current context
     */
    PARENTS,

    /**
     * Search the entire hierarchy
     */
    ALL

}