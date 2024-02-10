package alvarez.fernando.giftlist.domain.gift.service

import alvarez.fernando.giftlist.domain.gift.dto.GiftEditRequest
import alvarez.fernando.giftlist.domain.gift.dto.GiftRequest
import alvarez.fernando.giftlist.domain.gift.model.Gift
import alvarez.fernando.giftlist.domain.gift.repository.GiftRepository
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GiftService(
    private val giftRepository: GiftRepository,
    private val giftListService: GiftListService,
) {
    fun create(
        giftRequest: GiftRequest,
        giftList: GiftList,
    ): Gift {
        val gift =
            this.giftRepository.save(
                Gift(
                    giftRequest = giftRequest,
                    giftList = giftList,
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
        gift.edit(giftEditRequest = giftEditRequest)
        this.giftRepository.save(gift)
    }

    fun markAsChosen(
        gift: Gift,
        chosen: Boolean,
    ) {
        gift.chosenByAGuest = chosen
        this.giftRepository.save(gift)
    }
}
