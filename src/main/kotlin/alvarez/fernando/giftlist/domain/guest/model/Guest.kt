package alvarez.fernando.giftlist.domain.guest.model

import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.guest.dto.GuestRequest
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table
class Guest(
    @Id val guestId: UUID,
    val giftListId: UUID,
    var name: String,
    val accessCode: String,
    val createdAt: LocalDateTime,
) {
    constructor(
        guestRequest: GuestRequest,
        giftList: GiftList,
        accessCode: String,
    ) : this(
        guestId = UUID.randomUUID(),
        giftListId = giftList.giftListId,
        name = guestRequest.name,
        createdAt = LocalDateTime.now(),
        accessCode = accessCode,
    )
}
