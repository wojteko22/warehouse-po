package com.rusoko.api

interface DataInitializer {
    /**
     * Initialize database with tables and example date
     */
    fun init()
}