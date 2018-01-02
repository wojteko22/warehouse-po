package com.rusoko.core.db.user

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Permissions : IntIdTable() {
    val permissionName = varchar("permissionName", 255)
}

class Permission(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Permission>(Permissions) {
        fun readFromDto(permissionsDto: String): Permission =
                Permission.find { Permissions.permissionName eq permissionsDto }.first()
    }

    var permissionName by Permissions.permissionName
}