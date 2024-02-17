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

        class Fragments {
            companion object {
                const val GIFT_LIST_EDIT_FRAGMENT = "admin/fragments/giftlist/edit"
                const val GIFT_DELETE_FRAGMENT = "admin/fragments/gift/delete"
                const val GIFT_EDIT_FRAGMENT = "admin/fragments/gift/edit"
                const val GUEST_DELETE_FRAGMENT = "admin/fragments/guest/delete"
                const val GUEST_EDIT_FRAGMENT = "admin/fragments/guest/edit"
                const val GUEST_ACCESS_CODE_FRAGMENT = "admin/fragments/guest/access-code"
            }
        }
    }

    class Guests {
        companion object {
            const val GIFT_LIST = "guests/giftlist/detail"
        }

        class Fragments {
            companion object {
            }
        }
    }
}
