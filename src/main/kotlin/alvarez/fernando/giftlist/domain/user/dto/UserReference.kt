package alvarez.fernando.giftlist.domain.user.dto

import java.util.UUID

interface UserReference {
    val userId: UUID

    val name: String
}
