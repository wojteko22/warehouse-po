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
        SchemaUtils.new(Providers)

        arrayOf("BIO-HURT", "Antel", "Paswex", "Biotrans", "Trantrix").forEach { aName ->
            Providers.insert {
                it[name] = aName
            }
        }
    }
}

class Provider(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Provider>(Providers)

    var name by Providers.name
}

object Providers : IntIdTable() {
    val name = varchar("name", 255)
}