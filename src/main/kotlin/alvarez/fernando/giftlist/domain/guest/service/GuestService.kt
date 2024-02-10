package alvarez.fernando.giftlist.domain.guest.service

import alvarez.fernando.giftlist.domain.giftlist.model.GiftList
import alvarez.fernando.giftlist.domain.giftlist.service.GiftListService
import alvarez.fernando.giftlist.domain.guest.dto.GuestEditRequest
import alvarez.fernando.giftlist.domain.guest.dto.GuestRequest
import alvarez.fernando.giftlist.domain.guest.model.Guest
import alvarez.fernando.giftlist.domain.guest.repository.GuestRepository
import alvarez.fernando.giftlist.domain.guest.util.AccessCodeGenerator
import alvarez.fernando.giftlist.domain.guestgiftchoise.service.GuestGiftChoiceService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GuestService(
    private val guestRepository: GuestRepository,
    private val giftListService: GiftListService,
    private val guestGiftChoiceService: GuestGiftChoiceService,
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

    fun findByIdAndGiftList(
        guestId: UUID,
        giftListId: UUID,
    ) = this.guestRepository.findByGuestIdAndGiftListIdAndDeletedAtIsNull(
        guestId = guestId,
        giftListId = giftListId,
    )

    fun findByAccessCode(accessCode: String) = this.guestRepository.findByAccessCodeAndDeletedAtIsNull(accessCode)

    fun findAllByGiftList(giftListId: UUID) =
        this.guestRepository.findAllByGiftListIdAndDeletedAtIsNullOrderByNameAsc(giftListId = giftListId)

    fun edit(guest: Guest, guestEditRequest: GuestEditRequest) {
        guest.edit(guestEditRequest = guestEditRequest)
        this.guestRepository.save(guest)
    }

    fun delete(
        guest: Guest,
        giftList: GiftList,
    ) {
        guest.delete()
        this.guestRepository.save(guest)
        this.giftListService.removeGuest(giftList = giftList)
        this.guestGiftChoiceService.removeAllByGuest(guest = guest)
    }
}
