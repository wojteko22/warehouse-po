package com.rusoko.core.db.delivery

import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert

fun main(args: Array<String>) {
    connect {
        Measures.init()
    }
}

class Measure(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Measure>(Measures)

    var name by Measures.name
}

object Measures : InitializableTable() {
    val name = varchar("name", 255).uniqueIndex()

    override fun init() {
        SchemaUtils.new(this)

        arrayOf("kg", "sztuki", "tony").forEach { aName ->
            insert {
                it[name] = aName
            }
        }
    }
}