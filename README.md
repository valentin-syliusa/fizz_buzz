# What I've Done

I created a basic Android application, split into two modules. 


The first one, domain module, only contains business logic & our use cases. In our case, business logic is limited to an Input model and a use case that'll take an Input, perform our business logic, and return a result as a List of Strings. 
We could have improved this part by creating a dedicated "Result" model with a "value" parameter that'd be a List of Strings, but I though it wasn't really needed.
This domain module also contains one unit test, that test our use case, key part of our business logic in this project.


The second one, app module, contains essentially our presentation part. This app don't really use a repository and don't communicate with a back-end/webservice so the main part is this presentation part. 
I used Navigation Components to have a smooth, easy to understand and easy to improve flow. I also took advantage of the Safe Args plug-in to pass values between Fragments in a more readable and secure way.
Because I used Navigation Components, my presentation part contains a MainActivity that essentially include the NavHostFragment and two Fragments, representing the two screens of our flow.


The Input one displays a form with 5 inputs (I developed a custom View to avoid duplicating code here, the custom view acting as an input view with some parameters to customise it the way we want), check if data is valid (not null for an Int field or not null AND not empty for a String field). If all the fields are valid, we can go to the next step and validation button is enabled. If it's not the case, the validation button is disabled and we can't go to the next Fragment to ensure that our flow is secure.
The result one displays a list of results, through a simple RecyclerView, by calling the dedicated use case.

# Architecture

I tried to implement a Clean Architecture in this project, by splitting presentation & domain layers. 


On the presentation side, I used MVVM pattern with a ViewModel asociated to each Fragment. The ViewModel is responsible of the logic part and generate a State observed by the Fragment that can update its UI following this State's value. 

# Improvements

I could have added a color code for input view to detect if input is valid or invalid, instead of displaying a simple state text.


I also could have improved the input Fragment by re-displaying the proper values when we go back to this Fragment (probably by reloading data and updating fields if data don't match).
In the improvements 


I could've made, the creation of a dedicated UI Input model that'll be passed between Fragment (by must have been a Parcelable or Serialisable so I avoid this to avoid adding useless libraries to the project) instead of passing the 5 arguments. 


I also could've made the input screen more "flexible" and use a RecyclerView instead.


Code is also ready for a Dark mode, we just have to set the colors in dark mode and it'll work because I named colors in a "Design System-like" mode to be able to switch to dark mode easily and quickly.