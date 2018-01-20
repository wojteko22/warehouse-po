package com.rusoko.core.db

import org.jetbrains.exposed.dao.IntIdTable

abstract class InitializableTable(name: String = "") : IntIdTable(name) {
    abstract fun init()
}