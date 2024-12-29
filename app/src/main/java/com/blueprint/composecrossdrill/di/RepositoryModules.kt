package com.blueprint.composecrossdrill.di

import com.blueprint.composecrossdrill.data.repository.LoginRepositoryImpl
import com.blueprint.composecrossdrill.domain.repository.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<LoginRepository> {  LoginRepositoryImpl(get()) }
}