package alvarez.fernando.giftlist.controller

import org.apache.commons.text.StringSubstitutor

class Urls {
    companion object {
        const val ROOT = "/"

        fun processParams(
            uri: String,
            vararg params: Pair<String, Any>,
        ): String {
            if (params.isEmpty()) {
                return uri
            }

            val paramMap = mutableMapOf<String, Any>()
            for (param in params) {
                paramMap[param.first] = param.second
            }

            return StringSubstitutor(paramMap, "{", "}").replace(uri)
        }
    }

    class Admin {
        companion object {
            const val ANT_MATCHER = "/admin/**"
            const val LOGIN = "/admin/login"
            const val LOGOUT = "/admin/logout"
            const val FIRST_ACCESS = "/admin/first-access"

            const val USERS = "/admin/users"

            const val MY_GIFT_LISTS = "/admin/my-gift-lists"
            const val MY_GIFT_LISTS_NEW = "/admin/my-gift-lists/new"
            const val MY_GIFT_LIST_DETAIL = "/admin/my-gift-lists/{giftListId}"
            const val MY_GIFT_LIST_DETAIL_NEW_GIFT = "/admin/my-gift-lists/{giftListId}/add-gift"

            const val DEFAULT_URL = MY_GIFT_LISTS
        }
    }

    class Guests {
        companion object {
            const val ANT_MATCHER = "/guests/**"
            const val LOGIN = "/guests"
        }
    }

    class Resources {
        companion object {
            const val FAVICON = "/favicon.ico"
            const val RESOURCES_ANT_MATCHER = "/resources/**"

            val PUBLIC =
                arrayOf(
                    FAVICON,
                    RESOURCES_ANT_MATCHER,
                )
        }
    }
}
