# CT-AndroidModuler

This project aims to manage multiple apps from a single source foundation. According to the Google Play Console, apps must be updated periodically with the latest Android SDK version target; otherwise, they will not be shown in the Play Console for updated OS Mobile devices.

#### To address this issue and save development time, we need a consolidated solution since some apps may contain similar code content such as network calls, alerts, camera, or gallery functionalities.

Given the updates in SDK and handling changes in technical and feature behaviors, as well as updating deprecated support libraries, methods, and native feature support classes, managing multiple apps in different timelines can lead to redundant efforts in replicating similar content updates.

###### Example for Feature Behavior udpates 
![Apps Target Android OS 13!](/assets/img.png "Behavior changes example")

Therefore, a Modular Architecture with a Single Source Code Foundation, coupled with Version Control and Branching, can effectively tackle this problem. This approach allows for efficient management of updates across multiple apps, streamlining development efforts and ensuring consistency while adapting to changes in SDKs and technical requirements.

## Modularization
Modularization is a practice of organizing a codebase into loosely coupled and self contained parts. Each part is a module. Each module is independent and serves a clear purpose. By dividing a problem into smaller and easier to solve subproblems, you reduce the complexity of designing and maintaining a large system.

Simple Moduler Architecure for single APP
                                          ![Dependency graph for Single APP]

Here the chanllenge is we have to manage the Gradle an advanced build toolkit, to automate and mange the buil process while letting you define flxble, custom build configurations. 
Each build configuration can define its own set of code and resources while reusing the parts common to all versions of your app.
we have to mange this gradle for each module individially 

