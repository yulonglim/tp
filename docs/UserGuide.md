---
layout: page
title: User Guide
---

TeachBook is a **desktop app for teachers to manage student contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It is targeted towards teachers who can type fast. It allows teachers to store and organise contacts related to their students into different classes. On top of that, teachers can tag the class role of the student.

* Table of Contents
    * [Quick start](#quick-start)
    * [Features](#features)
        * [Help](#viewing-help--help)
        * [Add](#adding-a-student-add)
        * [List](#listing-all-students--list)
        * [Edit](#editing-a-student--edit)
        * [Tag](#tagging-a-student--tag)
        * [Print](#printing-to-excel--print)
        * [Delete](#deleting-students-contact-information--delete)
        * [Find](#locating-students-by-name-find)
        * [Clear](#clearing-all-entries--clear)
        * [Exit](#exiting-the-program--exit)
    * [FAQ](#faq)
    * [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `teachbook.jar` from here (available soon).

1. Copy the file to the folder you want to use as the _home folder_ for your TeachBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   {to be updated}

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

{to be updated}

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* `ID` is made up of student’s class and student’s number in class e.g., if a student is from Class A and have student
  number 2, then the student’s ID would be A2

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

{image to be updated}

Format: `help`


### Adding a student: `add`

Adds a student to the TeachBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t/TAG1] ...​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

* Everything is compulsory except for Blood Type, Parents Contact and Tag.
* You can add multiple tags by adding more “t/” flags.
* Student number will automatically be assigned once student is added.

Examples:
* `add n/John Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077` adds the bare minimum contact information of a student named John Doe from class A into TeachBook.
* `add n/Jane Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077 b/AB+ pc/92349983 t/Class Monitor` adds contact information of Jane Doe with all the optional information into TeachBook.

### Listing all students : `list`

Shows a list of all students in the TeachBook.

Format: `list`

### Editing a student : `edit`

Edits the specified student's contact information from TeachBook. It can also be used to add previously not-added information.

Format: `edit ID [n/NAME] [c/CLASS] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BLOOD_TYPE] [pc/PARENTS_CONTACT]​`

* Edits the student at the specified `ID`.
* The ID is made up of student’s class and student’s number in class e.g., if a student is from Class A and have student number 2, then the student’s ID would be A2.
* The student number must be a `positive integer` 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit A1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the student with ID A1 to be `91234567` and `johndoe@example.com` respectively.
*  `edit B2 n/Betsy Crower b/O+` Edits the name of the student with ID B2 to be `Betsy Crower` and adds additional information (e.g., blood type and parents contact) that is not previously added.

### Tagging a student : `tag`

Assigns one or more tags to the specified student.

Format: `tag ID t/TAG1 [t/TAG2] ...​`

* Tag represents class role, which includes class monitor, assistant class monitor, etc.

Examples:

* `tag A2 t/class monitor` Assigns the student with ID A2 the class role tag class monitor
* `tag B1 t/assistant class monitor t/secretary` Assigns the student with ID B1 the class role tag assistant class 
monitor and secretary

### Printing to Excel : `print`

Prints a student list of current selected class with customized columns.

Format: `print c/COLUMN1 [c/COLUMN2] ...​`

* First Column is the names of students in currently selected class.
* Column represents column headers in the Excel file.

Examples:

* `print c/Signature` Creates an Excel (.xls) file with a student name column and Signature column

### Deleting students’ contact information : `delete`

Deletes the specified students’ contact information from TeachBook.

Format: `delete ID`

* Deletes the contact information of the student with the specified `ID`.

Examples:
* `delete A2` deletes the contact information of the student with `ID` `A2` from TeachBook.

### Locating students by name: `find`

{to be updated}

### Clearing all entries : `clear`

{to be updated}

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TeachBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

{to be updated}

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: ...<br>
**A**: ...

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t/TAG1]...​` <br> e.g., `add n/John Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077 b/AB+ pc/92039923 t/class treasurer`
**Clear**  | `clear`
**Delete** | `delete ID`<br> e.g., `delete A2`
**Edit**   | `edit ID [n/NAME] [c/CLASS] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BLOOD_TYPE] [pc/PARENTS_CONTACT]​`<br> e.g.,`edit B3 n/Alice Yeoh b/O+ pc/98533322`
**Tag**    | `tag ID t/TAG1 [t/TAG2]…` <br> e.g., `tag A2 t/class monitor`
**Print**  | `print c/COLUMN1 [c/COLUMN2]...` <br. e.g. , `print c/Signature`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
