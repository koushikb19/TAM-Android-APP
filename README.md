# TAM-Android-APP
A simple android app built on Material Design for a college fest to show the details of events/ quizzes and registering for the same.

## Highlights
Showcase your Crew/Organizing member of your fest/event.
Ability to make an event inter-college or intra-college

You can check the app here
https://play.google.com/store/apps/details?id=com.smec.tam

## This app has 4 separate fragments containing details for each of the following.
1. Home - Contains a long text with details of the event
2. Quizzes - Contains list of quizzes conducted with an option to register for the quiz
3. Events - Contains list of Events similar to quizzes.
4. Crew - Contains list of people who are organizing the whole event.


### Adding Firebase Database.
1. Goto firebase.com and register.
2. Create a new project.
3. Select Add app and then select android.
4. Give the package name as com.smec.TAM.
5. Download the config file.
6. Add the config file to ~/app/
7. Run the app and your should be connected to firebase.

Add the details of your Organisation/Company, events, quizzes, crew in firebase using the following schemas to the root of your database.

### Adding Home page text
    The schema for HomePage
    tamInfo: [{ // This is an array containing only one object.
        whyTam: "String" // Provide the description of your fest here.
    }]

### Adding Event/Quizzes
    The schema for Event and quiz is same which is given below
    events/QuizzesList: [{ // Make two arrays in the root directory events for event details, QuizzesList for quiz details
        Details: "String", // Event details. A short description of the event
        content: "String", // Long description of the event
        date: "String", // Date of the event
        image: "String", // Image for the event
        interClg: "Boolean", // This determines if the event is an inter-college or intra-college
        idFirstYear: "Boolean", // When this is true, the event only accepts 1st year students
        location: "String", // Location or venue of the event
        man: int, // This should be set 2 for quizzes and 1 for events *important*
        name: "String", // Name of the event
    }]

### Adding Crew Details.
    The schema for Crew is as follows
    Crew : [{
        Department : "String", // Name of the department
        members: [{ // Members of that department
            Designation: "String", // Designation of the member
            img: "String" // Url to the image (Can be kept in Firebase storage and use that link)
            is_board: "Boolean" //Set true if member is board
            is_head: "Boolean" // Set true if member is head of the particular department.
            name: "String" // Name of the member
        }]
    }]

### Adding list of colleges
    This will set the list of colleges to select if the event is inter-college
    collegeList : [ // Array of names of colleges
        "String" // Add the name of the college
    ]

The registrations will be stored under Registrations object with the event name.

For any suggestions/queries:
email: koushik.bhargava@gmail.com
