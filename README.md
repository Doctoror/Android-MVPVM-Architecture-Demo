# Splittor

This application uses Clean Architecture with an `MVVMP` (Model-View-ViewModel-Presenter) pattern. For Dependency Injection `Koin` is used.

The idea behind `MVVMP` pattern is to avoid business logic in a `ViewModel` entirely.

Presenters implement `androidx.lifecycle.ViewModel` to have that `ViewModel` scope and do the business logic that you would normally do in a `ViewModel` in a classic MVVM.

### Presentation module

Feature-related Activities, Fragments, ViewModels, ViewModelUpdaters, ViewModelMappers and Presenters

### Platform module

Android-scpecific non-feature related functionality

### Domain module

Business logic

### Data module

Storage mechanism


# Disclaimer
For the sake of time saving only one feature (groups list) is covered by unit tests.

You will also find a TODO that states that the currency should not be hardcoded, as I decided to leave out this functionality for time saving sake.

One more useful feature that is missing is an `InputFilter` on the Amount text view that forbids entering more fraction digits than the selected currency allows.
