# Test Cases – Data Structure & Graph Analyzer

Tester: ANT.Ahamed (23DA2-0514)

| ID | Module | Test | Input | Expected result | Status |
|----|--------|------|-------|-----------------|--------|
| T01 | Menu | Non-numeric menu choice | `abc` | "Invalid input" and menu asks again | Pass |
| T02 | Menu | Choice out of range | `99` | "Please enter a number between…" | Pass |
| T03 | Array | Insert at end | `72`, `85`, `64` | Array shows 72 85 64 | Pass |
| T04 | Array | Insert at position | pos `1`, value `91` | 72 91 85 64, steps = 3 | Pass |
| T05 | Array | Delete from empty array | delete on empty | "Array is empty" message | Pass |
| T06 | Array | Delete invalid position | pos `99` | Re-prompt with valid range | Pass |
| T07 | Array | Search missing value | `777` | "Value not found" | Pass |
| T08 | Array | Auto-resize | insert 11 items (capacity 10) | "capacity doubled" message | Pass |
| T09 | Stack | Push / Peek / Pop | 1, 2, 3 | Top = 3, pops 3, 2, 1 | Pass |
| T10 | Stack | Pop from empty | pop on empty | "Stack Underflow" message | Pass |
| T11 | Stack | Push when full | capacity 3, push 4 items | "Stack Overflow" message | Pass |
| T12 | Stack | Balanced brackets | `{[(a+b)]}` | BALANCED | Pass |
| T13 | Stack | Unbalanced brackets | `(]` | NOT balanced | Pass |
| T14 | Queue | Enqueue / Dequeue order | 101, 102, 103 | Dequeues 101, 102, 103 (FIFO) | Pass |
| T15 | Queue | Dequeue from empty | dequeue on empty | "Queue Underflow" message | Pass |
| T16 | Queue | Enqueue when full | capacity 3, enqueue 4 | "Queue Overflow" message | Pass |
| T17 | Queue | Circular wrap-around | fill, dequeue 1, enqueue 1 | New item stored in freed slot | Pass |
| T18 | Linked List | Insert head/tail/position | 40 (head), 30 (head), 50 (tail), 35 @1 | 30 -> 35 -> 40 -> 50 | Pass |
| T19 | Linked List | Delete missing value | `100` | "not found" | Pass |
| T20 | Linked List | Delete from empty list | delete on empty | "List is empty" | Pass |
| T21 | Linked List | Reverse | 35 -> 40 | 40 -> 35 | Pass |
| T22 | Search | Linear vs Binary | n = 1000, last element | Linear 1000 steps, Binary 10 steps | Pass |
| T23 | Search | Binary on unsorted array | unsorted data | Sorted copy used, message shown | Pass |
| T24 | Graph | Duplicate vertex | add `Jaffna` twice | "already exists" | Pass |
| T25 | Graph | Self-loop | edge `Jaffna Jaffna` | "Self-loops are not allowed" | Pass |
| T26 | Graph | Edge to unknown vertex | edge `Jaffna Vavuniya` | "Both vertices must exist first" | Pass |
| T27 | Graph | BFS on sample graph | start `Colombo` | Colombo Negombo Galle Matale Kandy Matara Badulla Ampara | Pass |
| T28 | Graph | DFS on sample graph | start `Colombo` | Colombo Negombo Matale Kandy Ampara Matara Galle Badulla | Pass |
| T29 | Graph | Shortest path | Colombo to Ampara | Colombo -> Negombo -> Kandy -> Ampara (3 edges) | Pass |
| T30 | Graph | Traversal on empty graph | BFS on empty | "Graph is empty" | Pass |
| T31 | Performance | Full report | menu 7 → 5 | All tables printed with WHY explanations | Pass |
| T32 | Results | Display All Results | menu 8 | Log of all recorded operations | Pass |
