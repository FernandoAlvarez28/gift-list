package alvarez.fernando.giftlist.domain.giftlist.model

import alvarez.fernando.giftlist.domain.giftlist.dto.GiftListRequest
import alvarez.fernando.giftlist.domain.user.dto.UserReference
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table
class GiftList(
    @Id val giftListId: UUID,
    val name: String,
    val userId: UUID,
    val createdAt: LocalDateTime,
    var active: Boolean,
    var giftQuantity: Int,
    var guestQuantity: Int,
) {
    constructor(giftListRequest: GiftListRequest, user: UserReference) : this(
        giftListId = UUID.randomUUID(),
        name = giftListRequest.name.trim(),
        userId = user.userId,
        createdAt = LocalDateTime.now(),
        active = true,
        giftQuantity = 0,
        guestQuantity = 0,
    )

    fun addGift() = ++this.giftQuantity

    fun addGuest() = ++this.guestQuantity
}
