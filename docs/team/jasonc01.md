---
layout: page
title: Chung Yunseong's Project Portfolio Page
---

### Project: TeachBook

TeachBook is a desktop app for teachers to manage student contacts. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC. (copied from Xin Yi's)

Given below are my contributions to the project.

* **New Feature**: Added the edit class command to the TeachBook.
    * What it does: Allows the user to change the name of the currently selected class without altering the students inside the class.
    * Justification: There are cases where users make spelling mistakes for class names, and this feature allows them to 
                     fix the mistake without deleting the class and creating it again.
    
* **New Feature**: Added the overall grade structure to the TeachBook. Grade class, edit/set/reset grade and grade command for grading students.
    * What it does: Allows the user to set a personalised grading system in TeachBook which the user can then use it to grade students accordingly.
    * Justification: This feature helps the user with the overall grading experience.

* **New Feature**: Added sort command 
    * What it does: Allows the user to sort the students according to name or grade.
    * Justification: This feature helps the user to get a more organised view of the student list.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=JasonC01&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=false)

* **Enhancements to existing features**:
    * Updated edit command to account for additional user fields added.

* **Documentation**:
    * User Guide:
        * In charge of updating overall formatting of command description in UG.
        * Added documentation for `sort`, `resetGrade` and `setGrade`.
    * Developer Guide:
        * Added overall implementation for edit command along with activity and sequence diagrams.
    
