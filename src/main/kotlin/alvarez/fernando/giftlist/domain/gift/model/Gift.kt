package alvarez.fernando.giftlist.domain.gift.model

import alvarez.fernando.giftlist.domain.gift.dto.GiftRequest
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table
class Gift(
    @Id val giftId: UUID,
    val giftListId: UUID,
    var name: String,
    var description: String?,
    var requirement: String?,
    val createdAt: LocalDateTime,
    var promised: Boolean,
    // TODO save image somehow
) {
    constructor(giftRequest: GiftRequest, giftList: GiftList) : this(
        giftId = UUID.randomUUID(),
        giftListId = giftList.giftListId,
        name = giftRequest.name.trim(),
        description = giftRequest.description?.trim(),
        requirement = giftRequest.requirement?.trim(),
        createdAt = LocalDateTime.now(),
        promised = false,
    )
}
