package alvarez.fernando.giftlist.domain.guest.util

class AccessCodeGenerator {
    companion object {
        private val charPool =
            ('a'..'z') + ('A'..'Z') + ('0'..'9') -
                arrayOf('a', 'e', 'i', 'o', 'u') -
                arrayOf('A', 'E', 'I', 'O', 'U') -
                arrayOf('4', '3', '1', '0')

        fun generateRandomAccessCode(size: Int = 12) = List(size) { charPool.random() }.joinToString("")
    }
}
