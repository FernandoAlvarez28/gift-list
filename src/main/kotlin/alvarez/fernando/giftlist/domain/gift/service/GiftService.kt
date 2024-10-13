package alvarez.fernando.giftlist.domain.gift.service

import alvarez.fernando.giftlist.domain.gift.dto.GiftEditRequest
import alvarez.fernando.giftlist.domain.gift.dto.GiftRequest
import alvarez.fernando.giftlist.domain.gift.model.Gift
import alvarez.fernando.giftlist.domain.gift.repository.GiftRepository
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.image.model.Image
import alvarez.fernando.giftlist.domain.image.service.ImageService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GiftService(
    private val giftRepository: GiftRepository,
    private val giftListService: GiftListService,
    private val imageService: ImageService,
) {
    fun create(
        giftRequest: GiftRequest,
        giftList: GiftList,
    ): Gift {
        var image: Image? = null
        if (giftRequest.imageFile != null && giftRequest.imageFile?.isEmpty != true) {
            image = this.imageService.uploadImage(file = giftRequest.imageFile!!)
        }

        val gift =
            this.giftRepository.save(
                Gift(
                    giftRequest = giftRequest,
                    giftList = giftList,
                    image = image,
                ),
            )

        this.giftListService.addGift(giftList)

        return gift
    }

    fun findAllByGiftList(giftListId: UUID) =
        this.giftRepository.findAllByGiftListIdAndDeletedAtNullOrderByNameAsc(giftListId = giftListId)

    fun findByIdAndGiftListId(
        giftId: UUID,
        giftListId: UUID,
    ) = this.giftRepository.findByGiftIdAndGiftListIdAndDeletedAtNull(
        giftId = giftId,
        giftListId = giftListId,
    )

    fun edit(
        gift: Gift,
        giftEditRequest: GiftEditRequest,
    ) {
        var previousImageId: UUID? = null
        if (giftEditRequest.imageFile != null && giftEditRequest.imageFile?.isEmpty != true) {
            val newImage: Image? = this.imageService.uploadImage(file = giftEditRequest.imageFile!!)
            if (newImage != null) {
                previousImageId = gift.changeImage(newImage = newImage)
            }
        }

        gift.edit(giftEditRequest = giftEditRequest)
        this.giftRepository.save(gift)

        if (previousImageId != null) {
            this.imageService.deleteImage(imageId = previousImageId)
        }
    }

    fun markAsChosen(
        gift: Gift,
        chosen: Boolean,
    ) {
        gift.chosenByAGuest = chosen
        this.giftRepository.save(gift)
    }
}
