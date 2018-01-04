package com.rusoko.core.db.delivery

import com.rusoko.core.connect
import com.rusoko.core.new
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert

fun main(args: Array<String>) {
    connect {
        SchemaUtils.new(Measures)

        arrayOf("kg", "sztuki", "tony").forEach { aName ->
            Measures.insert {
                it[name] = aName
            }
        }
    }
}

class Measure(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Measure>(Measures)

    var name by Measures.name
}

object Measures : IntIdTable() {
    val name = varchar("name", 255).uniqueIndex()
}