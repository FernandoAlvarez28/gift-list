package alvarez.fernando.giftlist.domain.gift.dto

interface GiftRequest {
    val name: String
    val description: String?
    val requirement: String?
}
