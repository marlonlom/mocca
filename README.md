# Mocca

[![Android](https://img.shields.io/badge/API-33%2B-blue?logo=android-studio)]()
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![CodeFactor](https://www.codefactor.io/repository/github/marlonlom/mocca/badge/main)](https://www.codefactor.io/repository/github/marlonlom/mocca/overview/main)
![Build Status](https://img.shields.io/github/actions/workflow/status/marlonlom/mocca/build.yml)
![GitHub repo size](https://img.shields.io/github/repo-size/marlonlom/mocca)

Android application for mobile phones and smartwatches, that showcases money transfers cost calculation based in [Efecty](https://www.efecty.com.co/web/giros-nacionales) related calculator feature.

# Features

**Mocca** shows an user interface for doing money transfers cost calculation, adding an amount to be sent in Efecty transfers and reviewing the calculation results, or the calculation error based on negative amounts and valid range values.

## Screenshots

![mocca-github_screenshots-all](https://github.com/marlonlom/mocca/assets/1868030/e6df18fe-60e0-4b4d-9939-8ed519aed88c)

## Application modules

**Mocca** contain tne following application modules:

![mocca-github_screenshots-app_modules](https://github.com/marlonlom/mocca/assets/1868030/0cea31f4-f8f7-438c-9738-43387b03566a)

| Module                      | Description                                                               |
|-----------------------------|---------------------------------------------------------------------------|
| `:features:core:calculator` | Core money tramsfers cost calculation implementation                      |
| `:apps:mobile-app`          | Contains the user interface applied to Android mobile phones and tablets. |
| `:apps:wearos-app`          | Contains the user interface applied to WearOS smartwatches.               |

## Architecture

**Mocca** is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

In detail, the app, specially the mobile app, it contains the following defined features:

| Application feature | Description                                                                                                                                        |
|---------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| Calculator          | Contains the UI for the money order cost calculator. Also, the calculation ui state for having a clean usage of the calculation input and outputs. |
| Settings            | Includes the listing and modifications for the following settings: Dark theme, Dynamic colors                                                      |

Both modules described here follows the convention for the already known layers that mvvm pattern include.

```
/* Calculator */
[UI] -> [ViewModel]  // with UI state: CalculatorUiState <- {Empty, WithFailure, WithSuccess}

/* Settings */
[UI] -> [ViewModel] -> [Repository] -> [Datastore]  // with UI state: UserPreferences

```


### WearOS

For the WearOS application module, you have the calculator functionality as a single module. which contains, in a simplified way, the calculator function already described.
In the ui design aspect, the material design current implementation (the compose-material library for wearos) works fine, but, in the future, it will be applied the `material3` implementation for wearos whet it gets available.

## Used Open-source libraries
TBD

# Documentation

## Code-of-Conduct

See the [code of conduct](CODE_OF_CONDUCT.md) document for more information.

## Contributing

See the [Contributing](CONTRIBUTING.md) document for more information.

<hr/>

# License

This application is distributed under the terms of the Apache License (Version 2.0). See the [license](LICENSE) for more
information.

```
Copyright 2023 Marlonlom

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
