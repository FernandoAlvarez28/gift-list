package alvarez.fernando.giftlist.domain.gift.model

import alvarez.fernando.giftlist.domain.gift.dto.GiftEditRequest
import alvarez.fernando.giftlist.domain.gift.dto.GiftRequest
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.apache.commons.lang3.StringUtils
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
    var deletedAt: LocalDateTime?,
    // TODO save image somehow
) {
    constructor(giftRequest: GiftRequest, giftList: GiftList) : this(
        giftId = UUID.randomUUID(),
        giftListId = giftList.giftListId,
        name = giftRequest.name.trim(),
        description = StringUtils.trimToNull(giftRequest.description),
        requirement = StringUtils.trimToNull(giftRequest.requirement),
        createdAt = LocalDateTime.now(),
        deletedAt = null,
        promised = false,
    )

    fun edit(giftEditRequest: GiftEditRequest) {
        this.name = giftEditRequest.name.trim()
        this.description = StringUtils.trimToNull(giftEditRequest.description)
        this.requirement = StringUtils.trimToNull(giftEditRequest.requirement)
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }
}
