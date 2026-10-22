# PasswordsManager

> ⚠️ **Learning project — do not store real passwords in it.**
> The current encryption is not secure: the AES key and IV are saved in plain text
> (`application.properties`) next to the encrypted data, the same IV is reused for
> every entry, there is no master password, and AES/CFB8 is unauthenticated.

A small Java Swing desktop app that stores site / email / password entries in an
encrypted `passwords.json`.

## Run

Open the project in IntelliJ IDEA (JDK 21+ with preview features enabled, because
of `STR` string templates) and add the `org.json` jar as a library. Run `Main`.

## Planned security fixes

- Derive the key from a master password (PBKDF2 or Argon2 with a random salt); never store it.
- Switch to AES-GCM with a fresh random IV per entry, stored next to the ciphertext.
- Read passwords with `JPasswordField.getPassword()` and wipe the `char[]` after use.
- Add a Maven/Gradle build and a JUnit encrypt/decrypt round-trip test.

`passwords.json` and `application.properties` are git-ignored and must never be committed.
