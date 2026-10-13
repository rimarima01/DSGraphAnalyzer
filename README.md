# Data Structure & Graph Performance Analyzer

**CIT300 Data Structures and Algorithms – Graded Practical Assignment 2 (Week 12)**
Sri Lanka Technology Campus (SLTC) · Language: **Java** · Type: **Console-based application**

---

## 1. Project Description

The **Data Structure & Graph Performance Analyzer** is a single, integrated Java console application that
demonstrates the practical use of core data structures and algorithms covered in Weeks 1–11 of CIT300.

Through one main menu the user can:

* work with an **Array, Stack, Queue and Linked List** (all implemented manually, without `java.util` collections),
* compare **Linear Search** and **Binary Search**,
* build and explore a **Graph** using **BFS** and **DFS**,
* run a **Performance Comparison** that records the **number of steps** and **execution time** and explains the
  results using **algorithmic complexity (Big-O)**.

All user input is validated and all invalid operations (for example popping from an empty stack) are handled
with clear messages instead of crashes.

---

## 2. Team Members

| No. | Name | Student ID | Role |
|-----|------|-----------|------|
| 1 | Mohammed Siddeek Fathima Rimasa | 23DA2-0507 | Group Leader |
| 2 | Abdul Nazeer Thaiseer Ahamed | 23DA2-0514 | Member 2 |
| 3 | Mohammed Uwais Mohammed Amhar | 23DA2-0673 | Member 3 |
| 4 | Mohamed Iqbal Fathima Zeenath Azzah | 23DA2-1072 | Member 4 |


---

## 3. Responsibilities and Individual Contributions

### Student Name: MSF.Rimasa (Group Leader)
**Student ID:** 23DA2-0507

**Assigned Responsibility:** Array component, Searching component, Main menu & system integration, GitHub repository owner

**Individual Contribution:**
- Created the GitHub repository, `.gitignore` and project structure
- Implemented `DynamicArray` (insert at end / at position, delete by position / value, linear search, sort, display, auto-resize)
- Implemented `ArrayMenu` with input validation
- Implemented `SearchAlgorithms`, `SearchResult` and `SearchMenu` (Linear Search, Binary Search, side-by-side comparison, sorted test-data generator)
- Implemented the shared utilities `InputHelper` (input validation) and `ResultLog` (Display All Results)
- Implemented `Main` (main menu) and integrated all modules into one application
- Reviewed and merged teammates' pull requests

### Student Name: ANT.Ahamed
**Student ID:** 23DA2-0514

**Assigned Responsibility:** Stack component, Queue component, Testing

**Individual Contribution:**
- Implemented `ArrayStack` (push, pop, peek, display, overflow / underflow handling)
- Implemented `StackMenu` including the **balanced-bracket checker** (stack application)
- Implemented `CircularQueue` (enqueue, dequeue, peek/front, display, circular wrap-around, overflow / underflow handling)
- Implemented `QueueMenu`
- Wrote the test-case document `TESTING.md` and tested invalid inputs and empty-structure cases
- Tested the Stack and Queue menus inside the integrated main application

### Student Name: MUM.Amhar
**Student ID:** 23DA2-0673

**Assigned Responsibility:** Linked List component, Performance comparison

**Individual Contribution:**
- Implemented `SinglyLinkedList` (insert at head / tail / position, delete by value, delete head, search, reverse, display)
- Implemented `LinkedListMenu` with validation and empty-list handling
- Implemented `PerformanceAnalyzer` (Linear vs Binary search, search growth table, BFS vs DFS, Array vs Linked List insertion, full report)
- Wrote the complexity explanations ("WHY?") shown under every comparison table
- Tested the Linked List and Performance modules inside the integrated main application

### Student Name: MIFZ.Azzah
**Student ID:** 23DA2-1072

**Assigned Responsibility:** Graph component, Documentation, Video merge

**Individual Contribution:**
- Implemented the `Graph` class using an **adjacency list**
- Implemented add vertex, add edge, remove edge, display adjacency list, display adjacency matrix
- Implemented **BFS** traversal (queue) and **DFS** traversal (stack) with step counting
- Implemented vertex search (BFS / DFS), shortest-path search, sample-graph loader and random-graph generator
- Implemented `GraphMenu` with validation
- Tested the graph functionality, including inside the integrated main application
- Prepared the README and merged the final demonstration video

---

## 4. Technologies Used

| Item | Details |
|------|---------|
| Language | Java 17 or newer (developed on Java 21) |
| Application type | Console / command-line |
| Libraries | Java standard library only (`java.util` for `Scanner`, `ArrayDeque`, `HashMap`, `HashSet`, `Arrays`) |
| Version control | Git + GitHub (branches, commits, pull requests) |
| Tools | VS Code / IntelliJ IDEA / Eclipse, Git Bash / Command Prompt |

---

## 5. Main System Features

| Menu | Features |
|------|----------|
| **1. Array** | Insert (end / position), delete (position / value), search, display, sort, random fill, clear, auto-resize |
| **2. Stack** | Push, pop, peek, display, overflow & underflow handling, balanced-bracket checker |
| **3. Queue** | Enqueue, dequeue, peek/front, display, circular array, overflow & underflow handling |
| **4. Linked List** | Insert (head / tail / position), delete (value / head), search, display, reverse |
| **5. Searching** | Linear search, binary search, comparison on identical data, sorted test-data generator |
| **6. Graph** | Add vertex / edge, remove edge, adjacency list & matrix, BFS, DFS, vertex search, shortest path, sample graph |
| **7. Performance Comparison** | Steps + average execution time for Linear vs Binary, BFS vs DFS, Array vs Linked List; growth table; full report |
| **8. Display All Results** | Log of every operation and result recorded during the session |
| **9. Exit** | Ends the program |

