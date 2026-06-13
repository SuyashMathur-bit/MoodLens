# MoodLens

### Android-Based Facial Emotion Analysis Using Google ML Kit

---

## Project Overview

MoodLens is an Android application that analyzes facial expressions in real time using Google ML Kit Face Detection. The application captures an image through the device camera, detects facial features, and estimates the user's emotional state based on smile probability and eye openness.

The system automatically classifies facial expressions into different mood categories and displays corresponding emojis to provide a simple visual representation of the detected emotion.

---

## Features

* Real-Time Camera Capture
* Face Detection using Google ML Kit
* Smile Detection
* Eye Open/Closed Detection
* Mood Classification
* Emoji-Based Emotion Visualization
* Mobile-Friendly User Interface

---

## Technologies Used

### Programming Language

* Java

### Android Development

* Android Studio
* Android SDK

### Libraries

* Google ML Kit Face Detection
* Android Camera Intent

### Concepts

* Face Detection
* Facial Expression Analysis
* Mobile AI Integration
* Image Processing

---

## Project Workflow

### Image Capture

The user captures a photo using the device camera.

↓

### Face Detection

Google ML Kit detects faces from the captured image.

↓

### Feature Extraction

The system extracts:

* Smile Probability
* Left Eye Open Probability
* Right Eye Open Probability

↓

### Mood Analysis

Based on facial probabilities:

* Happy Face
* Neutral Face
* Sad Face

↓

### Emoji Visualization

The corresponding emoji is displayed on the screen.

---

## System Architecture

Camera Capture

↓

Google ML Kit Face Detection

↓

Facial Feature Analysis

↓

Smile & Eye Probability Calculation

↓

Emotion Classification

↓

Emoji Display

---

## Key Features

* Automatic Face Detection
* Real-Time Facial Analysis
* Smile Probability Detection
* Eye State Recognition
* Emotion-Based Emoji Display
* Lightweight Mobile Application
* On-Device Processing

---

## Sample Output

### Happy Emotion

* Smiling: Yes
* Left Eye Open: Yes
* Right Eye Open: Yes
* Emoji: 😊

### Neutral Emotion

* Smiling: Slightly
* Eyes Open
* Emoji: 🙂

### Sad Emotion

* Smiling: No
* Eyes Open/Closed
* Emoji: 😔

---

## Visualizations

The application displays:

* Captured User Image
* Face Analysis Results
* Smile Detection Status
* Eye Detection Status
* Emotion Emoji

---

## Key Insights

* Facial expressions can be estimated using smile probability metrics.
* ML Kit provides efficient on-device face detection without requiring cloud processing.
* Mobile devices can perform facial analysis in real time with low latency.
* Simple facial attributes can be used to infer basic emotional states.

---

## Business Impact

This project can be applied in:

### Mental Wellness Applications

* Basic mood monitoring
* Emotion-aware interfaces

### Smart User Interfaces

* Personalized user experiences
* Emotion-responsive applications

### Educational Applications

* Learning Computer Vision concepts
* Mobile AI development

### Human-Computer Interaction

* Emotion-aware mobile systems
* Interactive applications

---

## Future Improvements

* Deep Learning-Based Emotion Classification
* Multiple Face Detection Support
* Real-Time Video Emotion Detection
* Emotion History Tracking
* Firebase Integration
* Emotion Analytics Dashboard
* Voice + Facial Emotion Fusion

---

## Repository Structure

```text
MoodLens/
│
├── app/src
└── README.md
```

---

## Learning Outcomes

Through this project, I gained practical experience in:

* Android Development
* Google ML Kit
* Face Detection APIs
* Mobile Computer Vision
* Camera Integration
* Real-Time Image Processing
* User Interface Design

---
Application Type: Mobile Face Analysis Application

