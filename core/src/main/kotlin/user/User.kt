package user

import com.rusoko.api.user.Address

data class User(val name: String,
                val surname: String,
                val email: String,
                val password: String,
                val pesel: String,
                val address: Address,
                val permissions: Set<UserPermission>)