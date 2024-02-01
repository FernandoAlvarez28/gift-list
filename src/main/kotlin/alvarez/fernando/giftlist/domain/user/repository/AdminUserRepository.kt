package alvarez.fernando.giftlist.domain.user.repository

import alvarez.fernando.giftlist.domain.user.model.AdminUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface AdminUserRepository : JpaRepository<AdminUser, UUID> {
    @Query("SELECT EXISTS(SELECT 1 FROM AdminUser)")
    fun existsAny(): Boolean

    fun findByEmail(email: String): Optional<AdminUser>
}
