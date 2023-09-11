# Android MVPVM Clean Architecture Demo

### What the example application does (User Stories)

The application lets you create a bill and share it with your contacts, everyone pays an equal amount.
The group item is crossed out with strikethrough text once everyone has paid.

<img src="/screenshots/Empty.png" width="320" /> <img src="/screenshots/Wizard.png" width="320" />

<img src="/screenshots/Details.png" width="320" /> <img src="/screenshots/Overview.png" width="320" /> 


### Clean Architecture

This application uses a modified variant of ["*Clean Architecture*" by Uncle Bob](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html), and `MVVMP` (Model-View-ViewModel-Presenter) pattern.

Please refer to the diagram below that compares it with original Clean Architecture by Uncle Bob

<img src="/docs/compare.png" />

The naming and concepts of *Data*, *Domain*, *UI* and *Presentation* layers are taken from [Guide to app architecture](https://developer.android.com/topic/architecture) and pretty much correspond to what they do in the proposed architecture in the linked article.

Having each layer in it's own Gradle module allows for framework separation and reduces a chance of accidental leaks of responsibilities between layers. For example, you cannot access the database or Compose/Views from `*domain* or *Presentation* layer.

This separation makes updating and replacing frameworks easy.

### Domain layer

This is the core of the application itself. I.e. what the application does.

This is a pure `java` module that contains no Android logic whatsoever. It does not know about the UI, database or network requests. These details are implemented in `data`, `presentation` and `ui` modules.

Contains domain models, for example,
- [Group](domain/src/main/java/com/doctoror/splittor/domain/groups/Group.kt)
- [ContactDetails](domain/src/main/java/com/doctoror/splittor/domain/contacts/ContactDetails.kt)

Also, contains Use Cases that represent business logic of the application, see
- [ObserveGroupsUseCase](domain/src/main/java/com/doctoror/splittor/domain/groups/ObserveGroupsUseCase.kt)
- [InsertGroupUseCase](domain/src/main/java/com/doctoror/splittor/domain/groups/InsertGroupUseCase.kt)
- [DeleteGroupUseCase](domain/src/main/java/com/doctoror/splittor/domain/groups/DeleteGroupUseCase.kt)

Also, contains interfaces to data layer, for example
- [GroupsRepository](domain/src/main/java/com/doctoror/splittor/domain/groups/GroupsRepository.kt)
- [ContactDetailsRepository](domain/src/main/java/com/doctoror/splittor/domain/contacts/ContactDetailsRepository.kt)

### Data layer

Your network requests, databases, file system, etc, belong here.

In this example app, only data layer has Room dependency and knows about Room.

It contains models that are useful for data layer only, for example, normalized for Room
- [GroupEntity](data/src/main/java/com/doctoror/splittor/data/groups/GroupEntity.kt) and
- [GroupMemberEntity](data/src/main/java/com/doctoror/splittor/data/groups/GroupMemberEntity.kt)
that are used to store and restore [Group](domain/src/main/java/com/doctoror/splittor/domain/groups/Group.kt) domain model by using mappers, see
- [GroupWithMembersMapper](data/src/main/java/com/doctoror/splittor/data/groups/GroupWithMembersMapper.kt)

Also contains reopsitory implementations, see
- [RoomGroupsRepository](data/src/main/java/com/doctoror/splittor/data/groups/RoomGroupsRepository.kt)
- [ContactDetailsRepositoryImpl](data/src/main/java/com/doctoror/splittor/data/contacts/ContactDetailsRepositoryImpl.kt)


### Presentation layer

Depends on domain layer.

Implements *MVPVM* pattern, contains Presenters, ViewModels and ViewModelUpdaters and mappers from domain models to view models. **Please note that in the chart below *Fragment* belong to *Application module*** and is illustrated for the purpose of showing the data flow

<img src="/docs/MVPVM.png" />

Presenter receives UI and lifecycle events and decides what to do with data by choosing a corresponding Use Case.

[GroupsOverviewPresenter](presentation/src/main/java/com/doctoror/splittor/presentation/groupsoverview/GroupsOverviewPresenter.kt) loads groups domain models by interacting with [ObserveGroupsUseCase](domain/src/main/java/com/doctoror/splittor/domain/groups/ObserveGroupsUseCase.kt), transforms domain models to list item view models by [GroupItemViewModelMapper](presentation/src/main/java/com/doctoror/splittor/presentation/groupsoverview/GroupItemViewModelMapper.kt) and updates the screen view model by [GroupsOverviewViewModelUpdater](presentation/src/main/java/com/doctoror/splittor/presentation/groupsoverview/GroupsOverviewViewModelUpdater.kt)

*ViewModelUpdater* is needed because you don't want your presenter to know how to update the view model, as if you try testing it you would have to set up loading and updating all the fields for each scanario. By extracting the knowledge necessary to update view model you just need to test if a ViewModelUpdater is called.

### UI layer

Depends only on *presentation* layer. Provides Views or Compose content for *presentation* *ViewModels*.

This ensures that the *presentation* layer is always agnostic about the View implementation.

You can easily swap-out *ui* implementations from your *app* module. For example, you can create a *ui* module for same view but with a *Data Binding* framework instead of *Compose* and swap a module dependency based on your build type.

### Application layer

Here lies everything that is needed to build the Android Application.

It contains Android components like *Application*, *Activity*, *Fragment*, *Service*, etc. 

It also khows how to build the DI graph and the DI framework is not leaked to any other modules, which minimizes the effort of replacing the DI framework.

## Drawbacks, Criticism

#### Having Gradle modules per each layer means the application is still a monolith, and if you'd want to extract features to their own modules, you'll also have to create all the layer modules for each feature.
Splitting by features and then layers is possible with the following module structure (see [Nested Modules in Gradle](https://www.developerphil.com/nested-modules-in-gradle/))
- app
- feature-1
  - data
  - domain
  - presentation
  - ui
- feature-2
  - data
  - domain
  - presentation
  - ui

Or, if you have a centralized database

- app
- data (centralized database, application-wide network requests, etc)
- domain (repository interfaces for centralized data, centralized domain models, common use cases)
- feature-1
  - data (network requests for feature-1, DataStore for data in scope of feature-1, etc)
  - domain (use cases for feature-1)
  - presentation
  - ui
- feature-2
  - domain (use cases for feature-2)
  - presentation
  - ui

#### Kotlin Flows is a framework that is "leaked" across modules
Arguably, Flow can be treated as part of the language.

But if you really, really wanted to get rid of leaking the *Flow*s to *domain* you'd have to replace *StateFlow* with a custom *Observable* implementation with a state, possibly extending *java.util.Observable* and use that type in the *domain* module.

Then, adapters can be created in *data* and *presentation* layers that convert from normal *Obesrvable* to *Flows* and vice versa.

But then, if you think about it, the *domain* layer is a rather smaller part of the app, so you've just created a lot of adapters for yourself just to unlink the *domain* module from the *Flows* framework. However, the *domain* layer will still have to use *suspend* functions and coroutines would still be leaked. And if you'd really want to get rid of the coroutines leak in the domain module, you will lose the ability to use *Flow*s adapters in *Room* at all. This also means that you won't be able to benefit from *viewModelScope* in the *Presenter* and you'd have to manually deal with the lifecycle.

And thus, getting rid of coroutines framework leak does not seem to be beneficial at all. But then, does this still count as "Clean Aritecture" if we depend on coroutines so much?
