# Automation Setup ZH

这两个 TOML 文件是“可创建的 automation 配置草案”，方便你先在 IDEA 里理解结构，再决定是否在 Codex 里真正创建。

## 文件列表

- [weekly-repo-summary.toml](/Users/dmoon/IdeaProjects/days/codex-skills/automations/weekly-repo-summary.toml)
- [daily-failure-check.toml](/Users/dmoon/IdeaProjects/days/codex-skills/automations/daily-failure-check.toml)

## 字段说明

- `name`：自动化名称
- `kind`：这里用的是 `cron`，表示定时任务
- `status`：默认 `ACTIVE`
- `executionEnvironment`：运行环境，这里先用 `local`
- `model`：建议模型
- `reasoningEffort`：推理强度
- `cwds`：任务执行目录
- `rrule`：执行频率
- `prompt`：自动化真正执行的任务内容

## 当前默认时间

- `Weekly Repo Summary`：每周一上午 9 点
- `Daily Failure Check`：每天上午 10 点

以上时间按你当前本地时区理解。

## 调整建议

- 如果你习惯周五复盘，可以把 weekly 改成周五下午
- 如果你每天先开会，可以把 daily 改成上午 11 点
- 如果你只想工作日运行，daily 现在已经覆盖了全周，后续可以改成只保留工作日

## 使用建议

先手动跑几次，确认 prompt 输出风格符合你的习惯，再正式长期启用 automation。这样最稳。
