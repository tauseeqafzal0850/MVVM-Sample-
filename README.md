# Model-View-ViewModel 
MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.
![img.png](img.png)

<br /><b>Model:</b>
It represents the data and the business logic of the Android Application. It consists of the business logic - local and remote data source, model classes, repository.
<br /><b>View:</b>
It consists of the UI Code(Activity, Fragment), XML. It sends the user action to the ViewModel but does not get the response back directly. To get the response, it has to subscribe to the observables which ViewModel exposes to it.
<br /><b>ViewModel:</b> 
It is a bridge between the View and Model(business logic). It does not have any clue which View has to use it as it does not have a direct reference to the View. So basically, the ViewModel should not be aware of the view who is interacting with. It interacts with the Model and exposes the observable that can be observed by the View.
<br />
<b>Project Structure</b><br />
For the project, we are going to follow a beginner version of MVVM. Our package in the project will look like below:<br />
![img_1.png](img_1.png)