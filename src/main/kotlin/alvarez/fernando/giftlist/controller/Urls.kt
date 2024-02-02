package alvarez.fernando.giftlist.controller

class Urls {
    class Admin {
        companion object {
            const val ANT_MATCHER = "/admin/**"
            const val LOGIN = "/admin/login"
            const val FIRST_ACCESS = "/admin/first-access"

            const val USERS = "/admin/users"
        }
    }

    class Guests {
        companion object {
            const val ANT_MATCHER = "/guests/**"
            const val LOGIN = "/guests"

            const val GIFTS = "/guest/gifts"
        }
    }

    class Resources {
        companion object {
            const val FAVICON = "/favicon.ico"

            val PUBLIC =
                arrayOf(
                    FAVICON,
                )
        }
    }
}
