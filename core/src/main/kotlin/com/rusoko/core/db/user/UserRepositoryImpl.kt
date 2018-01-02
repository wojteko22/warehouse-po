package com.rusoko.core.db.user

import com.rusoko.api.user.UserRegisterDto
import com.rusoko.api.user.UserRepository
import com.rusoko.core.db.connect
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.insert
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl : UserRepository {
    override fun add(userDto: UserRegisterDto) {
        connect {
            val user = User.createFromDto(userDto)
            insertPermissions(user.id, userDto.permissions)
        }
    }

    private fun insertPermissions(user: EntityID<Int>, permissions: Set<String>) =
            permissions.forEach { permission ->
                UsersPermissions.insert {
                    it[userId] = user
                    it[userPermission] = Permission.readFromDto(permission).id
                }
            }
}

// --------------------------------------------- INITIALIZE DB AND TEST ---------------------------------------------------------------------------
// --------------- to jest głupi test jak narazie i trzeba patrzeć (localhost/phpmyadmin) czy dodaje się prawidłowo w bazie danych :P -------------

fun main(args: Array<String>) {
    connect {
        drop(Users, Permissions, UsersPermissions, Addresses)
        create(Users, Permissions, UsersPermissions, Addresses)
        insertPermissions()
    }
    testInsertUser()
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
