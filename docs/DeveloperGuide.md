---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-W10-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/teachbook/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/teachbook/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/teachbook/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `ClassListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.
The `ClassCard` will be displayed by the `ClassListPanel` and the `StudentCard` will be displayed by the `StudentListPanel`. 

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/teachbook/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-W10-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` and `Class` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/teachbook/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `TeachBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `TeachBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `TeachBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/teachbook/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the teach book data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `TeachBook`, which `Student` references. This allows `TeachBook` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/teachbook/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both teach book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `TeachBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.teachbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Integration of class feature

#### Design considerations

**Aspect: Structure of the new model component**

To integrate the new class feature into the existing AB3 product, we decided that each student object should have a reference to its class, and there should only be one class object for the same class. We considered and compared a few designs of the rest of the model component:

* **Alternative 1 (current choice):** `TeachBook` maintains a list of all classes. Each class maintains a list of all students in that class. A unique student list containing all students in all classes is generated every time when users execute the `list all` command.
  * Pros
    * When adding/deleting students, we only need to add/delete ONCE using a class's student list at most times.
    * The filtered student list inside `ModelManager` can take the student list of the currently selected class as its source directly.
  * Cons
    * When `list all`, we need to construct the unique student list by iterating through each class's student list, which can degrade the performance of the `list all` command.

* **Alternative 2:** `TeachBook` maintains a list of all classes. Each class maintains a list of all students in that class. `TeachBook` also maintains a unique student list containing all students in all classes.
  * Pros
    * The filtered student list inside `ModelManager` can still take the student list of the currently selected class as its source directly.
    * When `list all`, the filtered student list can also take the maintained unique student list as its source directly.
  * Cons
    * When adding/deleting students, we always need to add/delete TWICE. This means we need to modify both a class's student list and the unique student list.
    * If we simply add/delete students of the unique student list without maintaining a specific order of all the students, the list can look messy when `list all`. We may still need to do a sorting by class when `list all`, which actually also degrades the performance of `list all`.

* **Alternative 3:** `TeachBook` only maintains a unique student list containing all students in all classes.
  * Pros
    * Easiest to implement and most components can be reused from AB3.
    * When adding/deleting students, we only need to add/delete ONCE using the unique student list.
  * Cons
    * Similar to _Alternative 2_, there is still the need to maintain the order of students in the unique student list.
    * We always need a predicate to screen out students of the currently selected class. Since users may interact with a specific class at most times, this can degrade the performance of most commands.

### Synchronization of Student List in Model and UI

To ensure synchronization throughout the program, `ModelManager` maintains a `filteredStudents` observable, which is 
observed by `MainWindow`. `filteredStudents` contains the list of students to be displayed in the UI.

![UiAndModel](images/UiAndModel.png)

`SelectCommand` and `ListCommand` with the `all` option i.e., `list all` are the only two commands that will modify the
`filteredStudents` entirely, i.e., a new observable is created and replaces the existing observable, via 
`ModelManager#updateSourceOfFilteredStudentList()`. However, this change is not observed by `MainWindow` as `MainWindow`
only observes changes within the observable, i.e., the previous `filteredStudents` observable, and not the changes to
the `filteredStudents` variable itself, which contains the observable. To mitigate this issue, the 
`updateStudentListPanel` flag, in the `commandResult` returned after the execution of both `SelectCommand` and 
`ListCommand` with the `all` option, is set to `true`. The flag then triggers the `MainWindow` to retrieve the new 
`filteredStudents` observable via the `MainWindow#updateStudentListPannel()` and start observing changes in the new 
observable. Thereafter, the student list in the UI is again in sync with the student list in the Model.

Below is the sequence diagram of the execution of the `SelectCommand`.

![SelectSequenceDiagram](images/SelectSequenceDiagram.png)

Below is the sequence diagram of the execution of the `ListCommand` with the `all` option.

![ListAllSequenceDiagram](images/ListAllSequenceDiagram.png)

### Delete class feature

#### Implementation

The delete class feature allows users to delete a class and all students in the class from the TeachBook. This feature is facilitated by `DeleteClassCommand` and `DeleteClassCommandParser`.

