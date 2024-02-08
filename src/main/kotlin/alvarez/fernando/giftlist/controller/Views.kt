package alvarez.fernando.giftlist.controller

class Views {
    class Admin {
        companion object {
            const val LOGIN = "admin/login/login"
            const val FIRST_ACCESS = "admin/login/first-access"

            const val USERS_LIST = "admin/users/list"

            const val GIFT_LISTS_LIST = "admin/giftlist/list"
            const val GIFT_LISTS_NEW = "admin/giftlist/new"
            const val GIFT_LIST_DETAIL = "admin/giftlist/detail"
        }
    }

    class Guests {
        companion object {
            const val GIFT_LIST = "guests/giftlist/detail"
        }

        class Fragments {
            companion object {
                const val GIFT_DETAIL_FRAGMENT = "guests/fragments/gift/detail"
            }
        }
    }
}
