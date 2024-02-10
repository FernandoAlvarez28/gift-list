package alvarez.fernando.giftlist.domain.gift.service

import alvarez.fernando.giftlist.domain.gift.model.Gift
import alvarez.fernando.giftlist.domain.gift.repository.GiftRepository
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.guestgiftchoise.service.GuestGiftChoiceService
import org.springframework.stereotype.Service

@Service
class GiftDeleteService(
    private val giftRepository: GiftRepository,
    private val giftListService: GiftListService,
    private val guestGiftChoiceService: GuestGiftChoiceService,
) {
    fun delete(
        gift: Gift,
        giftList: GiftList,
    ) {
        gift.delete()
        this.giftRepository.save(gift)
        this.giftListService.removeGift(giftList = giftList)
        this.guestGiftChoiceService.removeAllByGift(gift = gift)
    }
}