Given below is an example usage scenario and how the delete class mechanism behaves.

The following object diagram shows an example initial state of the TeachBook:

<img src="images/DeleteClassObjectDiagram0.png" width="520" />

The following sequence diagram shows interactions within the `Logic` component and part of the `Model` component for the `deleteClass B` command:

![Interactions Inside the Logic and Model Components for the `deleteClass B` Command](images/DeleteClassSequenceDiagram.png)

The following object diagram shows the updated TeachBook:

<img src="images/DeleteClassObjectDiagram1.png" width="280" />

#### Design considerations

_{To be updated later}_

### Implementations of some features

### 1. Edit feature

TeachBook allows users to edit students' details after initially adding the students. However, 
a student's class cannot be modified.

#### 1.1 Implementation details

The edit command is implemented using EditCommand and EditCommandParser, along with TeachBookParser
and LogicManager which creates the required objects. Cases where the user enters an invalid input or
does not modify the student at all is handled with exceptions along with corresponding error feedback
to the user.

The edit command has the following format:
`edit INDEX [n/NAME] [c/CLASS] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t\TAG1] [t\TAG2]...`

Given below is the sequence diagram on how EditCommand behaves in TeachBook when the user tries to edit 
the student's name at index 1 of the current class to john

![EditCommandSequenceDiagram](images/EditCommandSequenceDiagram.png)

Given below is the activity diagram for the same scenario above

![EditCommandActivityDiagram](images/EditCommandActivityDiagram.png)

### Filtering

Filtering is an essential feature to have when it comes to an application that stores data. This is because with 
filtering, users can access information with ease in the shortest time possible.

