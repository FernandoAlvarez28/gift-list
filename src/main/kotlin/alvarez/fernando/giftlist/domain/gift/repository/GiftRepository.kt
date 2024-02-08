package alvarez.fernando.giftlist.domain.gift.repository

import alvarez.fernando.giftlist.domain.gift.model.Gift
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface GiftRepository : JpaRepository<Gift, UUID> {
    fun findAllByGiftListIdOrderByNameAsc(giftListId: UUID): List<Gift>

    fun findByGiftIdAndGiftListId(
        giftId: UUID,
        giftListId: UUID,
    ): Optional<Gift>
}
