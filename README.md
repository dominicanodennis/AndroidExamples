# AndroidExamples
Android example collection... maybe there will be more

This is an example how to pass values from an activity with a ListView to another activity. 
Between both activities is a DialogFragment that asks if you want to open the next activity.
In the next activity the list items value is displayed.

Problem left: prevent DialogFragment to be passed to the BackStack which causes if the user presses
              "back" the dialog to be displayed again. (solved)
              My initial thought was wrong. The activity had the dialog still open due to the fact that it
              has not been dismissed after its work.

Note: Don't mind the german comments.
	  And of course: please don't just copy and paste! I take no responsibilities what so ever if you use this.
	  				 And if you are using it please note the reference for even if it is not much work I put into
	  				 this it is still work.