package alvarez.fernando.giftlist.domain.giftlist.service

import alvarez.fernando.giftlist.domain.giftlist.dto.GiftListRequest
import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.repository.GiftListRepository
import alvarez.fernando.giftlist.domain.user.dto.UserReference
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GiftListService(
    private val giftListRepository: GiftListRepository,
) {
    fun create(
        giftListRequest: GiftListRequest,
        user: UserReference,
    ): GiftList {
        return this.giftListRepository.save(GiftList(giftListRequest = giftListRequest, user = user))
    }

    fun findByIdAndUser(
        giftListId: UUID,
        user: UserReference,
    ) = this.giftListRepository.findOneByIdAndUser(giftListId = giftListId, userId = user.userId)

    fun findAllByUser(user: UserReference) = this.giftListRepository.findAllByUserId(userId = user.userId)

    fun addGift(giftList: GiftList) {
        giftList.addGift()
        this.giftListRepository.save(giftList)
    }

    fun addGuest(giftList: GiftList) {
        giftList.addGuest()
        this.giftListRepository.save(giftList)
    }
}
