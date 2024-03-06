package alvarez.fernando.giftlist.controller

import org.apache.commons.text.StringSubstitutor

class Urls {
    companion object {
        const val ROOT = "/"
        const val ERROR = "/error"

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
            const val MY_GIFT_LIST_EDIT = "/admin/my-gift-lists/{giftListId}/edit"
            const val MY_GIFT_LIST_DETAIL_NEW_GIFT = "/admin/my-gift-lists/{giftListId}/add-gift"
            const val MY_GIFT_LIST_DETAIL_GIFT_DELETE = "/admin/my-gift-lists/{giftListId}/gifts/{giftId}/delete"
            const val MY_GIFT_LIST_DETAIL_GIFT_EDIT = "/admin/my-gift-lists/{giftListId}/gifts/{giftId}/edit"
            const val MY_GIFT_LIST_DETAIL_NEW_GUEST = "/admin/my-gift-lists/{giftListId}/add-guest"
            const val MY_GIFT_LIST_DETAIL_GUEST_DELETE = "/admin/my-gift-lists/{giftListId}/guests/{guestId}/delete"
            const val MY_GIFT_LIST_DETAIL_GUEST_EDIT = "/admin/my-gift-lists/{giftListId}/guests/{guestId}/edit"
            const val MY_GIFT_LIST_DETAIL_GUEST_ACCESS_CODE =
                "/admin/my-gift-lists/{giftListId}/guests/{guestId}/access-code"

            const val DEFAULT_URL = MY_GIFT_LISTS
        }

        class Fragments {
            companion object {
                const val MY_GIFT_LIST_EDIT_FRAGMENT =
                    "/admin/my-gift-lists/{giftListId}/edit/fragment"
                const val MY_GIFT_LIST_DETAIL_GIFT_DELETE_FRAGMENT =
                    "/admin/my-gift-lists/{giftListId}/gifts/{giftId}/delete/fragment"
                const val MY_GIFT_LIST_DETAIL_GIFT_EDIT_FRAGMENT =
                    "/admin/my-gift-lists/{giftListId}/gifts/{giftId}/edit/fragment"
                const val MY_GIFT_LIST_DETAIL_GUEST_DELETE_FRAGMENT =
                    "/admin/my-gift-lists/{giftListId}/guests/{guestId}/delete/fragment"
                const val MY_GIFT_LIST_DETAIL_GUEST_EDIT_FRAGMENT =
                    "/admin/my-gift-lists/{giftListId}/guests/{guestId}/edit/fragment"
                const val MY_GIFT_LIST_DETAIL_GUEST_ACCESS_CODE_FRAGMENT =
                    "/admin/my-gift-lists/{giftListId}/guests/{guestId}/access-code/fragment"
            }
        }
    }

    class Guests {
        companion object {
            const val ANT_MATCHER = "/guests/**"

            const val GIFT_LIST = "/guests/{guestAccessCode}"
            const val GIFT_TOGGLE_CHOICE = "/guests/{guestAccessCode}/gifts/{giftId}/choice"
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
