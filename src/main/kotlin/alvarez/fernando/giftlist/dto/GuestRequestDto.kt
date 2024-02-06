package alvarez.fernando.giftlist.dto

import alvarez.fernando.giftlist.domain.guest.dto.GuestRequest

class GuestRequestDto(
    override val name: String,
) : GuestRequest
