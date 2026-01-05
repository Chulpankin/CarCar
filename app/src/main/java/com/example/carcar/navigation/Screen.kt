package com.example.carcar.navigation

import com.example.common.utils.Keys

sealed class Screen(val route: String) {
    object SignIn : Screen("sign_in")
    object SignUp : Screen("sign_up")
    
    object CarList : Screen("car_list") {
        fun createRoute(
            make: String? = null,
            model: String? = null,
            year: Int? = null,
            body: String? = null,
            keyword: String? = null
        ): String {
            val params = mutableListOf<String>()
            make?.takeIf { it.isNotEmpty() }?.let { params.add("${Keys.CAR_SEARCH_MAKE_KEY}=$it") }
            model?.takeIf { it.isNotEmpty() }?.let { params.add("${Keys.CAR_SEARCH_MODEL_KEY}=$it") }
            year?.takeIf { it > 0 }?.let { params.add("${Keys.CAR_SEARCH_YEAR_KEY}=$it") }
            body?.takeIf { it.isNotEmpty() }?.let { params.add("${Keys.CAR_SEARCH_BODY_KEY}=$it") }
            keyword?.takeIf { it.isNotEmpty() }?.let { params.add("${Keys.CAR_SEARCH_KEYWORD_KEY}=$it") }
            return if (params.isEmpty()) route else "$route?${params.joinToString("&")}"
        }
    }
    
    object CarSearch : Screen("car_search")
    
    object Favorites : Screen("favorites")
    
    object Profile : Screen("profile?username={username}") {
        fun createRoute(username: String) = "profile?username=$username"
    }
    
    object PostDetails : Screen("post_details?postId={postId}") {
        fun createRoute(postId: String) = "post_details?postId=$postId"
    }
    
    object CommentReplies : Screen("comment_replies?parentCommentId={parentCommentId}") {
        fun createRoute(parentCommentId: String) = "comment_replies?parentCommentId=$parentCommentId"
    }
    
    object SavePost : Screen("save_post")
}

