package alvarez.fernando.giftlist.domain.user.repository

import alvarez.fernando.giftlist.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    @Query("SELECT EXISTS(SELECT 1 FROM User)")
    fun existsAny(): Boolean

    fun findByEmail(email: String): Optional<User>
}
