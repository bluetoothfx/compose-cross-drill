package com.blueprint.composecrossdrill.di

import com.blueprint.composecrossdrill.data.repository.LoginRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory {  LoginRepositoryImpl(get()) }
}