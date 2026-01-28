# Students App - Assignment 2

An Android application built with Kotlin and Gradle that manages a student repository.

## Features

- **Students List**: View all students in a RecyclerView with picture, name, ID, and check status
- **New Student**: Add a new student to the repository
- **Student Details**: View detailed information about a selected student
- **Edit Student**: Update or delete an existing student (all fields editable, including ID)

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/studentsapp/
│   │   ├── Student.kt                    # Data model
│   │   ├── StudentsRepository.kt         # In-memory database
│   │   ├── StudentAdapter.kt             # RecyclerView adapter
│   │   ├── StudentsListActivity.kt       # Main list screen
│   │   ├── NewStudentActivity.kt         # Add new student screen
│   │   ├── StudentDetailsActivity.kt     # Student details screen
│   │   └── EditStudentActivity.kt        # Edit/delete student screen
│   ├── res/
│   │   ├── layout/                       # XML layout files
│   │   ├── values/                       # Resources (strings, colors, themes)
│   │   └── drawable/                     # Student picture resource
│   └── AndroidManifest.xml
└── build.gradle.kts                      # Module build configuration
```

## Requirements

- Android Studio
- Minimum SDK: 24
- Target SDK: 34
- Kotlin
- Gradle

## How to Run

1. Open the project in Android Studio
2. Sync Gradle files
3. Run the app on an emulator or physical device

## Notes

- Data is stored in memory and will be lost when the app is closed
- All students use the same static picture (no gallery/camera selection)
- The RecyclerView automatically refreshes when returning to the list screen
- Student ID can be changed when editing (with validation to prevent duplicates)
