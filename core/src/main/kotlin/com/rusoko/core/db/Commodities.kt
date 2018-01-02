package com.rusoko.core.db

import com.rusoko.api.dto.CommodityDto
import com.rusoko.core.connect
import com.rusoko.core.new
import com.rusoko.core.nextNumber
import com.rusoko.core.random
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert

fun main(args: Array<String>) { // Needs Providers
    connect {
        SchemaUtils.new(Commodities)

        arrayOf("Bestermine Activ", "Bestermine Plus", "Bestermine Ultra", "Kaliber Musli", "Optimilk", "Protimilk",
                "SuperMusli").forEach { aName ->
            Commodities.insert {
                it[code] = "T-" + random.nextNumber(4)
                it[name] = aName
                it[measure] = Measure.random().id
            }
        }
    }
}

class Commodity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Commodity>(Commodities)

    private var code by Commodities.code
    private var name by Commodities.name
    private var measure by Measure referencedOn Commodities.measure

    fun toDto() = CommodityDto(code, name, measure.name)
}

object Commodities : IntIdTable() {
    val code = varchar("code", 255).uniqueIndex()
    val name = varchar("name", 255)
    val measure = reference("measure", Measures)
}