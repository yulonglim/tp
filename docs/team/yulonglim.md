---
layout: page
title: Lim Yu Long's Project Portfolio Page
---

### Project: Teachbook

TeachBook is a desktop app made for primary and secondary school teachers to manage student contacts. It is optimized for
use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is
written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: *Logic and code for feature is taken from AB4, but was updated to fit the need of our appplication.*

* **New Feature**: Added the print command to the TeachBook.
    * What it does: allows the user to generate an excel sheet with customizable columns.
    * Justification: This feature assist teachers in administrative tasks.
    * Highlights: This enhancement affects features that affects student information. Some special columns will fetch information about the student from the Teachbook and populate the column.
    * Credits: * Used an external library for excel features, org.apache.poi *
    

* **Code contributed**: [RepoSense link]()

* **Enhancements to existing features**:
    * Updated and maintain storage of the Teachbook to store student,class and grade object.

* **Documentation**:
    * User Guide:
      * In charge of updating all the screenshots for the UG.
      * Linked Screenshots to one another for better consistency.
      * Added documentation for `print` command.
    * Developer Guide:
      * Updated undo redo diagrams.
    

