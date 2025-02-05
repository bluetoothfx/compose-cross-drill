
# Jetpack Compose Cross Drill

A powerful and flexible Jetpack Compose app template designed to help kickstart Android projects with modern best practices. This template includes essential core features like MVVM architecture, Dependency Injection, Multilingual support, Theming, and more.

## Features

- Composeables – Fully built with Jetpack Compose for a modern UI experience.
- MVVM Architecture – Ensures clean code and better separation of concerns.
- Dependency Injection (Koin) – Simplifies dependency management.
- Multilingual Support – Easy integration of multiple languages.
- Dark/Light Theme – Built-in theming support.
- Navigation Component – Handles screen transitions efficiently.
- Network Layer (Retrofit) – Pre-configured API handling.

## Composables
Instead of relying on XML-based layouts, this template uses Jetpack Compose to define UI components as functions. This approach allows UI to be built dynamically and reactively, making it easier to manage state changes. Developers can structure the UI using reusable composables, which improves code readability and reduces the need for complex View hierarchies. This includes all the essentials composeables like Buttons, Texts, Dialog, Bottom Sheets etc.

## MVVM Architecture
The project follows the Model-View-ViewModel (MVVM) pattern, where the ViewModel handles UI-related data while the Model represents the data source (e.g., API or database). This separation ensures that UI logic remains independent of business logic, making it possible to modify one without affecting the other. The ViewModel also survives configuration changes like screen rotations, preventing unnecessary recomputation of data.

## Dependency Injection (Koin)
The template uses Koin to manage object dependencies without requiring manual instantiation. Instead of creating instances throughout the app, dependencies are declared in a single module, and Koin takes care of providing them wherever needed. This reduces the risk of memory leaks, avoids redundant object creation, and makes unit testing easier since dependencies can be mocked or replaced dynamically.

## Multilingual Support – Easy integration of multiple languages.
The app supports multiple languages using Android’s resources system. All user-facing text is stored in strings.xml, allowing translations to be added without modifying the core code. By detecting the user's device language settings, the app automatically switches to the appropriate translation. If a translation is unavailable, it falls back to the default language, ensuring a consistent experience.

## Dark/Light Theme – Built-in theming support.
The template includes a theme system that defines colors, typography, and shapes for both light and dark modes. UI elements dynamically adjust based on the system setting or user preference. Themes are structured in a way that allows developers to modify primary colors, surface colors, or even introduce custom theme variations without affecting the underlying UI components.

## Navigation Component
Instead of manually handling fragment transactions, this template uses Jetpack Navigation to manage navigation between screens. It provides type-safe argument passing, handles back navigation automatically, and supports deep linking for opening specific app sections from external sources. By defining navigation in a single place, it reduces boilerplate code and makes navigation flow easier to maintain.

## Network Layer (Retrofit)
The template includes a network module that sets up Retrofit for making API requests. A centralized service interface defines endpoints, and data classes are used to parse JSON responses automatically. The module also includes error-handling logic to detect issues like authentication failures, connectivity problems, or unexpected server responses, ensuring that network operations don’t disrupt the app’s user experience.

## Run Locally

Clone the project

```bash
  https://github.com/bluetoothfx/compose-cross-drill.git
```

Go to the project directory

```bash
  cd composeCrossDrill
```
Open the project in Android Studio IDE and sync the project.


## Authors

- [Syed Refat Al Abrar](https://github.com/bluetoothfx)
- [Sakhawat Hossain](https://github.com/shakiz)

