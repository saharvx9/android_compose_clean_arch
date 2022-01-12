package com.sahar.composecleanarchitecture.data.source.user

import javax.inject.Inject

class UserRepository
@Inject constructor(
    private val local: UserDataSource.Local,
    private val remote: UserDataSource.Remote
) {

}