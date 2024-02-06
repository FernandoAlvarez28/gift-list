package alvarez.fernando.giftlist.dto

import alvarez.fernando.giftlist.domain.gift.dto.GiftRequest

class GiftRequestDto(
    override val name: String,
    override val description: String?,
    override val requirement: String?,
) : GiftRequest
