# Activation Strategy 
## Goals
- Work reliably in any file (including arbitrary file types and untitled buffers).
- Be non-intrusive by default.
- Support a fake language id to force “always-on” behavior for demos/tests.
- Allow activation by “start-of-something” prefixes configurable in settings.

## Triggers
### Prefix-gated (universal)
- The server registers for all files.
- Features activate only when the current token starts with one of the configured prefixes (e.g., "mck:", "mck_").
- Outside of these prefixes, the server responds with empty/neutral results.
### Fake language ID: lsmock
- Introduce a fake language id, e.g. (lsmock)
- When a document’s language is set to mock-any, features are always active (no prefix required).
- Useful for workshops, demos, and CI.

## Client Registration (Universal Coverage)
- language: `lsmock`
- general text usage: file and untitled schemes with eager selector