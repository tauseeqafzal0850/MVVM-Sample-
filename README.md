# MVVM Architecture

<br /><b>Model:</b>
It represents the data and the business logic of the Android Application. It consists of the business logic - local and remote data source, model classes, repository.
<br /><b>View:</b>
It consists of the UI Code(Activity, Fragment), XML. It sends the user action to the ViewModel but does not get the response back directly. To get the response, it has to subscribe to the observables which ViewModel exposes to it.
<br /><b>ViewModel:</b> 
It is a bridge between the View and Model(business logic). It does not have any clue which View has to use it as it does not have a direct reference to the View. So basically, the ViewModel should not be aware of the view who is interacting with. It interacts with the Model and exposes the observable that can be observed by the View.
<br />
<br />
<b>Mostly Used</b><br />
<li>Dependency Injection</li>
<li>Room Database</li>
<li>Coil</li>
<li>Remote Source</li>
<li>Hilt View Model</li>
<li>Coroutines</li>
<li>Flow</li>
<br />
<br />
<b>Project Structure</b><br />
<br />
![image](https://user-images.githubusercontent.com/49031493/199671050-1bf71b83-423c-44ae-9713-e19bebe0a6fd.png)

