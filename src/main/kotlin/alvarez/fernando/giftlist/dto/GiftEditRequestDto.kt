package alvarez.fernando.giftlist.dto

import alvarez.fernando.giftlist.domain.gift.dto.GiftEditRequest
import org.springframework.web.multipart.MultipartFile

class GiftEditRequestDto(
    override val name: String,
    override val description: String?,
    override val requirement: String?,
    override val imageFile: MultipartFile?,
) : GiftEditRequest
