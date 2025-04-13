use std::env;
use zed_extension_api as zed;
use zed_extension_api::{Command, EnvVars, LanguageServerId, Result, Worktree};

struct LSMockExtension {}

impl zed::Extension for LSMockExtension {
    
    fn new() -> Self {
        Self {}
    }

    fn language_server_command(
        &mut self,
        _language_server_id: &LanguageServerId,
        _worktree: &Worktree,
    ) -> Result<Command> {
        let is_windows = env::consts::OS == "windows";

        let gradle_base_path = std::path::PathBuf::from(env!("CARGO_MANIFEST_DIR"))
            .join("..")
            .join("..");

        let gradle_executable = if is_windows { "gradlew.bat" } else { "gradlew" };
        let gradle_path = gradle_base_path.join(gradle_executable)
            .to_string_lossy()
            .into_owned();
        
        let build_gradle_path = gradle_base_path.join("build.gradle.kts")
            .to_string_lossy()
            .into_owned();
        
        let command_string = if is_windows {
            format!("cmd /c {}", gradle_path)
        } else {
            gradle_path.to_string()
        };

        Ok(Command {
            command: command_string,
            args: vec![
                "--build-file".to_string(),
                build_gradle_path,
                "runServerWithDebug".to_string(),
                "-q".to_string(),
                "--console=plain".to_string()
            ],
            env: EnvVars::default(),
        })
    }
}

zed::register_extension!(LSMockExtension);