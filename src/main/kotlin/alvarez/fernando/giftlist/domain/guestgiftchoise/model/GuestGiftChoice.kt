package alvarez.fernando.giftlist.domain.guestgiftchoise.model

import alvarez.fernando.giftlist.domain.gift.model.Gift
import alvarez.fernando.giftlist.domain.guest.dto.GuestReference
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table
class GuestGiftChoice(
    @Id val guestGiftChoiceId: UUID,
    val giftId: UUID,
    val guestId: UUID,
    val giftListId: UUID,
    val chosenAt: LocalDateTime,
) {
    constructor(
        gift: Gift,
        guest: GuestReference,
    ) : this(
        guestGiftChoiceId = UUID.randomUUID(),
        giftId = gift.giftId,
        guestId = guest.guestId,
        giftListId = gift.giftListId,
        chosenAt = LocalDateTime.now(),
    )

    companion object {
        fun mapByGiftId(choices: Collection<GuestGiftChoice>) = choices.associateBy { it.giftId }

        fun mapGiftIds(choices: Collection<GuestGiftChoice>) = choices.map { it.giftId }.toHashSet()
    }
}
