A SceneTransitionAnimation does not work when an application is launched from a second, different, application and then uses Intent.FLAG_NEW_TASK together with ActivityOptions.makeSceneTransitionAnimation bundle to launch a third (internal) activity.

#MakeSceneTransitionAnimation-error

In this test application "External Application" will launch "My Application" using an intent with an action. When My Application is launched, it will start another internal activity containing a
button. When the  button is pressed, a third activity is launched using the flag FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP and ActivityOptions.makeSceneTransitionAnimation bundle. 

##The error
The activity will never be brought to front, but can be seen from the recent tasks list (with a glitchy UI) and in the #HIST tasks list as nowVisible=true. If ActivityOptions.makeSceneTransitionAnimation is commented out, the third activity is launched normally.

##Another solution
If the intent flags is changed to for instance FLAG_ACTIVITY_SINGLE_TOP the scene transition works fine.

##Graphical overview

Error case (if you are using FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP and ActivityOptions.makeSceneTransitionAnimation):
```
 ExternalApplication (MainActivity) -> MyApplication (LaunchActivity) -> MyApplication (A) -> MyApplication (B)
|----------------------------------------task stack 1---------------------------------------|---task stack 2---|
 
```

Working case (ExternalApplication uses FLAG_NEW_TASK)
```
 ExternalApplication (MainActivity) -> MyApplication (LaunchActivity) -> MyApplication (A) -> MyApplication (B)
|------task stack 1-----------------|-----------------------------------task stack 2---------------------------|
 
```

##BUILD
```
./gradlew ExternalLauncher:installDebug
./gradlew MyApplication:installDebug
```

##RUN
1. Start External Application.
2. When My Application is started, press the "Launch" button.
