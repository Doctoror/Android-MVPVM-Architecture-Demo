# Android Architecture Demos

Splittor app is an architecture demo, used as a take home project for one of my previous interviews.

The application lets you create a bill and share it with your contacts, everyone pays an equal amount.

The group item is crossed out with strikethrough text once everyone has paid.

<img src="/screenshots/Empty.png" width="320" /> <img src="/screenshots/Wizard.png" width="320" />

<img src="/screenshots/Details.png" width="320" /> <img src="/screenshots/Overview.png" width="320" /> 

## Architecture

This application uses so called "Clean Architecture" with an `MVVMP` (Model-View-ViewModel-Presenter) pattern.
`Koin` is used for Dependency Injection.

The idea behind `MVVMP` pattern is to avoid business logic in a `ViewModel` entirely.

Presenters implement `androidx.lifecycle.ViewModel` to have that `ViewModel` scope and do the business logic that you would normally do in a `ViewModel` in a classic MVVM.

### Presentation module

Feature-related Activities, Fragments, ViewModels, ViewModelUpdaters, ViewModelMappers and Presenters

### Platform module

Android-scpecific non-feature related functionality

### Domain module

Business logic

### Data module

Storage mechanism. Uses Room database.


# Disclaimer

You will also find a TODO that states that the currency should not be hardcoded, as I decided to leave out this functionality to save development time.

One more useful feature that is missing is an `InputFilter` on the Amount text view that forbids entering more fraction digits than the selected currency allows.
