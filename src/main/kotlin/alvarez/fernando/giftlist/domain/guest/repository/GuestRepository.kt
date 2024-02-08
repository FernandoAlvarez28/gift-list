package alvarez.fernando.giftlist.domain.guest.repository

import alvarez.fernando.giftlist.domain.guest.model.Guest
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface GuestRepository : JpaRepository<Guest, UUID> {
    fun existsByAccessCode(accessCode: String): Boolean

    fun findByAccessCode(accessCode: String): Optional<Guest>

    fun findAllByGiftListIdOrderByNameAsc(giftListId: UUID): List<Guest>
}
