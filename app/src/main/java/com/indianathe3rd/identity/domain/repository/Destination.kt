package com.indianathe3rd.identity.domain.repository

interface Destination {
    val route: String
    val title: String
}

object SignUpDestination: Destination {
    override val route = "signup"
    override val title = "Sign Up"
}

object AuthenticationDestination: Destination{
    override val route = "authentication"
    override val title = "Authentication"
}