package edu.miu.mdp_layout

import java.io.Serializable

data class UserAccount(var username: String?, var password: String?, var fname: String?, var lname: String?) :
    Serializable
