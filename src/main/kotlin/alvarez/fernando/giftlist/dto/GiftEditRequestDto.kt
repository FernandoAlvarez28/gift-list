package alvarez.fernando.giftlist.dto

import alvarez.fernando.giftlist.domain.gift.dto.GiftEditRequest

class GiftEditRequestDto(
    override val name: String,
    override val description: String?,
    override val requirement: String?,
) : GiftEditRequest
