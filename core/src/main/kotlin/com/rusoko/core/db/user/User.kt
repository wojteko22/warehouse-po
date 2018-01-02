package com.rusoko.core.db.user

import com.rusoko.api.dto.UserRegisterDto
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Table

object Users : IntIdTable() {
    val login = varchar("login", 255)
    val password = varchar("password", 15).nullable()
    val fstName = varchar("fstName", 255)
    val lastName = varchar("lastName", 255)
    val pesel = varchar("pesel", 11).nullable()
    val address = reference("address", Addresses).nullable()
}

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users) {
        fun createFromDto(userDto: UserRegisterDto): User =
            User.new {
                fstName = userDto.name
                lastName = userDto.surname
                email = userDto.email
                password = null
                pesel = null
                address = null
            }
    }

    var fstName by Users.fstName
    var lastName by Users.lastName
    var email by Users.login
    var password by Users.password
    var pesel by Users.pesel
    var address by Address optionalReferencedOn Users.address
    val permissions by Permission via UsersPermissions
}

object UsersPermissions : Table() {
    val userId = reference("userId", Users)
    val userPermission = reference("userPermission", Permissions)
}
