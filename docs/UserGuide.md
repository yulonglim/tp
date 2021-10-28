---
layout: page
title: User Guide
---

# TeachBook User Guide

## Introduction

Welcome to the TeachBook User Guide! 

If you are a primary or secondary school teacher who is using TeachBook, or just someone who wants to find out more 
about what TeachBook can do, you are at the right place!

In this user guide, you will find step-by-step instructions on how you can install TeachBook and guide on how to use all 
of its features.

[comment]: <> (its features. Did we also mention that TeachBook is free?)

## How to use this guide?

As this user guide is created for users with varying levels of experience using our app, it is divided in different 
sections, so feel free to navigate to the portion of interest using our handy Table of Contents provided below. If you 
are a new user who is using TeachBook for the first time, you can follow the [Quick Start](#quick-start) on how to get 
TeachBook running on your system for the first time. If you are a user who faced some issues with TeachBook, you can go 
to the [FAQs](#faqs) section to see if your issues have already been addressed. If not, feel free to reach out to us, 
and we will respond promptly. If you are an experienced user who can't remember the extensive list of features that 
TeachBook provide, you can jump to the [Command Summary](#command-summary) section or the [Features](#features) section 
if you want to find out more.

There are 3 symbols that you need know in order to use this user guide effectively, and they are :information_source:, 
:bulb: and :exclamation:

* If you see a :information_source: symbol, it means that it is additional information that we feel that you ought to know.

* If you see a :bulb: symbol, it means that it is a tip (e.g. shortcut or hidden features) which you can follow to enhance
your experience. 

* If you see a :exclamation: symbol, take extra caution because if you don't follow the instruction listed, it might cause 
the program to not run the way you intended it to and return unwanted results.

## What is TeachBook?

TeachBook is a **desktop app for primary and secondary school teachers to manage student contacts, optimized for use via
a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It is targeted 
towards teachers who can type fast. It allows teachers to store and organise contacts related to their students into 
different classes. On top of that, teachers can tag the class role of the student.

## Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `teachbook.jar` from [here](https://github.com/AY2122S1-CS2103T-W10-2/tp/releases/tag/v1.3).

3. Copy the file to the folder you want to use as the _home folder_ for your TeachBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   {update or delete this point}

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Graphical User Interface

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

{to be updated}

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a student : `add`

You can use this command to add a student to the TeachBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t/TAG1] [t/TAG2]...`

![add](images/addStudent.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

* Everything is compulsory except for Blood Type, Parents Contact and Tag.
* You can add multiple tags by adding more `t/` flags.
* Student number will automatically be assigned once student is added.

Examples:
* `add n/John Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077` adds the bare minimum contact information of a student named John Doe from class A into the TeachBook.
* `add n/Jane Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077 b/AB+ pc/92349983 t/Class Monitor` adds contact information of Jane Doe with all the optional information into the TeachBook.

### Deleting a student : `delete`

Deletes the specified student from the TeachBook.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1, 2, 3, ...

Examples:
* `list all` followed by `delete 2` deletes the 2nd student in the TeachBook.
* `find John` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Editing a student : `edit`

Edits an existing student in the TeachBook. You can also use this command to add previously not-added information.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG1] [t/TAG2]...`

* Edits the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1, 2, 3, ...
* Existing values will be updated to the input values.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Joseph Chan t/` edits the name of the 2nd student to be `Joseph Chan` and clears all existing tags.

### Adding a class : `addClass`

Adds a class with the specified class name to the TeachBook.

Format: `addClass CLASS_NAME`

Examples:
* `addClass example`

![add class](images/addClass.png)

### Deleting a class : `deleteClass`

Deletes the specified class from the TeachBook.

Format: `deleteClass CLASS_NAME`

* Deletes the class with the specified `CLASS_NAME`

Examples:
* `deleteClass A` Deletes the class named `A` from the TeachBook.

### Editing a class : `editClass`

Edits an existing class in the TeachBook.

Format: `editClass INDEX n/CLASS_NAME`

* Edits the class at the specified `INDEX`.
* The index refers to the index number shown in the displayed class list.
* The index must be a positive integer 1, 2, 3, ...
* Existing values will be updated to the input values.

Examples:
* `edit 1 n/Ace` edits the class name of the 1st class to be `Ace`.

### Selecting a class : `select`

Selects the class to be shown by TeachBook.

Format: `select CLASSNAME`

* The `CLASSNAME` to be selected is case-sensitive and must be already inside the list. If not TeachBook will return `The class does not exist`.
* The currently selected class will be highlighted in blue as seen in the image below where class named `B` is selected.

![select_example](images/select_example.png)

Example:
* `select A` Selects the class named `A` from the list of classes.

### Setting a grading system : `setGrade`

Allows for setting of personalised grading system. You may implement your own grading system
which may differ from semester to semester and subject to subject in order to grade your students accordingly.
Grades are set in descending order, from the highest grade, Grade1 to the lowest grade, grade Grade'N'.

Format: `setGrade [Grade1]>[Grade2]>[Grade3]>...>[Grade'N']`

Example:
* `setGrade A>B>C>D>E>F` sets the grading system as A, B, C, D, E and F

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command takes in a list of grades separated by ">" and they are entered in decreasing order!
</div>

### Resetting a grading system : `resetGrade`

Teachbook can only incorporate at most 1 grading system at any time. Therefore, you have to wipe out an existing
grading system before implementing a new one. This command not only resets the grading system, but also wipes out
all the grades which were previously given to the students.

Format: `resetGrade`

### Keying the grade of a student : `grade`

You can use this to set a grade for a particular student.

Format: `grade INDEX g/GRADE`

Example:
* `grade 3 g/A` grades the student at index 3 an A grade.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
You cannot grade a student without having first add grading system. So, remember to do that first!
</div>

### Sorting the students : `sort`

Unorganised lists are a pain to see. Therefore, TeachBook provides the functionality for you to sort your students either
according to name or grade.

Format: `sort grade` `sort name`

Examples:
* `sort grade` sorts the list of students according to their grade in descending order.
* 'sort name' sorts the list of students according to their name in alphabetical order.

### Marking the attendance of a student : `mark`.

You can use this to mark your student as present.

Format: `mark INDEX1 [INDEX2] ...`

Examples:
* `mark 1` marks the student at index 1 as present.
* `mark 2 3 4` marks the students at index 2, 3 and 4 as present all at once.

<div markdown="block" class="alert alert-info">:information_source: **Info**
This command allows you to mark multiple indexes at once. Which can save you lots of time! The check box will turn green
once the attendance of the student is marked! 
</div>

### Unmarking the attendance of a student : `unmark`

You can use this to mark your student as absent.

Format: `unmark INDEX1 [INDEX2] ...`

Examples:
* `unmark 1` marks the student at index 1 as absent.
* `Unmark 2 3 4` marks the students at index 2, 3 and 4 as absent all at once.

<div markdown="block" class="alert alert-info">:information_source: **Info**
This command allows you to unmark multiple indexes at once. Which can save you lots of time! The check box will turn red
once the attendance of the student is unmarked! 
</div>

### Listing all students : `list`

Shows the list of all students from the currently selected class or the entire TeachBook.

Format: `list`  `list all`  `list absentee`

* `list` lists all students from the currently selected class or the entire TeachBook.
* `list all` lists all students in the TeachBook.
* `list absentee` lists all students from the currently selected class or the entire TeachBook whose status is unmarked.

![list all](images/listAll.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
`list all` clears any currently selected class. As a result, any command followed by `list all` will be operating on all students in the TeachBook.
</div>

### Locating students by name : `find`

Finds students whose name contain any of the given keywords.

Format: `find KEYWORD1 [KEYWORD2]...`

* The search is case-insensitive e.g. john, JOHN or JoHn will match John.
* The order of the keywords does not matter e.g. Doe John will match John Doe.
* Only the name is searched. {more fields to be searched}
* Only full words will be matched e.g. John will not match Johnny.
* Students matching at least one keyword will be returned (i.e. OR search). e.g. John Doe will return Jone Deer, Jane Doe.

Examples:
* `find John Doe` returns only one `John Doe` from the currently selected class, even though there is another `John Doe` from a different class.
* `list all` followed by `find John Doe` returns two `John Doe`, assuming that there are two `John Doe` in the entire the TeachBook, one from class `A` and another one from class `B`.

![find](images/findStudent.png)

### Printing to Excel : `print`

Prints a student list of current selected class with customized columns.

Format: `print c/COLUMN1 [c/COLUMN2] ...`

* First Column is the names of students in currently selected class.
* Column represents column headers in the Excel file.
* Columns that requires 

Examples:
* `print c/Signature` Creates an Excel (.xls) file with a student name column and Signature column

### Clearing all entries : `clear`

Clears all classes and students from the TeachBook. In other words, it makes TeachBook clear all its stored data

Format: `clear`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Don't forget that TeachBook provides an undo feature just incase you accidentally clear TeachBook of all its data!
</div>

### Undoing a command : `undo`.

Allows you to undo a command that you have entered. You most likely will be using this command if you have made a mistakes.

Format: `undo`

### Redoing a command: `redo`.

Allows you to redo a command after using an undo command.

Format: `redo`

### Exiting the program : `exit`

Exits TeachBook.

Format: `exit`

<div markdown="block" class="alert alert-info">:information_source: **Info**
TeachBook will automatically save you progress if you exit the app so that you can continue working on it at a later time!
</div>

### Saving the data

* **Except for attendance data**, TeachBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
* **You need to execute `print` command to save any attendance data.**

### Editing the data file

TeachBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TeachBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQs

**Q**: Where does the Excel file save to after using the print command? <br>
**A**: It will be saved to your computer's download folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action                         | Format, Examples
-------------------------------|------------------
**Help**                       | `help`
**Add student**                | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t/TAG1] [t/TAG2]...` <br> e.g., `add n/John Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077 b/AB+ pc/92039923 t/class treasurer`
**Delete student**             | `delete INDEX` <br> e.g., `delete 1`
**Edit student**               | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG1] [t/TAG2]...` <br> e.g.,`edit 2 n/Joseph Chan t/`
**Add class**                  | `addClass CLASS_NAME` <br> e.g., `addClass A`
**Delete class**               | `deleteClass CLASS_NAME` <br> e.g., `deleteClass A`
**Edit class**                 | `editClass INDEX n/CLASS_NAME` <br> e.g., `editClass 1 n/Ace`
**Select class**               | `select CLASS_NAME` <br> e.g., `select A`
**Set grading system**         | `setGrade [Grade1]>[Grade2]>[Grade3]>...>[Grade'N']` <br> e.g., `setGrade A>B>C>D>E>F`
**Reset grading system**       | `resetGrade`
**Key in a grade**             | `grade INDEX g/GRADE` <br> e.g., `grade 3 g/A`
**Sort students**              | `sort name`  `sort grade`
**Mark student attendance**    | `mark INDEX1 [INDEX2] ...` <br> e.g., `mark 1 2 3`
**Unmark student attendance**  | `unmark INDEX1 [INDEX2] ...` <br> e.g., `unmark 1 2 3`
**List students**              | `list `  `list all`  `list absentee`
**Find a particular student**  | `find KEYWORD1 [KEYWORD2]...`<br> e.g., `find James Jake`
**Print data as Excel File**   | `print c/COLUMN1 [c/COLUMN2]...` <br> e.g. , `print c/Signature`
**Clear TeachBook**            | `clear`
**Undo previous command**      | `undo`
**Redo previous command**      | `redo`
**Exit TeachBook**             | `exit`
