package alvarez.fernando.giftlist.domain.gift.model

import alvarez.fernando.giftlist.domain.gift.dto.GiftEditRequest
import alvarez.fernando.giftlist.domain.gift.dto.GiftRequest
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.image.model.Image
import jakarta.persistence.Column
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
    @Column(name = "chosen_by_a_guest") var chosenByAGuest: Boolean,
    var deletedAt: LocalDateTime?,
    var imageId: UUID?,
) {
    constructor(giftRequest: GiftRequest, giftList: GiftList, image: Image?) : this(
        giftId = UUID.randomUUID(),
        giftListId = giftList.giftListId,
        name = giftRequest.name.trim(),
        description = StringUtils.trimToNull(giftRequest.description),
        requirement = StringUtils.trimToNull(giftRequest.requirement),
        createdAt = LocalDateTime.now(),
        deletedAt = null,
        chosenByAGuest = false,
        imageId = image?.imageId,
    )

    fun edit(giftEditRequest: GiftEditRequest) {
        this.name = giftEditRequest.name.trim()
        this.description = StringUtils.trimToNull(giftEditRequest.description)
        this.requirement = StringUtils.trimToNull(giftEditRequest.requirement)
    }

    /**
     * @return The previous image's ID, if existent.
     */
    fun changeImage(newImage: Image): UUID? {
        val previousImageId = this.imageId
        this.imageId = newImage.imageId
        return previousImageId
    }

    fun removeImage() {
        this.imageId = null
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }
}