With reference to the discussion on 
[*Synchronization of Student List in Model and UI*](#synchronization-of-student-list-in-model-and-ui) earlier, 
at any point of time, `filteredStudents` will contain either the list of all students from the currently 
selected class or the list of all students from all classes. By making `filteredStudents` a `FilteredList<Student>`,
filtering can be done easily to both the list of all students from a class and the list of all students from all classes
by just passing in the corresponding `Predicate<Student>`. For example, `FindCommand` and `ListCommand` with the 
`absent` option filter the `filteredStudents` via `ModelManager#updateFilteredStudentList()` by passing in the 
`NameContainsKeywordsPredicate` and `StudentIsAbsentPredicate` respectively. To "clear" the filter on the other hand, 
there is the `ListCommand` which utilizes the `ModelManager#updateFilteredStudentList()` as well but passing in
`PREDICATE_SHOW_ALL_STUDENTS` instead. Note that because filtering is done on the `filteredStudent` observable, the 
changes will be observed by the `MainWindow` and the result of filtering will be reflected immediately in the UI.

Below is the sequence diagram of the execution of the `FindCommand`.

![FindSequenceDiagram](images/FindSequenceDiagram.png)

Below is the sequence diagram of the execution of the `ListCommand` with the `absent` option.

![ListAbsentSequenceDiagram](images/ListAbsentSequenceDiagram.png)

Below is the sequence diagram of the execution of the `ListCommand`.

![ListSequenceDiagram](images/ListSequenceDiagram.png)

### 2. Add class feature

TeachBook allows users to add classes.

#### 2.1 Implementation details

The addClass command is implemented using multiple classes. Firstly, when the user input `addClass A`, the LogicManager
will invoke the parseCommand of TeachBookParser to create a addClassCommandParse object. The parse method in 
the AddClassCommandParser will be called to parse the inputs. It will create a class object named `A` and then return a
`addClassCommand` object. The returned `addClassCommand` then runs the `execute()` method which will in turn invoke the chain of
`addClass()`, `addClass()`, `add()` and `add()` command by the `model`, `TeachBook`, `UniqueClassList` and `ObservableList` classes
respectively.

The addClass command has the following format:
`addClass CLASS_NAME`

Given below is the sequence diagram on how addClass Command behaves in TeachBook when the user tries to add a class
named `A`.

![AddClassSequenceDiagram](images/AddClassSequenceDiagram.png)

### Undo/redo feature

#### Implementation

The undo/redo mechanism is facilitated by `VersionedTeachBook`. It extends `TeachBook` with an undo/redo history, each state is stored internally as an `TeachbookDataState` which consist of a `TeachBook` and `TeachbookDisplayState`. `TeachbookDisplayState` stored the filter predicate for the student list if any is applied and also the index of the selected class to be displayed.
The `TeachbookDataState` is stored in a `TeachbookStateList` with a `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedTeachBook#commit()` — Saves the current teach book state in its history.
* `VersionedTeachBook#undo()` — Restores the previous teach book state from its history.
* `VersionedTeachBook#redo()` — Restores a previously undone teach book state from its history.

These operations are exposed in the `Model` interface as `Model#commitTeachBook()`, `Model#undoTeachBook()` and `Model#redoTeachBook()` respectively.

`Model#undoTeachBook()` and `Model#redoTeachBook()` will return display settings to update the model accordingly.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedTeachBook` will be initialized with the initial TeachbookDataState, and the `currentStatePointer` pointing to that single teach book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in the Teachbook. The `delete` command calls `Model#commitTeachBook()`, causing the modified TeachbookDatastate after the `delete 5` command executes to be saved in the `teachBookStateList`, and the `currentStatePointer` is shifted to the newly inserted teach book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David ...` to add a new student. The `add` command also calls `Model#commitTeachBook()`, causing another modified TeachbookDatastate to be saved into the `teachBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitTeachBook()`, so the teach book state will not be saved into the `teachBookStateList`.

</div>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoTeachBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous teach book state, and restores the Teachbook to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial TeachBook state, then there are no previous TeachBook states to restore. The `undo` command uses `Model#canUndoTeachBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoTeachBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the teach book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `teachBookStateList.size() - 1`, pointing to the latest teach book state, then there are no undone TeachBook states to restore. The `redo` command uses `Model#canRedoTeachBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the teach book, such as `list`, will usually not call `Model#commitTeachBook()`, `Model#undoTeachBook()` or `Model#redoTeachBook()`. Thus, the `teachBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitTeachBook()`. Since the `currentStatePointer` is not pointing at the end of the `teachBookStateList`, all teach book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David ...` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations

**Aspect: How undo & redo executes:**

* **Alternative 1 (choice):** Saves the entire teach book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.
  

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* a primary school teacher teaching mathematics
* has a need to manage 4 class worth of student's contacts which she is teaching
* prefer desktop apps over other types
* not proficient in IT but can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* wants to keep her work life and personal life separate
* often face problem of mixing up students with the same name when she adds it into her phone contact list
* have to also add emergency contact into her phone contact list which makes it flooded, which is hard to find or link it with the correct student
* would prefer to have an app that can also store other emergency information (e.g. blood type and allergies) all in one place

**Value proposition**: Manage contacts faster than a typical mouse/GUI driven app. Allows teachers to have a dedicated teach book to keep their work life separated from their personal life. Allows teacher to find students and their emergency information accurately and easily.


### User stories

Priorities: High (must have) - `* * *` , Medium (nice to have) - `* *` , Low (unlikely to have) - `*`

| Priority | As a ...                                                  | I want to ...                                                                                   | So that I can ...                                                                        |
| -------- | --------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| `* * *` | new user | get instructions | refer to instructions when I forget how to use the Teachbook |
| `* * *` | teacher | add the students I teach |  |
| `* * *` | teacher | remove students from my contacts | remove specific students who are no longer take my classes |
| `* * *` | teacher | search for my students in my contacts | get a student's information easily  |
| `* * *` | teacher | view my student's information | contact them easily |
| `* * *` | teacher | separate my students by classes | better sort my contacts |
| `* * *` | teacher | separate my students by classes | not mix up students with similar names but from different classes |
| `* *` | teacher | modify contacts | change information easily rather than creating a new contact to replace the previous one |
| `* *` | teacher with students whom require special attention | add important information about my students such as diet, allergies or health conditions | quickly react to any emergency related to these information |
| `* *` | teacher | contact student parents | inform parents if any incident happen to the child |
| `* *` | teacher | easily store the grades of my students | remember how well each student is doing in my classes |
| `* *` | teacher | sort my students by grade | quickly find out groups of students which require more help |
| `* *` | teacher | set a special grading system | customize my grading system just in case it changes in the future |
| `* *` | teacher | delete a class with its data all at once | quickly remove the class I have stopped teaching |
| `* *` | teacher | clear the Teachbook data all at once | get a fresh Teachbook at the start of the year  |
| `* *` | teacher | filter my students using keywords | quickly list out specific students |
| `* *` | teacher | undo the most recent command | revert any mistakes I make quickly |
| `* *` | teacher | redo the most recent undo | redo any accidental undos |
| `*` | teacher | set special tags for my students | tag my students with extra information |
| `*` | teacher | print out a list of students | do any administrative work that requires a hard copy document |
| `*` | teacher | print out a list of students with their information | do not have to manually input all the information |
| `*` | teacher | view the list of all students | have an overview of all my students |
| `*` | teacher | add all students from a class at once | quickly add the information of the students in each class |
| `*` | teacher | archive my Teachbook data | start over with a clean slate and can retrieve records I need in the future |
| `*` | teacher | able to load a different Teachbook data to my Teachbook  | easily transfer any data from one device to another |


### Use cases

(For all use cases below, the **System** is the `TeachBook` and the **Actor** is the `user`, unless specified otherwise)


**Use case: UC01  - Set GradingSystem.**

MSS:
1. User decides to implement grading system for TeachBook
2. User enters a list of grades for the grading system
3. TeachBook confirms that new grading system has been set successfully
   Use case ends.

Extensions:
2a.  TeachBook detects that a grading system is already present in TeachBook

    2a1. TeachBook requests user to delete existing grading system before setting a new one.
    2a2. User resets grading system (UC02)
    Use case resumes from step 2.

2b. TeachBook detects that the command format is invalid.

    2b1. TeachBook requests user to follow the correct format.
    Use case resumes from step 2.


**Use case: UC02 - Reset GradingSystem.**

MSS:
1. User decides to reset the existing grading system.
2. User enters command for resetting grade.
3. TeachBook confirms that the grading system has been reset successfully.

Extensions:
2a. TeachBook detects that there is no grading system present in TeachBook.

    2a1. TeachBook informs user that no grading has been set, and therefore no grading system to reset.
    Use case ends.

2b. TeachBook detects that the command format is invalid.

    2b1. TeachBook requests user to follow the correct format.
    Use case resumes from step 2.

**Use case UC03 - Grading a student**

MSS:
1. User decides the grade to give to a specific student.
2. User enters command to grade the student.
3. TeachBook confirms that the student has been successfully graded.
   Use case ends.

Extensions:
2a. TeachBook detects that no grading system is present in TeachBook.

    2a1. TeachBook prompts user to set a grading system
    2a2. User sets a new grading system (UC01)
    Use case resumes from step 2.

2b. TeachBook detects that the specified grade is invalid.

    2b1. TeachBook informs user that the grade is invalid and displays the current grading system.
    Use case resumes from step 2.

2c. TeachBook detects that the command format is invalid.

    2c1. TeachBook requests user to follow the correct format.
    Use case resumes from step 2.

**Use case UC04 - Sorting students according to grade.**

MSS:
1. User decides to sort the students according to their grade.
2. User enters the command for sorting students according to their grade.
3. TeachBook sorts the students according to their grade specified by the grading system.
   Use case ends.

Extension:

2a. TeachBook detects that no grading system is present in TeachBook.

    2a1. TeachBook prompts user to set a grading system
    2a2. User sets a new grading system (UC01)
    Use case resumes from step 2.


2b. TeachBook detects that the command format is invalid.

    2b1. TeachBook requests user to follow the correct format.
    Use case resumes from step 2.

**Use case: UC?? - Delete a Student / Students**

MSS:

1. User <ins>list all the students (UC??)</ins>.
2. User requests to delete a specific student / specific students in the list by giving ID(s).
3. TeachBook deletes the student(s).

    Use case ends.

Extensions:

* 1a. The list is empty.

  Use case ends.

* 2a. The given ID(s) is/are invalid.

    * 2a1. For all valid ID(s), TeachBook deletes the student(s).
    * 2a2. For all invalid ID(s), TeachBook shows an error message.

      Use case resumes at step 1.

**Use case: UC?? - List Students from A Class**

MSS:

1. User <ins>select a class (UC??)<ins>.
2. User requests to list students from the class.
3. TeachBook shows a list of students from the class.

   Use case ends.

**Use case: UC?? - List Students from All Classes**

MSS:

1. User requests to list students from all classes.
2. TeachBook shows a list of students from all classes.

   Use case ends.

**Use case: UC?? - List Absent Students from A Class**

MSS:

1. User <ins>select a class (UC??)<ins>.
2. User requests to list absent students from the class.
3. TeachBook shows a list of absent students from the class.

   Use case ends.

**Use case: UC?? - List Absent Students from All Classes**

MSS:

1. User requests to <ins>list all classes (UC??)<ins>.
2. User requests to list absent students from all classes.
3. TeachBook shows a list of absent students from all classes.

   Use case ends.

**Use case: UC?? - Mark a Student as Present**

MSS:

1. User marks a student as present.
2. TeachBook displays the student with his attendance marked.

   Use case ends.

Extensions:

* 1a. Student does not exist.
    * 1a1. TeachBook displays error.

      Use case ends.

**Use case: UC?? - Mark a Student as Absent**

MSS:

1. User marks a student as absent.
2. TeachBook displays the student with his attendance unmarked.

   Use case ends.

Extensions:

* 1a. Student does not exist.
    * 1a1. TeachBook displays error.

      Use case ends.

**Use case: UC?? - print**

MSS:

1. Teacher print an Excel sheet of all the students.
2. TeachBook displays that the Excel sheet is generated and is stored in a specific folder path.

   Use case ends.

Extensions:

* 1a. Teacher does not have Excel on the device.
    * 1a1. TeachBook displays error.

      Use case ends.

* 1b. Teacher does not have a downloads folder on the device.
    * 1b1. TeachBook displays error.

      Use case ends.

**Use case: UC?? - undo**

MSS:

1. Teacher undo a recent command.
2. TeachBook displays the exact state before the previous command was executed.

   Use case ends.

Extensions:

* 1a. Teachbook does not have any commands to undo.
    * 1a1. TeachBook displays error telling user no commands to undo.

      Use case ends.

**Use case: UC?? - redo**

MSS:

1. Teacher redo a recent undo command.
2. TeachBook displays the exact state before the previous undo command was executed.

   Use case ends.

Extensions:

* 1a. Teachbook does not have any undo commands to redo.
    * 1a1. TeachBook displays error telling user no commands to redo.

      Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. The app should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. The app should be able to hold up to 30 classes and 200 students in each class without long time lags in performance (being able to respond to any command within 5 seconds) for typical usage.
3. A new user should be able to understand and use all the commands within two days with the help of the User Guide.
4. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
5. All user data of an app should be kept only on the device installed the app.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Class number**: A letter A, B, C, ...
* **Student number**: A positive integer 1, 2, 3, ...
* **ID**: A serial number assigned to a student when he/she is added to the TeachBook. **ID** is made up of a student's _class number_ and his/her _student number_ in the class,
e.g. if a student is from class A and has student number 2, then the student’s ID would be A2.
* **Currently selected class**:

*{More to be added}*

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases ... }_

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.
   
