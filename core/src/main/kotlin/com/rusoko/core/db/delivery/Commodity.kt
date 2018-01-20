package com.rusoko.core.db.delivery

import com.rusoko.api.dto.CommodityDto
import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import com.rusoko.core.nextNumber
import com.rusoko.core.random
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert

fun main(args: Array<String>) {
    connect {
        Commodities.init()
    }
}

class Commodity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Commodity>(Commodities)

    private var code by Commodities.code
    private var name by Commodities.name
    private var measure by Measure referencedOn Commodities.measure
    private var producer by Producer referencedOn Commodities.producer

    fun toDto() = CommodityDto(code, name, measure.name, producer.name)
}

object Commodities : InitializableTable() {
    val code = varchar("code", 255).uniqueIndex()
    val name = varchar("name", 255)
    val measure = reference("measure", Measures)
    val producer = reference("producer", Producers)

    override fun init() {
        SchemaUtils.new(this)

        arrayOf("Bestermine Activ", "Bestermine Plus", "Bestermine Ultra", "Kaliber Musli", "Optimilk", "Protimilk",
                "SuperMusli").forEach { aName ->
            insert {
                it[code] = "T-" + random.nextNumber(4)
                it[name] = aName
                it[measure] = Measure.random().id
                it[producer] = Producer.random().id
            }
        }
    }
}