# Vaitcraft

Fork de **Zalith Launcher 2** (v2.7.2) con otro nombre y una interfaz de piedra oscura, oro y runas.

## Compilar el APK (GitHub Actions)

1. Sube este repositorio a tu cuenta de GitHub (repositorio propio o fork).
2. Pestaña **Actions** → **Vaitcraft APK** → **Run workflow** (elige `arm64` y `Release`).
3. Al terminar (unos 15-30 min), descarga el APK desde **Artifacts**.

### Secretos opcionales (Settings → Secrets and variables → Actions)

| Secreto | Para qué |
|---|---|
| `KEYSTORE_BASE64`, `STORE_PASSWORD`, `KEY_PASSWORD` | Tu clave de firma fija (`base64 -w0 vaitcraft.jks`, alias `vaitcraft`). Sin ellos se usa una clave temporal y cada APK queda con una firma distinta. |
| `OAUTH_CLIENT_ID` | Client ID de tu app de Microsoft/Azure; sin él no funciona el inicio de sesión Microsoft. |
| `CURSEFORGE_API_KEY` | Tu API key de CurseForge; sin ella no funciona CurseForge. |

Crear tu clave de firma una sola vez:

    keytool -genkeypair -keystore vaitcraft.jks -alias vaitcraft -keyalg RSA -keysize 2048 -validity 10000

No subas el `.jks` ni las contraseñas al repositorio.

## Atribución y licencias

- Basado en Zalith Launcher 2, Copyright (C) 2025 MovTery y colaboradores, bajo GPL-3.0 con términos adicionales (ver `LICENSE`). Las cabeceras de licencia de los archivos originales se conservan.
- Los cambios de este fork (nombre, tema `DungeonTheme.kt`, icono, desactivación del actualizador oficial) son también GPL-3.0.
- Fuente Cinzel: SIL Open Font License 1.1 (`licenses/OFL-Cinzel.txt`).
- Vaitcraft no es un producto oficial de Minecraft; no está aprobado ni asociado a Mojang ni a Microsoft. El tema está inspirado en una estética general y no incluye recursos de Minecraft Dungeons.
