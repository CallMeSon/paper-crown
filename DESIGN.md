# DESIGN.md

## Design Philosophy

Paper Crown should visually communicate:

```text
Fantasy roguelike + modern desktop application + dashboard-heavy experience
```

- [x] Fantasy + modern desktop aesthetic
- [ ] Dashboard feels rich (skeleton only)
- [x] Polished without custom artwork (library-based)

Primary priorities:

- [x] usability
- [ ] information density (needs real data wiring)
- [x] maintainability
- [ ] reusable UI components (SidebarItem done, others pending)
- [x] desktop-first workflows

- [x] No hand-made asset dependency

---

# Design Constraints

Hard constraints:

- [x] desktop only
- [x] JavaFX only
- [x] single window application
- [x] English language only
- [x] no custom illustration work required
- [x] icon-first design approach (Ikonli added)
- [x] dark mode only
- [ ] medium animation complexity (not implemented)

Preferred design approach:

```text
Fantasy atmosphere without asset-heavy implementation
```

Achieve atmosphere through:

- [x] typography (Inter/Segoe UI in CSS)
- [x] icons (Ikonli dependency)
- [x] spacing (CSS layout)
- [x] colors (dark fantasy palette)
- [ ] subtle textures (not implemented)
- [x] card design (CSS stat cards)

---

# Window Structure

Single window architecture.

Layout:

```text
------------------------------------------------
| Sidebar | Main Content Area                  |
|         |                                    |
|         |                                    |
------------------------------------------------
```

- [x] Sidebar + content layout

Window requirements:

- [x] resizable
- [x] minimum window size enforced (1024x768)
- [ ] fullscreen support (setting exists, not wired)
- [x] windowed mode support (default)
- [ ] remember previous window preferences (setting key exists)

---

# Sidebar Navigation

- [x] Persistent left sidebar
- [x] Dashboard, Play, History, Achievements, Settings
- [x] Active page highlighted
- [ ] Collapsible (optional)
- [x] Icon + label
- [ ] Smooth navigation transitions (pending)

- [x] No nested sidebars
- [x] Single window

---

# Visual Style

Theme:

```text
Fantasy Roguelike Dashboard
```

Characteristics:

- [x] dark surfaces
- [x] layered cards
- [x] soft rounded corners
- [x] muted fantasy colors
- [ ] subtle depth (dropshadow in CSS)

- [x] No bright neon palettes
- [x] No hyper realistic visuals
- [x] No cartoon-heavy UI

---

# Color Direction

