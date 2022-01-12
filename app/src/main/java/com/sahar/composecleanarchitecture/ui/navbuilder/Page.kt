package com.sahar.composecleanarchitecture.ui.navbuilder

sealed class Page(
    val route: String,
    val arg: String? = null,
    var removeAllStack: Boolean = false
) {
    companion object {
        private const val SPLASH_PAGE = "SPLASH_PAGE"
        private const val PAGE_A = "PAGE_A"
        private const val PAGE_B = "PAGE_B"
        private const val ARTICLES_LIST_PAGE = "ARTICLES_LIST_PAGE"
        private const val LOGIN_PAGE = "LOGIN_SCREEN"
    }

    val destination get() = arg?.let { "$route/$it" } ?: run { route }

    object SplashPage : Page(SPLASH_PAGE)
    object PageA : Page(PAGE_A)
    class PageB(arg: String? = "") : Page(PAGE_B, arg = arg)
    object ArticlesListPage : Page(ARTICLES_LIST_PAGE)
    object LoginPage : Page(LOGIN_PAGE)
}