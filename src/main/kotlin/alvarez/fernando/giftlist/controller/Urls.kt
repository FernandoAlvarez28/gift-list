package alvarez.fernando.giftlist.controller

class Urls {
    class Admin {
        companion object {
            const val ANT_MATCHER = "/admin/**"
            const val LOGIN = "/admin/login"
        }
    }

    class Guests {
        companion object {
            const val ANT_MATCHER = "/guests/**"
            const val LOGIN = "/guests"

            const val GIFTS = "/guest/gifts"
        }
    }
}