- [x] Dark-first
- [x] Very dark neutrals (#0f0f14, #1a1a24, #14141c)
- [x] Gold (#c9a84c), deep red (#8b2f3a), muted purple (#6b5b95), steel blue (#4a7c8c)
- [x] Accent colors used sparingly

---

# Typography

- [x] Clean sans-serif (Inter, Segoe UI)
- [ ] Fantasy accent font for titles (not implemented — pending)
- [x] Readability first

---

# Icons Strategy

- [x] Icons as primary visual assets
- [x] Icon library approach (Ikonli: FontAwesome 5 + Material Design 2)
- [x] Rock, Paper, Scissors, HP, Achievements, Statistics, Settings

- [x] SVG / vector / library icons

If assets are missing: Request explicit asset download.

```text
/assets/icons
```

---

# Illustrations

- [x] Optional, never blocks development
- [x] No dependency on custom art
- [x] Decorative only

---

# Dashboard Design

Dashboard should be information dense.

Required widgets:

Statistics Cards:

- [ ] Total Wins (not wired)
- [ ] Total Losses (not wired)
- [ ] Total Draws (not wired)
- [ ] Win Rate (not wired)
- [ ] Current Streak (not wired)

Charts:

- [ ] Move Usage (not implemented — JFreeChart dep added)
- [ ] Win Trend (not implemented)
- [ ] Run Length History (not implemented)

Sections:

- [ ] Recent Runs (skeleton list)
- [ ] Achievements (skeleton list)
- [ ] Resume Run (not wired)
- [x] Quick Actions (skeleton buttons)

Dashboard structure:

```text
------------------------------------------------
Top Stats Cards
Charts Area
Recent Runs + Achievements
Quick Actions
------------------------------------------------
```

- [ ] Dashboard skeleton exists, needs wiring

---

# Play Screen Design

Layout proposal:

```text
------------------------------------------------
HP Bar

Enemy Area

Move Selection

Result Area

Active Buffs

Round History
------------------------------------------------
```

Required components:

- [x] HP indicator
- [x] round number
- [x] move buttons (Rock, Paper, Scissors — styled)
- [ ] result feedback (not wired)
- [ ] buffs display (not wired)
- [ ] history feed (not wired)

Move controls:

```text
Rock  Paper  Scissors
```

- [x] Highly visible (large styled buttons)

---

# Gameplay Feedback

Provide strong visual feedback.

Win:

- [ ] highlight (not implemented)
- [ ] animation (not implemented)
- [ ] sound (not implemented)

Loss:

- [ ] shake (not implemented)
- [ ] damage feedback (not implemented)

Draw:

- [ ] neutral feedback (not implemented)

Achievements:

- [ ] toast notification (not implemented)
- [ ] badge popup (not implemented)

---

# Animations

Animation level: medium

- [ ] fade transitions
- [x] hover animations (CSS)
- [ ] page transitions
- [ ] result animations
- [ ] card animations

- [x] No long or blocking animations

---

# History Page

History should be expandable.

- [ ] Run number, date, rounds, stats, buffs, moves (skeleton only)
- [ ] Expandable round-by-round detail (not implemented)
- [ ] Cards / expandable panels (not implemented)
- [x] No giant tables only (not an issue yet)

---

# Achievement Screen

Display achievements as:

```text
badge cards
```

- [ ] Icon, name, description, progress, unlock state (skeleton only)
- [ ] Locked state (not implemented)
- [ ] Unlocked state (not implemented)
- [ ] Progress state (not implemented)

---

# Settings Screen

Required settings:

- [ ] fullscreen toggle (not wired)
- [ ] window mode (not wired)
- [ ] master volume (not wired)
- [ ] sound toggle (not wired)

Optional:

- [ ] animation toggle (not wired)

- [x] No theme switching (as specified)

---

# Audio UX

- [ ] click sounds (not implemented)
- [ ] round results (not implemented)
- [ ] achievement unlock (not implemented)
- [ ] button interactions (not implemented)

- [ ] volume control (not wired)
- [ ] mute toggle (not wired)

---

# Component Reusability

Prefer reusable components.

Examples:

- [ ] StatCard (CSS only, no programmatic component)
- [ ] AchievementCard (not created)
- [ ] RunCard (not created)
- [x] SidebarItem (created)
- [ ] BuffCard (not created)
- [ ] ChartContainer (not created)

- [x] No duplicated layouts (not an issue yet)

---

# Accessibility

Keep future-friendly.

- [x] Clear contrast
- [x] Scalable layouts (resizable window)
- [x] Readable typography

- [ ] Accessibility features not mandatory yet (not implemented)

---

# Design Anti-Goals

- [x] No custom illustration dependency
- [x] No multiple windows
- [x] No mobile-first layouts
- [x] No over-designed fantasy visuals
- [x] No excessive particle effects
- [x] No cluttered screens
- [x] No tiny text
- [x] No giant nested menus

---

# Success Criteria

- [x] users immediately understand navigation (sidebar + content)
- [ ] dashboard feels rich (skeleton only)
- [ ] game feedback feels responsive (not wired)
- [x] fantasy vibe exists without heavy assets (CSS palette)
- [x] UI remains maintainable
- [ ] components are reusable (SidebarItem done, others pending)
- [ ] interface feels like desktop software instead of a web page (needs polish)
