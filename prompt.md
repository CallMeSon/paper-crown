# Phase 6 — Polish & QA

Complete the remaining front-end implementation. This phase wires all screens to real backend data, adds animations, sound effects, and handles edge cases.

---

## 1. Dashboard — Wire to Backend

**Files:** `desktop-client/src/main/java/.../view/DashboardView.java`

Replace static placeholders with live data via `BackendClient`:

- [ ] Fetch `StatsDTO` from `GET /api/stats` on page load
- [ ] Display **Total Wins, Total Losses, Total Draws, Win Rate, Current Streak** in stat cards
- [ ] Fetch recent runs from `GET /api/runs` — show last 5 as run cards
- [ ] Fetch achievements from `GET /api/achievements` — show recently unlocked in a mini grid
- [ ] Check `GET /api/runs/unfinished` — if a run exists, show **Resume** button instead of **New Run**
- [ ] **New Run** button: call `POST /api/runs`, then navigate to Play screen
- [ ] **Resume** button: navigate to Play screen with existing run ID
- [ ] Show **health check failed** overlay if `GET /api/health` returns non-UP (retry every 5s)

**Charts (JFreeChart-FX):**
- [ ] `ChartContainer` reusable component wrapping `ChartPanel` + `SwingNode`
- [ ] **Move Usage** — pie/bar chart from `StatsDTO.moveUsage`
- [ ] **Win Trend** — line chart over recent runs (win count per run)
- [ ] **Run Length History** — bar chart (rounds survived per run)

---

## 2. Play Screen — Wire to Backend

**Files:** `desktop-client/src/main/java/.../view/PlayView.java`

- [ ] Accept optional run ID parameter (new vs resume)
- [ ] Display HP as `❤` hearts using `RunDTO.currentHp` / `maxHp`
- [ ] Display round number from `RunDTO.roundNumber`
- [ ] **Move buttons**: on click → `POST /api/runs/{id}/round` with `MoveRequest`
- [ ] Show result feedback after move resolution:
  - **Win:** green highlight + "+1" floating label
  - **Loss:** screen shake + red flash + HP heart removed
  - **Draw:** neutral yellow pulse
- [ ] Show opponent's move alongside player's move
- [ ] **Buff choice dialog** — when `MoveResponse.buffChoice` is non-null, show a modal/overlay with 3 buff cards to choose from
- [ ] On buff selection → `POST /api/runs/{id}/buff?buffId=`
- [ ] **Active Buffs** strip — query active buffs from `RunDTO.activeBuffs`
- [ ] **Round History** — scrollable feed of past rounds (player move, bot move, outcome)
- [ ] **Run ended** — when `MoveResponse.runEnded` is true, show run summary overlay with stats + **Return to Dashboard** button

---

## 3. History Page

**Files:** `desktop-client/src/main/java/.../view/HistoryView.java`

- [ ] Fetch all runs from `GET /api/runs`
- [ ] Display as expandable `RunCard` components
- [ ] Each card shows: run number, date, rounds survived, HP remaining, win/loss/draw counts
- [ ] Expand to show round-by-round detail (fetched from `GET /api/runs/{id}`)
- [ ] Empty state when no runs exist

---

## 4. Achievements Page

**Files:** `desktop-client/src/main/java/.../view/AchievementsView.java`

- [ ] Fetch from `GET /api/achievements`
- [ ] Grid of `AchievementCard` components
- [ ] Three visual states:
  - **Locked:** greyed out, dimmed icon, progress bar
  - **In progress:** partially lit, progress bar at current %
  - **Unlocked:** full color, gold border, checkmark overlay
- [ ] Empty state when none exist

---

## 5. Settings Page

**Files:** `desktop-client/src/main/java/.../view/SettingsView.java`

- [ ] **Fullscreen toggle** — `CheckBox`, persists via `PUT /api/settings`
- [ ] **Master volume** — `Slider` (0.0–1.0), persists
- [ ] **Sound effects** — toggle on/off, persists
- [ ] **Animations** — toggle on/off, persists
- [ ] Load current values from `GET /api/settings` on page mount
- [ ] Auto-save on change

---

## 6. Reusable Components

Create proper JavaFX custom controls under `desktop-client/src/main/java/.../component/`:

- [ ] **StatCard** — icon, value, label, accent color property
- [ ] **AchievementCard** — icon, name, description, progress bar, state (locked/unlocked/progress)
- [ ] **RunCard** — collapsible, summary header + expandable round table
- [ ] **BuffCard** — icon, name, description, selectable state
- [ ] **ChartContainer** — wraps JFreeChart `ChartPanel` in `SwingNode`, themed palette
- [ ] **Toast** — auto-dismissing notification overlay for achievement unlocks and errors

---

## 7. Animations

**Goal:** medium animation complexity. Never block gameplay.

- [ ] **Page transitions** — fade in/out when switching sidebar pages (`OpacityTransition`, 200ms)
- [ ] **Hover animations** — existing CSS, verify all interactive elements respond
- [ ] **Round result animations:**
  - Win: green glow pulse on result area (2 cycles, 300ms)
  - Loss: horizontal shake on HP area (`TranslateTransition`, 50ms × 3)
  - Draw: subtle yellow pulse
- [ ] **Card entrance** — stat cards slide up + fade in on dashboard load
- [ ] **Buff choice** — cards scale up on appear, scale slightly on hover
- [ ] **Achievement toast** — slide in from top-right, stay 4s, slide out
- [ ] Respect `animation_enabled` setting — skip all animations if false

---

## 8. Audio

**Files:** Place `.wav` files in `desktop-client/src/main/resources/sounds/`

- [ ] Add `MediaPlayer` utility class in `desktop-client/src/main/java/.../util/AudioManager.java`
- [ ] Play sounds on:
  - Button click → `/sounds/click.wav`
  - Win round → `/sounds/win.wav`
  - Loss round → `/sounds/lose.wav`
  - Achievement unlock → `/sounds/achievement.wav`
- [ ] Respect `sound_enabled` and `master_volume` settings
- [ ] If sound files are missing, log a warning and silently skip (no crash)

---

## 9. Edge Cases & Error Handling

- [ ] **Backend unavailable** — show overlay: "Backend not running. Start with `docker compose up` + `./gradlew :backend-service:bootRun`" with retry button
- [ ] **Mid-run close** — on startup, check for unfinished run → offer resume on dashboard
- [ ] **HP reaches 0 mid-round** — show death summary, disable further moves, auto-navigate after 3s
- [ ] **Buff selection timeout** — if buff choice is presented, unbuffered state handled gracefully
- [ ] **Empty states** — all list views show helpful message when empty
- [ ] **Network error during move** — show error toast, keep move buttons enabled for retry
- [ ] **Min window size** — enforce 1024×768 minimum (already in `PaperCrownApp.java`, verify)

---

## 10. QA Checklist

After implementation, verify:

- [ ] `./gradlew clean build` passes (all modules compile, all 22+ tests pass)
- [ ] Desktop launches with `./gradlew :desktop-client:run`
- [ ] Backend starts with `./gradlew :backend-service:bootRun`
- [ ] Full game loop: New Run → play rounds → buff selection → run ends → dashboard updates
- [ ] Resume: close app mid-run → reopen → Resume button appears → continue play
- [ ] History: completed runs appear with expandable details
- [ ] Achievements: progress updates after runs, unlocks trigger toast
- [ ] Settings: toggle fullscreen, volume, sounds — persist across restart
- [ ] Backend offline: desktop shows friendly error, recovers when backend comes up
- [ ] Animations: toggle on/off respected
- [ ] No NullPointerExceptions in any screen transition
