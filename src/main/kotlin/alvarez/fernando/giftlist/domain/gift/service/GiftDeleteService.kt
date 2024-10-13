package alvarez.fernando.giftlist.domain.gift.service

import alvarez.fernando.giftlist.domain.gift.model.Gift
import alvarez.fernando.giftlist.domain.gift.repository.GiftRepository
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.guestgiftchoise.service.GuestGiftChoiceService
import alvarez.fernando.giftlist.domain.image.service.ImageService
import org.springframework.stereotype.Service

@Service
class GiftDeleteService(
    private val giftRepository: GiftRepository,
    private val giftListService: GiftListService,
    private val guestGiftChoiceService: GuestGiftChoiceService,
    private val imageService: ImageService,
) {
    fun delete(
        gift: Gift,
        giftList: GiftList,
    ) {
        gift.delete()
        this.giftRepository.save(gift)
        this.giftListService.removeGift(giftList = giftList)
        this.guestGiftChoiceService.removeAllByGift(gift = gift)

        if (gift.imageId != null) {
            this.deleteImage(gift = gift)
        }
    }

    fun deleteImage(gift: Gift) {
        gift.removeImage()
        this.giftRepository.save(gift)
        this.imageService.deleteImage(imageId = gift.imageId)
    }
}