### Editing a student

1. Editing a student from list of students

    1. Prerequisites: List students in the currently selected class using `list` command or list all students in TeachBook using `list all` command.

    2. Test case: `edit 1 n/Jane p/1234`<br>
       Expected: First student in the list is being edited. Name of the student is changed to `Jane` and phone number of the student is changed to `1234`
    3. Test case: `edit 1`<br>
       Expected: No student is edited. Error details shown in the status message that at least one field has to be specified for edit.
    4. Other incorrect edit commands to try: `edit`, `edit nothing` `edit /nJane /p1234`
       Expected: Similar to previous test case.

### Deleting a class

1. Deleting a class.

    1. Prerequisites: Multiple classes in the list.
    2. Test case: `deleteClass 4E1` where 4E1 is in the list. <br>
       Expected: Class 4E1 is deleted from the list. Details of the deleted class shown in the status message.
    3. Test case: `deleteClass 4E1` where 4E1 is not in the list. <br>
       Expected: No class is deleted. Error details shown in the status message.
    4. Test case: `deleteClass` <br>
       Expected: Similar to previous.

### Editing name of the class

1. Editing the name of the currently selected class.

    1. Prerequisites: A class has to be selected using the `select` command.
    2. Test case: `editClass 4E1`<br>
       Expected: Name of the currently selected class will change to `4E1`. No changes will be made to existing students in the class.
    3. Test case: `editClass 4E1` followed by `editClass 4E1`<br>
       Expected: Error details are shown in the status message that the class already exists in the TeachBook.
    4. Test case: `editClass`<br>
       Expected: Error details are shown in the status message that the CLASS_NAME parameter is missing.

