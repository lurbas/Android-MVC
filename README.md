# Android MVC
This repository holds my interpretation of MVC pattern on Android. Sample application shows clean solution of some common problems:
- displaying refresh indicator after screen rotation.
- storing only one instance of object, even if lots of views uses it.
- updating view state after model has changed, even if lots of views uses it.
- passing object between Fragment, Activities, View etc.

## Diagram

![](https://github.com/lurbas/Android-MVC/blob/master/mvc.png)

## Structure
#### Activity, Fragment
Activities are containers for Fragments. Fragments handle lifecycle of views. After Views creation, they are subscribed to listen for ScreenState changes. Likewise, before Views destruction, they are unsubscribed to prevent memory leaks.

#### Model
Every model is stored in singleton called Storage. It provides basic methods to get item with id, write it, or update. There are two types of items:
- `ScreenState` - which represents current state of a screen (information like: is progress bar visible?; is animation running?; what's scroll position?) and holds list of ItemModels ids.
- `ItemModel` - contains data used for filling one view. When model changes, it notifies corresponding ScreenStates to update themselves.

#### View
- `Layout` - Group of Views. updates itselve with data from ScreenState. Passes ItemModels ids to its children.
- `View` - after update trigger, it gets ItemModel from Storage, and displays the data.

#### Controller
Each controller contains list of static methods used for changing models inside Storage

# License
```
Copyright 2015 Lucas Urbas

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
