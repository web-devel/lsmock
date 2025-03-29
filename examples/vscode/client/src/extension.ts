import {ExtensionContext, workspace} from 'vscode';
import {LanguageClient, LanguageClientOptions, ServerOptions, TransportKind} from 'vscode-languageclient/node';
import * as path from "node:path";

let client: LanguageClient;

export function activate(context: ExtensionContext) {

	// Working directory two levels above this file.
	const workingDir = path.join(__dirname, '..', '..', '..', '..');

	// Log the absolute working directory path for debugging.
	console.log(`Using working directory: ${workingDir}`);

	const isWindows = process.platform === 'win32';

	const gradleCommand = isWindows
		? path.join(workingDir, 'gradlew.bat')
		: path.join(workingDir, 'gradlew');

	const gradleArgs = ['runServerWithDebug', '-q', '--console=plain'];

	const serverOptions: ServerOptions = {
		run: {
			command: gradleCommand,
			args: gradleArgs,
			options: {
				cwd: workingDir,
				shell: isWindows
			},
			transport: TransportKind.stdio
		},
		debug: {
			command: gradleCommand,
			args: gradleArgs,
			options: {
				cwd: workingDir,
				shell: isWindows
			},
			transport: TransportKind.stdio
		},
		args: []
	};

	// Options to control the language client
	const clientOptions: LanguageClientOptions = {
		documentSelector: [{ scheme: 'file', language: 'plaintext' }],
		synchronize: {
			fileEvents: workspace.createFileSystemWatcher('**/.clientrc')
		}
	};

	// Create the language client and start the client.
	client = new LanguageClient(
		'languageServerExample',
		'Language Server Example',
		serverOptions,
		clientOptions
	);

	// Start the client. This will also launch the server
	client.start();
}

export function deactivate(): Thenable<void> | undefined {
	if (!client) {
		return undefined;
	}
	return client.stop();
}