### Setting a grading system in TeachBook

1. Setting a grading system in TeachBook when there is no grading system present.

    1. Prerequisites: There has to be no existing grading system present in TeachBook.
    2. Test case: `setGrade A>B>C>D>E`<br>
       Expected: Grading system is set for TeachBook in descending order from the highest grade `A` to the lowest grade `E`.
    3. Test case: `setGrade A`<br>
       Expected: Grading system is set for TeachBook with a single grade `A`
    4. Test case: `setGrade`<br>
       Expected: Error details are shown in the status message that the grade parameter is missing.


2. Setting a grading system in TeachBook when there is an existing grading system.

    1. Prerequisites: There is an existing grading system in TeachBook.
    2. Test case: `setGrade A>B>C>D>E`<br>
       Expected: Error details are shown in the status message that there is already an existing grading system.
    3. Test case: `setGrade`<br>
       Expected: Error details are shown in the status message that the grade parameter is missing.

### Resetting a grading system in TeachBook

1. Resetting a grading system in TeachBook when there is an existing grading system.
    
    1. Prerequisites: There is an existing grading system in TeachBook
    2. Test case: `resetGrade`<br>
       Expected: Message indicating successful resetting of grade is shown.

2. Resetting a grading system in TeachBook when there is no grading system present.

   1. Prerequisites: There is no grading system present in TeachBook.
   2. Test case: `resetGrade`<br>
      Expected: Error details are shown in the status message that there is no grading system to reset.

