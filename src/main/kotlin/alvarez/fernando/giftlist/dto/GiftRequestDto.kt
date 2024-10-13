package alvarez.fernando.giftlist.dto

import alvarez.fernando.giftlist.domain.gift.dto.GiftRequest
import org.springframework.web.multipart.MultipartFile

class GiftRequestDto(
    override val name: String,
    override val description: String?,
    override val requirement: String?,
    override val imageFile: MultipartFile?,
) : GiftRequest
