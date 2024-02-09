package alvarez.fernando.giftlist.domain.gift.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class GiftNotFoundException : Exception()