### Grading a student

Prerequisites: Students are listed using `list` command or `list all` command.

1. Giving grades to students when there is an existing grading system.

    1. Prerequisites: There is an existing grading system in TeachBook with grades `A>B>C>D>E`
    2. Test case: `grade 1 g/A`<br>
       Expected: First student in the list is graded with an `A`.
    3. Test case: `grade 1 g/F`<br>
       Expected: Error details are shown in the status message that the given grade is invalid.

2. Giving grades to students when there is no grading system present.

    1. Prerequisites: There is no grading system present in TeachBook.
    2. Test case: `grade 2 g/C`<br>
       Expected: Error details are shown in the status message that a grading system has to be set before grading students.

### Sorting students

Prerequisites: Students are listed using `list` command or `list all` command.

1. Sorting students according to grade when there is an existing grading system in TeachBook.

    1. Prerequisites: There is an existing grading system in TeachBook.
    2. Test case: `sort grade`<br>
       Expected: Students are sorted according to their grades in descending order as specified by the grading system.

2. Sorting students according to grade when there is no grading system present in TeachBook.

    1. Prerequisites: There is no grading system present in TeachBook.
    2. Test case: `sort grade`<br>
       Expected: Error details are shown in the status message that a grading system has to be set before sorting according to grade.

3. Sorting students according to name in alphabetical order

    1. Test case: `sort name`<br>
       Expected: Students are sorted according to their name in alphabetical order.

### Marking a student as present

1. Marking a student as present from list of students.

    1. Prerequisites: At least three students in the list.
    2. Test case: `mark 1`<br>
       Expected: First student from the list is marked as present. Details of the marked student shown in the status message.
    3. Test case: `mark 1 2 3` <br>
       Expected: First, second and third students from the list are marked as present. Details of the marked students shown in the status message.
    4. Test case: `mark 2 1 3` <br>
       Expected: Similar to previous. 
    5. Test case: `mark 0`<br>
       Expected: No student is marked. Error details shown in the status message.
    6. Other incorrect `mark` commands to try: `mark`, `mark random`, `mark x`, `mark 1 2 x`, `...` (where x is non-positive or larger than the list size) <br>
       Expected: Similar to previous.

