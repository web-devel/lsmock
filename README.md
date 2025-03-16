### Mock Language Server
* The aim is to implement all methods defined in the [protocol](https://microsoft.github.io/language-server-protocol/)
* Configurable timeouts and amount of data in responses

### Requirements
* Java 17

### Examples
#### [Intellij](examples/intellij) 
To launch the IntelliJ example use the `Run Intellij IDE` run configuration in the root project, alternatively use `runIde` gradle task in the [example](examples/intellij) project. 


### Debugging
To debug the running language server:
* Launch the server using `./gradlew runServerWithDebug` (by default, all examples use it launch the server)
* Start debugging using `Attach to server` configuration