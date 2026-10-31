# Vaitcraft

**Launcher de Vaitcraft para celular (Android)**, basado en Zalith Launcher 2 v2.7.2. Permite instalar y ejecutar Minecraft: Java Edition en dispositivos Android y preparar la instancia del servidor Vaitcraft.

## Compilación y firma de APK

- `Vaitcraft Android CI` compila el APK debug arm64 y ejecuta las pruebas cuando se actualiza un pull request hacia `main`.
- `Vaitcraft Release APK` genera un APK release firmado desde GitHub Actions. Requiere los secretos `KEYSTORE_BASE64`, `STORE_PASSWORD` y `KEY_PASSWORD`; no se guardan claves privadas ni contraseñas en Git.
- `KEYSTORE_BASE64` debe contener el keystore en Base64 y usar el alias `vaitcraft`. También se pueden configurar `OAUTH_CLIENT_ID` y `CURSEFORGE_API_KEY` como secretos opcionales.

**Seguridad de firma:** se detectaron credenciales y keystores publicados anteriormente. Deben considerarse comprometidos. Si ya distribuiste una versión firmada con esa clave, no la reemplaces sin planificar la migración de firma, porque Android no permitirá actualizar esa instalación con una clave distinta.

## Atribución y licencias

- Basado en Zalith Launcher 2, Copyright (C) 2025 MovTery y colaboradores, bajo GPL-3.0 con términos adicionales (ver `LICENSE`). Las cabeceras de licencia de los archivos originales se conservan.
- Los cambios de este fork (nombre, tema `DungeonTheme.kt`, icono, desactivación del actualizador oficial) son también GPL-3.0.
- Fuente Cinzel: SIL Open Font License 1.1 (`licenses/OFL-Cinzel.txt`).
- Vaitcraft no es un producto oficial de Minecraft; no está aprobado ni asociado a Mojang ni a Microsoft. El tema está inspirado en una estética general y no incluye recursos de Minecraft Dungeons.
