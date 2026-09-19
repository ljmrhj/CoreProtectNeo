# CoreProtect Neo

## !!!WARN:该 mod 由 AI 制作!!!

English documentation: [README-en.md](README-en.md)

> CoreProtect 24.1 的 NeoForge 1.21.1 移植版：方块保护、操作记录、查询与回滚。
>
> 配置项、语言文件（14 种语言，含简繁中文）、数据库表结构、指令与提示文本均与原版插件对齐。

---

## 一、这是什么

原版 CoreProtect 是 Paper/Spigot 插件，本项目把它移植到 NeoForge 平台：

- 保留原版内核：数据库层、写入队列、语言短语、配置键名、SQL 语句与列顺序均沿用原版
- 替换平台层：Bukkit 事件改为 NeoForge 事件，Bukkit 的世界/物品/方块 API 改为 Minecraft/NeoForge API
- 提示与原版一致：所有指令反馈都取自原版的 Phrase 词条与 lang 语言文件

modid：`coreprotect`　版本：`24.1-neoforge`　包名：`net.coreprotect`

---

## 二、环境要求

| 项目 | 要求 |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.228 及以上 |
| Java | 21 |
| 服务端 | 必需（所有记录与指令都在服务端执行） |
| 客户端 | 可选（无客户端专属功能） |
| 数据库 | 内置 SQLite（默认），可选 MySQL |

---

## 三、安装与数据目录

1. 把 `coreprotect-24.1-neoforge.jar` 放进服务端 mods 目录；
2. 启动一次服务器，会自动创建数据目录 `coreprotect/`，位置在服务器根目录下，内容如下：

| 文件 / 目录 | 说明 |
|---|---|
| `config.yml` | 配置（键名与原版一致） |
| `database.db` | SQLite 数据库（使用 MySQL 时不生成） |
| `blacklist.txt` | 可选：忽略名单 |
| `lang/` | 可选：自定义词条与翻译 |

3. 修改 `config.yml` 后执行 `/co reload` 或重启即可生效。

---

## 四、与原版的差异

以下功能暂未移植，但不影响核心的记录、查询与回滚：

- WorldEdit 与 FAWE 的改动记录（NeoForge 端无对应插件体系）
- bStats 遥测（移植版不发送任何遥测数据）
- AdvancedChests 兼容
- API 包：`LookupFilter`、`LookupOptions`、`UsernameAPI`，需要把 Bukkit 的 Material 与 World 换成注册名与维度 ID
- 告示牌与头颅的专属记录
- 旧版本数据库升级脚本 2.11 至 2.20，迁移极老数据库时需要重写，相关文件当前保留在 `src/pending-java`
- 检查器目前是右键单点查看历史，原版是可点击分页列表并区分交互与容器页签

---

## 五、构建

```bash
gradlew build
```

产物为 `build/libs/coreprotect-24.1-neoforge.jar`。

构建环境：

| 组件 | 版本 |
|---|---|
| NeoGradle userdev | 7.1.38 |
| NeoForge | 21.1.228 |
| Gradle wrapper | 9.2.1 |
| JDK | 21 |
| 数据库驱动 | sqlite-jdbc 3.53.0.0、mysql-connector-j 9.1.0、HikariCP 7.0.2，通过 jarJar 打包进模组 |

---

## 六、移植进度

| 阶段 | 内容 | 状态 |
|---|---|---|
| 1 | 工程骨架、语言文件、无平台依赖代码 | 已完成 |
| 2 | 配置系统与数据库层（建表、连接池、语句） | 已完成 |
| 3 | 队列与消费者引擎，方块、会话、聊天记录 | 已完成 |
| 4 | 容器事务、物品、实体击杀记录 | 已完成 |
| 5 | 查询引擎、回滚与还原、`/co` 指令集 | 已完成 |
| 6 | 检查器 `/co i`、清理 `/co purge` | 已完成 |
| 7 | 查询分页、`/co tp`、物品拾取 | 已完成 |
| 8 | 自然变化、漏斗与发射器、指令记录、API 层、告示牌与头颅 | 待开始 |

编译状态：`javac errors: 0`，`src/main/java` 下共 74 个 Java 文件。
