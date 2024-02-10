package alvarez.fernando.giftlist.domain.guestgiftchoise.repository

import alvarez.fernando.giftlist.domain.guestgiftchoise.model.GuestGiftChoice
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface GuestGiftChoiceRepository : JpaRepository<GuestGiftChoice, UUID> {
    fun findAllByGuestId(guestId: UUID): List<GuestGiftChoice>

    fun findByGiftIdAndGuestId(
        giftId: UUID,
        guestId: UUID,
    ): Optional<GuestGiftChoice>

    fun existsByGiftId(giftId: UUID): Boolean
}
