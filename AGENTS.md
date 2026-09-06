# AGENTS.md

## Projekt

`spring-ai-prompt-engineering` — Spring-AI-Beispielprojekt (DeepSeek/OpenAI-Modelle, Prompt-Techniken). Läuft auf
Port 8080 (Kubernetes 30080). Siehe README.md für AI-Test-Prompts und Setup.

## Kommandos

| Zweck | Befehl |
|---|---|
| Format prüfen (spring-javaformat + spotless inkl. shfmt) | `./mvnw validate` |
| Build (ohne Docker) | `./mvnw package -Dskip.docker.build=true` |
| Tests / ITs | `./mvnw test` / `./mvnw verify` |

## Sandbox

- Kit: opencode-sandbox-kit (README → Sandbox). Sandbox-Quirk: vor jedem `./mvnw`
  `export npm_config_bin_links=false` (Spotless/prettier → EPERM im Mount).
- Maven-Auflösung nutzt bei Mount `C:\development\maven-repo:ro` den Host-Cache. Nur echte Maven-Builds sind
  repräsentativ (`mvn dependency:get` ignoriert settings-`<proxies>`).
- Formatting: shfmt `3.13.1` (Spotless `<shfmt>` + CI `mfinelli/setup-shfmt@v4`).

## Hinweise

- **Flaky IT:** `InferenceServiceIT.testingForAnger` (LLM-Klassifikation, nicht-deterministisch) — CI-Failure dort
  ist keine Code-Regression.
- Registry-/Migrations-Entscheidungen: opencode-sandbox-kit Buchhaltung #44.
- Onboarding-Drehbuch: opencode-sandbox-kit #45 (dieses Projekt: Issue #83).
