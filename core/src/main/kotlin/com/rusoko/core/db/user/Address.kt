package com.rusoko.core.db.user

import com.rusoko.api.user.AddressDto
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Addresses : IntIdTable() {
    val town = varchar("town", 255)
    val postalCode = varchar("postalCode", 255)
    val street = varchar("street", 255)
    val houseNumber = varchar("houseNumber", 5)
    val apartmentNumber = varchar("apartmentNumber", 5).nullable()
}

class Address(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Address>(Addresses) {
        fun createFromDto(addressDto: AddressDto) =
                Address.new {
                    town = addressDto.town
                    postalCode = addressDto.postalCode
                    street = addressDto.street
                    houseNumber = addressDto.houseNumber
                    apartmentNumber = addressDto.apartmentNumber
                }
    }

    var town: String by Addresses.town
    var postalCode: String by Addresses.postalCode
    var street: String by Addresses.street
    var houseNumber: String by Addresses.houseNumber
    var apartmentNumber: String? by Addresses.apartmentNumber

    val users by User.optionalReferrersOn(Users.address)
}