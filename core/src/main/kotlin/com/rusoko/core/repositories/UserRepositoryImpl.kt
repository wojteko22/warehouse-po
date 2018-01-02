package com.rusoko.core.repositories

import com.rusoko.api.dto.AddressDto
import com.rusoko.api.dto.UserConfigurationDto
import com.rusoko.api.dto.UserRegisterDto
import com.rusoko.api.user.UserRepository
import com.rusoko.core.connect
import com.rusoko.core.db.user.*
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.insert
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl : UserRepository {

    override fun add(userDto: UserRegisterDto): Int =
            connect {
                val user = User.createFromDto(userDto)
                insertPermissions(user.id, userDto.permissions)
                return@connect user.id.value
            }

    private fun insertPermissions(user: EntityID<Int>, permissions: Set<String>) =
            permissions.forEach { permission ->
                UsersPermissions.insert {
                    it[userId] = user
                    it[userPermission] = Permission.readFromDto(permission).id
                }
            }

    override fun configure(userDto: UserConfigurationDto) =
            connect {
                val user = User.findById(userDto.id)
                user?.password = userDto.password
                user?.pesel = userDto.pesel
                user?.address = Address.createFromDto(userDto.address)
            }
}

// --------------------------------------------- INITIALIZE DB AND TEST ---------------------------------------------------------------------------
// --------------- to jest głupi test jak na razie i trzeba patrzeć (localhost/phpmyadmin) czy dodaje się prawidłowo w bazie danych :P -------------

fun main(args: Array<String>) {
    connect {
        drop(Users, Permissions, UsersPermissions, Addresses)
        create(Users, Permissions, UsersPermissions, Addresses)
        insertPermissions()
    }
    testInsertUser()
    testConfigureUser()
}

fun insertPermissions() {
    Permission.new { permissionName = "magazynier przyjmujacy" }
    Permission.new { permissionName = "magazynier wydajacy" }
    Permission.new { permissionName = "administrator" }
    Permission.new { permissionName = "snoorlak" }
}

fun testInsertUser() {
    val userRegisterDto = UserRegisterDto("Zdzislaw", "Poniedzielski",
            "lubieplacki@gmail.com", setOf("snoorlak", "magazynier wydajacy"))

    UserRepositoryImpl().add(userRegisterDto)
}

fun testConfigureUser() {
    val userDto = UserConfigurationDto(1, "99234598735", "okon",
            AddressDto("Lubola", "99-235", "ciemna", "5", null))
    UserRepositoryImpl().configure(userDto)
}
