# CoreProtect Neo

## !!! WARN:THIS MOD WAS MADE BY AI !!!

Chinese documentation: [README.md](README.md)

> A NeoForge 1.21.1 port of CoreProtect 24.1: block protection, action logging, lookups and rollbacks.
>
> Configuration keys, language files (14 locales), database schema, commands and message text all
> match the original plugin.

---

## 1. What this is

The original CoreProtect is a Paper/Spigot plugin. This project ports it to NeoForge:

- **Original core kept**: database layer, write queue, language phrases, config keys, SQL statements
  and column order are all taken from the original plugin.
- **Platform layer replaced**: Bukkit events became NeoForge events, and the Bukkit world/item/block
  APIs were replaced with Minecraft/NeoForge APIs.
- **Identical messages**: every command response comes from the original `Phrase` entries and
  `lang/*.yml` files.

modid: `coreprotect` | version: `24.1-neoforge` | package: `net.coreprotect`

---

## 2. Requirements

| Item | Requirement |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.228 or newer |
| Java | 21 |
| Server | **Required** — all logging and commands run server side |
| Client | Optional — the mod has no client-only features |
| Database | Built-in SQLite (default), MySQL optional |

---

## 3. Installation and data folder

1. Put `coreprotect-24.1-neoforge.jar` into the server's `mods` folder.
2. Start the server once. A `coreprotect/` folder is created in the server directory:

| File / folder | Purpose |
|---|---|
| `config.yml` | Configuration (same keys as the original) |
| `database.db` | SQLite database (not created when MySQL is used) |
| `blacklist.txt` | Optional ignore list |
| `lang/` | Optional custom phrases and translations |

3. After editing `config.yml`, run `/co reload` or restart the server.

---

## 4. Differences from the original

The following features are not ported yet and do not affect the core logging, lookup and rollback
functionality:

- WorldEdit and FAWE change logging (no equivalent plugin ecosystem on NeoForge)
- bStats metrics (the port sends no telemetry at all)
- AdvancedChests compatibility
- API classes: `LookupFilter`, `LookupOptions`, `UsernameAPI` — they need Bukkit `Material`/`World`
  replaced with registry names and dimension ids
- Dedicated sign and skull recording
- Legacy database upgrade scripts 2.11 to 2.20, which need a rewrite to migrate very old databases;
  the files currently live in `src/pending-java`
- The inspector currently shows a single position's history on right-click; the original offers a
  clickable paginated list with separate interaction and container tabs

---

## 5. Building

```bash
gradlew build
```

Output: `build/libs/coreprotect-24.1-neoforge.jar`

Build environment:

| Component | Version |
|---|---|
| NeoGradle userdev | 7.1.38 |
| NeoForge | 21.1.228 |
| Gradle wrapper | 9.2.1 |
| JDK | 21 |
| Database drivers | sqlite-jdbc 3.53.0.0, mysql-connector-j 9.1.0, HikariCP 7.0.2 — bundled into the mod jar via jarJar |

---

## 6. Porting progress

| Stage | Content | Status |
|---|---|---|
| 1 | Project skeleton, language files, platform independent code | Done |
| 2 | Configuration system and database layer (tables, connection pool, statements) | Done |
| 3 | Queue and consumer engine, block/session/chat logging | Done |
| 4 | Container transactions, item and entity kill logging | Done |
| 5 | Lookup engine, rollback and restore, `/co` command set | Done |
| 6 | Inspector `/co i`, purge `/co purge` | Done |
| 7 | Lookup pagination, `/co tp`, item pickups | Done |
| 8 | Natural changes, hoppers and dispensers, command logging, API layer, signs and skulls | Not started |

Compile status: `javac errors: 0`, 74 Java files under `src/main/java`.
