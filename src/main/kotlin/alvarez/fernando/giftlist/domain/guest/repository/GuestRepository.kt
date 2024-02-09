package alvarez.fernando.giftlist.domain.guest.repository

import alvarez.fernando.giftlist.domain.guest.model.Guest
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface GuestRepository : JpaRepository<Guest, UUID> {
    fun findByGuestIdAndGiftListIdAndDeletedAtIsNull(
        guestId: UUID,
        giftListId: UUID,
    ): Optional<Guest>

    fun existsByAccessCode(accessCode: String): Boolean

    fun findByAccessCodeAndDeletedAtIsNull(accessCode: String): Optional<Guest>

    fun findAllByGiftListIdAndDeletedAtIsNullOrderByNameAsc(giftListId: UUID): List<Guest>
}
