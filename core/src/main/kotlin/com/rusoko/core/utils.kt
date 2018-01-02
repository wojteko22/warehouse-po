package com.rusoko.core

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.math.pow

fun <T> connect(statement: Transaction.() -> T): T {
    Database.connect("jdbc:mysql://localhost/warehouse", driver = "com.mysql.jdbc.Driver", user = "root")
    return transaction(statement)
}

fun <T : IntEntity> IntEntityClass<T>.random(): T {
    val quantity = count()
    val index = Random().nextInt(quantity) + 1
    return get(index)
}

val random = Random()

fun Random.nextNumber(length: Int): String {
    val bound = 10f.pow(length).toInt()
    return nextInt(bound).toString().padStart(4, '0')
}

fun SchemaUtils.new(table: Table) {
    drop(table)
    create(table)
}