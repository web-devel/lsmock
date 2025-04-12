use zed_extension_api as zed;

struct LSMockExtension {

}

impl zed::Extension for LSMockExtension {
    fn new() -> Self {
        Self {
        }
    }
}

zed::register_extension!(LSMockExtension);