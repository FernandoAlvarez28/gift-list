package alvarez.fernando.giftlist.domain.guest.service

import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.guest.dto.GuestRequest
import alvarez.fernando.giftlist.domain.guest.model.Guest
import alvarez.fernando.giftlist.domain.guest.repository.GuestRepository
import alvarez.fernando.giftlist.domain.guest.util.AccessCodeGenerator
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GuestService(
    private val guestRepository: GuestRepository,
    private val giftListService: GiftListService,
) {
    fun create(
        guestRequest: GuestRequest,
        giftList: GiftList,
    ): Guest {
        val accessCode: String = generateUniqueAccessCode()

        val guest =
            this.guestRepository.save(
                Guest(
                    guestRequest = guestRequest,
                    giftList = giftList,
                    accessCode = accessCode,
                ),
            )

        this.giftListService.addGuest(giftList)

        return guest
    }

    private fun generateUniqueAccessCode(): String {
        var accessCode: String
        do {
            accessCode = AccessCodeGenerator.generateRandomAccessCode()
        } while (this.guestRepository.existsByAccessCode(accessCode))
        return accessCode
    }

    fun findAllByGiftList(giftListId: UUID) =
        this.guestRepository.findAllByGiftListIdOrderByNameAsc(giftListId = giftListId)
}