### Marking a student as absent

1. Marking a student as absent from list of students.

    1. Prerequisites: At least three students in the list.
    2. Test case: `unmark 1`<br>
       Expected: First student from the list is marked as absent. Details of the marked student shown in the status message.
    3. Test case: `unmark 1 2 3` <br>
       Expected: First, second and third students from the list are marked as absent. Details of the marked students shown in the status message.
    4. Test case: `unmark 2 1 3` <br>
       Expected: Similar to previous.
    5. Test case: `unmark 0`<br>
       Expected: No student is unmarked. Error details shown in the status message.
    6. Other incorrect `unmark` commands to try: `unmark`, `unmark random`, `unmark x`, `unmark 1 2 x`, `...` (where x is non-positive or larger than the list size) <br>
       Expected: Similar to previous.

### Print a list of students

1. Print the list of students in a class.

    1. Prerequisites: At least two student in the class, device has Excel/can view excel folder and has Downloads folder.
    2. Test case: `print`<br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names.
    3. Test case: `print c/address` <br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names and a column "Address" with the student's address.
    4. Test case: `print c/signature` <br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names and a column "signature" that is empty.
    5. Test case: `print c/ c/address`<br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names and a column "Address" with the student's address, both columns should be separated by an empty column.
    6. Test case: `print c/c/address`<br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names and a column "c/address" that is empty.
    7. Other incorrect `print` commands to try: `print t/`, `print c/` (i.e. print with any invalid prefix after) <br>
       Expected: Error to be thrown.

### Print a list of students

1. Print the list of students in a class.

    1. Prerequisites: At least two student in the class, device has Excel/can view excel folder and has Downloads folder.
    2. Test case: `print`<br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names.
    3. Test case: `print c/address` <br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names and a column "Address" with the student's address.
    4. Test case: `print c/signature` <br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names and a column "signature" that is empty.
    5. Test case: `print c/ c/address`<br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names and a column "Address" with the student's address, both columns should be separated by an empty column.
    6. Test case: `print c/c/address`<br>
       Expected: Excel file in downloads folder with a column "Name" with the student's names and a column "c/address" that is empty.
    7. Other incorrect `print` commands to try: `print t/`, `print c/` (i.e. print with any invalid prefix after) <br>
       Expected: Error to be thrown.

### Undo Commands

1. Undoing a recent command.

    1. Prerequisites: Start with a newly opened teachbook with no commands to undo.
    2. Test case: `undo`<br>
       Expected: Error stating no states to undo.
    3. Test case: `addClass A` followed by `undo` <br>
       Expected: Class A is added get removed after undo is done.
    4. Test case: `list all` followed by `undo` <br>
       Expected: All students will be displayed, then list will return to previous list after undo.
    5. Test case: Multiple `list` followed by `undo`<br>
       Expected: Undo will revert the display back to before first list command is done.
    6. Test case: `edit` first person name without changing anything, followed with `undo`<br>
       Expected: Error stating no states to undo.

### Redo Commands

1. Redoing a recent undo command.

    1. Prerequisites: Start with a newly opened teachbook with no commands to redo.
    2. Test case: `redo`<br>
       Expected: Error stating no states to redo.
    3. Test case: `addClass A` followed by `undo` followed by `redo`<br>
       Expected: Class A is added get removed after undo is done, Class A will reappear after redo is done.
    4. Test case: `list all` followed by `undo` <br>
       Expected: All students will be displayed, then list will return to previous list after undo, all students will be displayed again after redo is done.
    5. Test case: Multiple `list` followed by `undo`<br>
       Expected: Undo will revert the display back to before first list command is done, redo will then cause list to change to the list after command `list` is executed.
    6. Test case: `edit` first person name without changing anything, followed with `undo`, followed with `redo`<br>
       Expected: Error stating no states to redo.

3. _{ more test cases ... }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases ... }_

