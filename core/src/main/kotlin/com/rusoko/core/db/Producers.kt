package com.rusoko.core.db

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
        SchemaUtils.new(Producers)

        arrayOf("Flaki z olejem", "De Heus", "Paswex").forEach { aName ->
            Producers.insert {
                it[name] = aName
            }
        }
    }
}

class Producer(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Producer>(Producers)

    var name by Producers.name
}


object Producers : IntIdTable() {
    val name = varchar("name", 255)
}