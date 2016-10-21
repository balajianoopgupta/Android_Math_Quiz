# Requirements for Android_Math_Quiz Project

# Android_Math_Quiz

--> Task in Hand:
To implement a native Android app for addition, subtraction, and multiplication of single digit numbers.

--> Requirements
1. Home Screen
   a. This screen presents three options to the user to start a quiz for one of the operations, addition, subtraction, or production.

2. Quiz and Quiz Screen
   a. Each quiz consists of 10 questions of the operation type selected through the home screen

   b. The question is presented in the quiz screen one at a time
      i. Question presented as a vertical worksheet
          1. The two numbers are randomly generated and vertically aligned.
          2. The operator must be shown.

      ii. The number of the current question (e.g., 5 out of the total of 10) must be shown so that the user knows the progress

      iii. Each question must be answered within 5 seconds

      iv. The screen also shows a number pad created by you for the user to pick the results
          1. Contains 10 buttons numbered 0-9 respectively, and a button for Enter.
          2. The typed digit should be shown below the two vertically aligned numbers (operands).

      v. The current question moves to next question if any of the following happens
          1. It’s been five seconds since the start of the question
          2. The user has entered the enter key
          3. The user has entered the correct answer (e.g., if answer is 3, and the user pressed the key 3,
             then it automatically moves to next question)
          4. Before moving to next question, you must give a visual indication about the correctness of the current answer,
              without requiring the user to interact with it.

      vi. Summary Dialog
          1. At the end of the quiz, a dialog needs to be down to summary your total scores, 0-10.
          2. The dialog has an OK button, which brings the user back to the home screen.

      vii. Ancestral Navigation:
          When doing the quiz, the user must be given the option to press the app icon to get back to the home screen.
          Before navigating back, prompt the user with a dialog to confirm that he wants to quit the quiz
