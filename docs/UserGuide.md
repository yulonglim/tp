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
        * [Delete](#deleting-students-contact-information--delete)
        * [Edit](#editing-a-student--edit)
        * [Select](#selecting-a-class--select)
        * [AddClass](#adding-a-class--addclass)
        * [DeleteClass](#deleting-a-class--deleteclass)
        * [EditClass](#editing-a-class--editclass)
        * [List](#listing-all-students--list)
        * [Find](#locating-students-by-name-find)
        * [Print](#printing-to-excel--print)
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

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

{image to be updated}

Format: `help`

### Adding a student: `add`

Adds a student to TeachBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t/TAG1] [t/TAG2]...`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

* Everything is compulsory except for Blood Type, Parents Contact and Tag.
* You can add multiple tags by adding more `t/` flags.
* Student number will automatically be assigned once student is added.

Examples:
* `add n/John Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077` Adds the bare minimum contact information of a student named John Doe from class A into TeachBook.
* `add n/Jane Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077 b/AB+ pc/92349983 t/Class Monitor` Adds contact information of Jane Doe with all the optional information into TeachBook.

### Deleting students’ contact information : `delete`

Deletes the specified students’ contact information from TeachBook.

Format: `delete INDEX`

* Deletes the contact information of the student with the specified `INDEX`.

Examples:
* `delete 2` Deletes the contact information of the student with `INDEX 2` from TeachBook.

### Editing a student : `edit`

Edits the specified student's contact information from TeachBook. It can also be used to add previously not-added
information.

Format: `edit INDEX [n/NAME] [c/CLASS] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t\TAG1] [t\TAG2]...`

* Edits the student at the specified `INDEX`.
* The `INDEX` must be a positive integer 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the student with `INDEX 1` to be
  `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower b/O+` Edits the name of the student with `INDEX 2` to be `Betsy Crower` and adds additional
  information (e.g., blood type and parents contact) that is not previously added.

### Selecting a class : `select`

Selects the **class** to be shown by TeachBook.

Format: `select CLASSNAME`

* The `CLASSNAME` to be selected is case-sensitive and must be already inside the list. If not TeachBook will return `The class does not exist`.
* The currently selected class will be highlighted in blue as seen in the image below where class named `B` is selected.

![select_example](images/select_example.png)

Example:
* `select A` Selects the class named `A` from the list of classes.

### Adding a class : `addClass`

### Deleting a class : `deleteClass`

Deletes the specified class from TeachBook.

Format: `deleteClass CLASSNAME`

* Deletes the class with the specified `CLASSNAME`

Examples:
* `deleteClass A` Deletes the class with `CLASSNAME A` from TeachBook.

### Editing a class : `editClass`

### Listing all students : `list`

Shows the list of all students from the currently selected class or the list of all students from all classes in TeachBook.

* Lists all students from all classes by appending the `all` option.

Format: `list [all]`

Examples:
* `list` Lists all students from the currently selected class.
* `list all` Lists all students from all classes.

### Locating students by name: `find`

Finds students whose name contain any of the given keywords.

Format: `find KEYWORD1 [KEYWORD2]...`

* The search is case-insensitive e.g. john, JOHN or JoHn will match John.
* The order of the keywords does not matter e.g. Doe John will match John Doe.
* Only the name is searched. {more fields to be searched}
* Only full words will be matched e.g. John will not match Johnny.
* Students matching at least one keyword will be returned (i.e. OR search). e.g. John Doe will return Jone Deer, Jane Doe.

Examples:
* `find John Doe` returns only one `John Doe` from the currently selected class, even though there is another `John Doe` from a different class.
* `list all` followed by `find John Doe` returns two `John Doe`, assuming that there are two `John Doe` in the entire TeachBook, one from class `A` and another one from class `B`.

### Printing to Excel : `print`

Prints a student list of current selected class with customized columns.

Format: `print c/COLUMN1 [c/COLUMN2] ...`

* First Column is the names of students in currently selected class.
* Column represents column headers in the Excel file.

Examples:
* `print c/Signature` Creates an Excel (.xls) file with a student name column and Signature column

### Clearing all entries : `clear`

Clears all classes and students from TeachBook.

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

Action             | Format, Examples
-------------------|------------------
**Help**           | `help`
**Add student**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t/TAG1] [t/TAG2]...` <br> e.g., `add n/John Doe p/91234567 e/johndoe@example.com a/21 Lower Kent Ridge Road, Singapore 119077 b/AB+ pc/92039923 t/class treasurer`
**Delete student** | `delete INDEX` <br> e.g., `delete 1`
**Edit student**   | `edit INDEX [n/NAME] [c/CLASS] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BLOOD_TYPE] [pc/PARENTS_CONTACT] [t/TAG1] [t/TAG2]...` <br> e.g.,`edit 3 n/Alice Yeoh b/O+ pc/98533322`
**Select class**   | `select CLASSNAME` <br> e.g., `select A`
**Add class**      | `addClass CLASSNAME` <br> e.g., `addClass A`
**Delete class**   | `deleteClass CLASSNAME` <br> e.g., `deleteClass A`
**Edit class**     | `editClass OLDCLASSNAME NEWCLASSNAME` <br> e.g., `editClass A ClassA`
**List**           | `list [all]` <br> e.g., `list `, `list all`
**Find**           | `find KEYWORD1 [KEYWORD2]...`<br> e.g., `find James Jake`
**Print**          | `print c/COLUMN1 [c/COLUMN2]...` <br> e.g. , `print c/Signature`
**Clear**          | `clear`
**Exit**           | `exit`
