package alvarez.fernando.giftlist.dto

import alvarez.fernando.giftlist.domain.guest.dto.GuestEditRequest

class GuestEditRequestDto(
    override val name: String,
) : GuestEditRequest
