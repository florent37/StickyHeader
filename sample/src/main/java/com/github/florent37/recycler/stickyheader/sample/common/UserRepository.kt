package com.github.florent37.recycler.stickyheader.sample.common

import com.github.florent37.recycler.stickyheader.sample.common.model.User
import com.github.florent37.recycler.stickyheader.sample.common.model.UserHeader
import com.github.florent37.recycler.stickyheader.sample.common.model.UserModel

class UserRepository {
    fun provideUsers() : List<UserModel> {
        return listOf<UserModel>(
            UserHeader(title = "Familly"),
            User(name="Florent Champigny"),
            User(name="Théo Champigny"),
            User(name="Camille Champigny"),
            User(name="Flocon Champigny"),

            UserHeader(title = "Singers"),
            User(name="Francis Lallane"),
            User(name="Jennifer"),
            User(name="Florent Pagny"),
            User(name="Mika"),
            User(name="Jul"),

            UserHeader(title = "Old Singers"),
            User(name="Charles Aznavour"),
            User(name="Johnny Halliday"),
            User(name="Georges Brassens"),
            User(name="Jean Ferrat"),
            User(name="Alain Bashung"),
            User(name="Serge Gainsbourg"),
            User(name="Renaud"),
            User(name="Elvis Presley")
        )
    }
}