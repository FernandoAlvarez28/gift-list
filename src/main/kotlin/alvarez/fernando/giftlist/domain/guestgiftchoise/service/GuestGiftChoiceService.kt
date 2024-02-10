package alvarez.fernando.giftlist.domain.guestgiftchoise.service

import alvarez.fernando.giftlist.domain.gift.model.Gift
import alvarez.fernando.giftlist.domain.gift.service.GiftService
import alvarez.fernando.giftlist.domain.guest.dto.GuestReference
import alvarez.fernando.giftlist.domain.guestgiftchoise.model.GuestGiftChoice
import alvarez.fernando.giftlist.domain.guestgiftchoise.repository.GuestGiftChoiceRepository
import org.springframework.stereotype.Service

@Service
class GuestGiftChoiceService(
    private val guestGiftChoiceRepository: GuestGiftChoiceRepository,
    private val giftService: GiftService,
) {
    fun toggleChoice(
        guest: GuestReference,
        gift: Gift,
    ) {
        val guestGiftChoiceOptional =
            this.guestGiftChoiceRepository.findByGiftIdAndGuestId(
                guestId = guest.guestId,
                giftId = gift.giftId,
            )

        if (guestGiftChoiceOptional.isPresent) {
            this.removeChoice(guestGiftChoice = guestGiftChoiceOptional.get(), gift = gift)
        } else {
            this.choose(guest = guest, gift = gift)
        }
    }

    private fun choose(
        guest: GuestReference,
        gift: Gift,
    ) {
        this.guestGiftChoiceRepository.save(
            GuestGiftChoice(
                gift = gift,
                guest = guest,
            ),
        )

        this.giftService.markAsChosen(gift = gift, chosen = true)
    }

    private fun removeChoice(
        guestGiftChoice: GuestGiftChoice,
        gift: Gift,
    ) {
        this.guestGiftChoiceRepository.delete(guestGiftChoice)

        if (!this.guestGiftChoiceRepository.existsByGiftId(giftId = gift.giftId)) {
            this.giftService.markAsChosen(gift = gift, chosen = false)
        }
    }

    fun findAllByGuest(guest: GuestReference) = this.guestGiftChoiceRepository.findAllByGuestId(guestId = guest.guestId)
}
