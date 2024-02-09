package alvarez.fernando.giftlist.domain.guest.model

import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.guest.dto.GuestEditRequest
import alvarez.fernando.giftlist.domain.guest.dto.GuestReference
import alvarez.fernando.giftlist.domain.guest.dto.GuestRequest
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table
class Guest(
    @Id override val guestId: UUID,
    override val giftListId: UUID,
    override var name: String,
    override val accessCode: String,
    val createdAt: LocalDateTime,
    var deletedAt: LocalDateTime?,
) : GuestReference {
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
        deletedAt = null,
    )

    fun edit(guestEditRequest: GuestEditRequest) {
        this.name = guestEditRequest.name.trim()
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }
}