### How steps are counted
| Algorithm | One "step" is… |
|-----------|----------------|
| Linear search | one element comparison |
| Binary search | one comparison with the middle element |
| BFS / DFS | one vertex visited **or** one edge examined (so a full traversal = V + 2E) |
| Array insert / delete | one element shifted (+1 for the insert itself) |
| Linked list operations | one node visited |

### Complexity summary
| Operation | Array | Linked List | Stack | Queue |
|-----------|-------|-------------|-------|-------|
| Insert at front | O(n) | **O(1)** | – | – |
| Insert at end | **O(1)** | O(n) | – | – |
| Search | O(n) | O(n) | – | – |
| Push / Pop / Peek | – | – | **O(1)** | – |
| Enqueue / Dequeue / Peek | – | – | – | **O(1)** |

| Algorithm | Time complexity |
|-----------|-----------------|
| Linear search | O(n) |
| Binary search | O(log n) (data must be sorted) |
| BFS / DFS (adjacency list) | O(V + E) |

---

## 6. Project Structure

```
DSGraphAnalyzer/
├── README.md
├── TESTING.md                     # 32 test cases
├── .gitignore
└── src/
    ├── Main.java                  # main menu + integration
    ├── InputHelper.java           # input validation
    ├── ResultLog.java             # "Display All Results"
    ├── DynamicArray.java          # Array
    ├── ArrayMenu.java
    ├── ArrayStack.java            # Stack
    ├── StackMenu.java
    ├── CircularQueue.java         # Queue
    ├── QueueMenu.java
    ├── SinglyLinkedList.java      # Linked List
    ├── LinkedListMenu.java
    ├── SearchAlgorithms.java      # Linear + Binary search
    ├── SearchResult.java
    ├── SearchMenu.java
    ├── Graph.java                 # Graph (adjacency list, BFS, DFS)
    ├── GraphMenu.java
    └── PerformanceAnalyzer.java   # Performance comparison
```

Object-oriented principles used: **encapsulation** (private fields, public methods), **abstraction**
(each data structure hides its internals), **separation of concerns** (data-structure classes contain logic,
`*Menu` classes handle the user interface), **exception handling** (`IllegalStateException`,
`IndexOutOfBoundsException`, `IllegalArgumentException`) and **reusability** (shared `InputHelper`, `ResultLog`).

---

## 7. How to Run the Program

### Requirements
* Java JDK 17 or newer – check with `java -version` and `javac -version`.

### Step-by-step (Command Prompt / Terminal)

**1. Clone the repository and open the project folder**

```
git clone https://github.com/rimarima01/DSGraphAnalyzer.git
cd DSGraphAnalyzer
```

**2. Compile all source files into the `out` folder**

Windows (Command Prompt):
```
javac -d out src\*.java
```
Mac / Linux:
```
javac -d out src/*.java
```
No output means the compilation succeeded.

**3. Run the program**

```
java -cp out Main
```

### Using an IDE (IntelliJ / Eclipse / VS Code)
1. Open the `DSGraphAnalyzer` folder as a project.
2. Mark `src` as the sources root.
3. Run `Main.java`.

### Quick demo sequence
1. Enter stack/queue capacity (e.g. `3`).
2. Menu **6 → 10** loads the sample graph, then **6 / 7** to run BFS / DFS from `Colombo` (sample road network of 8 Sri Lankan cities).
3. Menu **5 → 4** generates sorted data (e.g. `1000`), then **5 → 3** compares Linear vs Binary search.
4. Menu **7 → 5** runs the full performance report.
5. Menu **8** shows every recorded result.

---

## 8. Sample Output

```
=============================================================================
 PERFORMANCE COMPARISON
=============================================================================
Operation        Algorithm                 Steps    Avg Time (ns)   Result
-----------------------------------------------------------------------------
Search (last)    Linear Search             10000             2637   found at index 9999
Search (last)    Binary Search                14              550   found at index 9999
Graph Traversal  BFS                        5998          1011944   1000 vertices visited
Graph Traversal  DFS                        5998          1166086   1000 vertices visited
=============================================================================
```
*(Times vary between computers and runs. The number of steps is the reliable measure.)*

**Why the results differ:** Linear search is O(n) – it checks items one by one – while binary search is
O(log n) – it halves the search range every step. BFS and DFS both run in O(V + E), so a full traversal takes
the same number of steps; they only differ when searching for one particular vertex.

---

## 9. Collaboration (GitHub)

Repository: https://github.com/rimarima01/DSGraphAnalyzer

The project was developed using GitHub with one stable `main` branch and separate
feature branches for each responsibility:

- `feature/array` - Array component
- `feature/searching` - Searching component
- `feature/stack` - Stack component
- `feature/queue` - Queue component
- `feature/linkedlist` - Linked List component
- `feature/graph` - Graph component
- `feature/performance` - Performance comparison
- `feature/main-integration` - Main menu integration
- `feature/testing` - Testing document

Every change was reviewed and merged into `main` through Pull Requests:

| Pull Request | Branch | Work |
|---|---|---|
| #1, #2 | `feature/linkedlist` | Linked List |
| #3 | `feature/performance` | Performance comparison |
| #4, #5 | `feature/array` | Array component |
| #6 | `feature/searching` | Searching component |
| #7 | `feature/stack` | Stack component |
| #8 | `feature/queue` | Queue component |
| #9 | `feature/graph` | Graph component |
| #10 | `feature/main-integration` | Main menu integration |
| #11 | `feature/testing` | Testing document (`TESTING.md`) |

Tasks were tracked using GitHub Issues, and the repository history contains the
commits, reviews, approvals and merge records.
