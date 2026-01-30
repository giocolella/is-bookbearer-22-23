# 📚 Book Bearer
**Your ultimate reading companion**

[![Android](https://img.shields.io/badge/Android-9.0%2B-green.svg?logo=android)](https://www.android.com/)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg?logo=java)](https://www.java.com/)
[![Firebase](https://img.shields.io/badge/Backend-Firebase-yellow.svg?logo=firebase)](https://firebase.google.com/)
[![Status](https://img.shields.io/badge/Status-Completed-success.svg)]()
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)]()

---

## 📑 Table of Contents
1. [Overview](#-overview)
2. [Motivation](#-motivation--why-this-project)
3. [Components & Capabilities](#-components--capabilities)
4. [Requirements](#-requirements)
5. [Setup Guide](#-setup-guide)
6. [Tools & Documentation](#-tools--documentation)

---

## 📌 Overview
**Book Bearer** is a native Android application designed to help avid readers navigate the vast world of literary productions. Inspired by Tolkien's "Ring Bearer", this app serves as a trusty companion for your reading journey.

The main goals are:
- **Discover** book quality before purchasing through community reviews.
- **Manage** your reading life with personalized lists ("Read" and "To Read").
- **Contribute** to the community by writing your own reviews.

This project was developed as part of the **Software Engineering** course (a.a. 2022-23) at the University of Salerno.

---

## 🎯 Motivation / Why This Project?
- 📱 **Mobile Development**: To master complex Android application development using **Java** and **Android Studio**.
- ☁️ **Serverless Architecture**: To explore **Firebase** as a full backend solution (Auth, Firestore, Storage) for managing data and users without a dedicated server.
- 🏗 **Design Patterns**: To implement the **MVP (Model-View-Presenter)** architecture, ensuring clean, testable and maintainable code.
- 📖 **User Needs**: To solve the practical "what should I read next?" problem by offering a tool to track readings and avoid blind purchases.

---

## ⚙ Components & Capabilities

#### **Android (Java)**
- Native application targeting **Android 9.0 (Pie)** and above.
- Intuitive and responsive UI optimized for portrait mode.

#### **Firebase Ecosystem**
- **Authentication**: Secure user registration and login management.
- **Cloud Firestore**: A NoSQL database handling user profiles, book catalogs, custom lists, and reviews with real-time synchronization.
- **Cloud Storage**: Secure storage for book covers and user profile pictures.

#### **Key Libraries**
- **Glide**: Used for efficient image loading, caching, and management to ensure smooth scrolling performance.

#### **Architecture**
- **MVP (Model-View-Presenter)**: Strict separation of concerns where the Presenter acts as the middleman between the Business Logic (Model) and the UI (View).

---

## 💻 Requirements

### **Hardware**
- Android Device or Emulator.
- Active Internet connection (required for Firebase services).

### **Software**
- **Android Studio** (Latest version recommended).
- **JDK 17**.
- **Android SDK** (Min SDK: API 28 / Android 9.0).

---

## 🚀 Setup Guide

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/giocolella/is-bookbearer-22-23.git](https://github.com/giocolella/is-bookbearer-22-23.git)
    ```

2.  **Open the project:**
    Launch Android Studio, select "Open an existing Android Studio project," and navigate to the cloned folder.

3.  **Firebase Configuration (Crucial):**
    *Note: The app requires a connection to a Firebase project to function.*
    - Create a new project on the [Firebase Console](https://console.firebase.google.com/).
    - Add an Android app to the project using the package name (like `com.example.bookbearer`).
    - Download the `google-services.json` file.
    - **Move the `google-services.json` file into the `app/` folder of your project.**
    - Enable **Authentication** (Email/Password), **Firestore Database**, and **Storage** in the console.

4.  **Sync & Build:**
    Wait for Gradle to sync dependencies.

5.  **Run:**
    Connect an Android device or start an emulator and click **Run 'app'**.

---

## 📚 Tools & Documentation

Full project documentation produced during the software lifecycle:

* **Requirements Analysis**: [RAD (Requirements Analysis Document)](./RAD_BookBearer.pdf) - Actors, use cases and functional requirements.
* **System Design**: [SDD (System Design Document)](./SDD_BookBearer.pdf) - MVP architecture, subsystem decomposition and persistent data.
* **Object Design**: [ODD (Object Design Document)](./ODD_BookBearer.pdf) - Class implementation details and interactions.
* **Testing**:
    * [Test Plan](./Test%20Plan_BookBearer.pdf)
    * [Test Case Specification](./Test%20Case%20Specification_BookBearer.pdf)
    * [Test Execution Report](./Test%20Execution%20Report_BookBearer.pdf)

---
*Created by [Giorgio Colella](https://github.com/giocolella) - University of Salerno*
