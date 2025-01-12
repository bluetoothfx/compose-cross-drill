package com.blueprint.composecrossdrill.di

import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
}