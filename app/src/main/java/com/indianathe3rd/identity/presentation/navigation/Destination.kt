package com.indianathe3rd.identity.presentation.navigation

interface Destination {
    val route: String
    val title: String
}

object SignUpDestination : Destination {
    override val route = "signup"
    override val title = "Sign Up"
}

object AuthenticationDestination : Destination {
    override val route = "authentication"
    override val title = "Authentication"
}

object RootDestination : Destination {
    override val route = "main"
    override val title = "Main"
}

object ForgotPassword : Destination {
    override val route = "forgotPassword"
    override val title = "ForgotPassword"
}

object SendMail: Destination{
    override val route = "sendForgotEmail"
    override val title = "SendForgotEmail"
}