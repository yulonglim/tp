---
layout: page
title: Liew Xin Yi's Project Portfolio Page
---

### Project: TeachBook

TeachBook is a desktop app made for primary and secondary school teachers to manage student contacts. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: Added the `deleteClass` command.
    * What it does: This feature allows users to delete a class.
    * Justification: This feature is necessary in order to fully incorporate the newly added `Class` component.

* **New Feature 2**: Added the `mark` and `unmark` commands.
    * What it does: This feature allows users (teachers) to mark their students as present or absent.
    * Justification: This feature adds value to our app as users can take attendance of students now. The adding of this feature is due to the observation that students absent from class is one of the reasons teachers contact their students.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=xyliew25&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=false)

* **Enhancements to existing features**:
    * Updated `addCommand` to make previously compulsory fields optional (Pull request [\#175](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/175))

* **Documentation**:
    * README
    * User Guide:
        * Added documentation for the `deleteClass` and `list` commands (Pull request [\#124](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/124))
    * Developer Guide:
        * Updated the class diagrams for all components to fit our current implementation (Pull request [\#114](https://github.com/AY2122S1-CS2103T-W10-2/tp/pull/114))
        * Added the implementation details for synchronization of student list in Ui and Model components, complemented by some sequence diagrams of related commands (Commit [1](https://github.com/AY2122S1-CS2103T-W10-2/tp/commit/e17325ba1e7fdf30c9a17ab2381f7e265d01260a))
        * Added the implementation details of how filtering is done
        * Added some use cases
        * Added some manual testings

* **Project management**:
    * Facilitated some team meetings.

* **Community**:
    * Reported bugs and suggestions for other teams in the class (Example [1](https://github.com/xyliew25/ped/issues))
