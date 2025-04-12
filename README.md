# Mock Language Server
* The aim is to implement all methods defined in the [protocol](https://microsoft.github.io/language-server-protocol/)
* Configurable timeouts and amount of data in responses
* Test integration in different IDEs

## Requirements
* Java 17

## Examples
### IntelliJ
[examples/intellij](examples/intellij)
To launch the IntelliJ example use the `Run Intellij IDE` run configuration in the root project, alternatively use `runIde` gradle task in the [example](examples/intellij) project. 
### VS Code
[examples/vscode](examples/vscode)
See [examples/vscode/README.md](examples/vscode/README.md)
### Zed
[examples/zed](examples/zed)
* [Install Rust](https://www.rust-lang.org/tools/install)
* `cargo buld`
* Open Zed and press Cmd+Shift+P / Ctrl+Shift+P
* Type "install dev extension" and select `examples/zed` dir


## Debugging
To debug the running language server:
* Launch the server using `./gradlew runServerWithDebug` (by default, all examples use it launch the server)
* Start debugging using `Attach to server` configuration