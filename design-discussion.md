Design Discussion.
===
Individual Designs.
---

**Design 1.**

Design done by jvaughan40.

![design1](../Design-Individual/jvaughan40/design.png "jvaughan40")

Pros: 

<i>Yoshi</i>
>* Player and Adminstrator classes are inherited from the same super class.
>* An assosiation Class of Attempted class
>* I agree with that 7) doesn't affect design directly.
>* requirement 9)'s a and b won't affect either.

<i>James</i>
>* The design is slim.
>* No unnecessary classes.

<i>Alex </i>: 
UML  Correctly describes main entities of design mentioned by Requirements. 
High level of detail allows for the implementation flexibility.


Cons:

<i>Yoshi</i>
>* Attibutes are needed for Player and Administrator classes.
>* Needs specificaions of operations for each class.
>* Although we don't have to worry about how database operates, I believe we need to find out where the queries for database resides. In another word, the connection point to the database. I guess I meant was to have some other service classes or have each entity take the responsibility to be service, maybe.
>* requirement 9)'s c and d will decide what type operation needed.

<i>James</i>
>* Missing some specifications of operations.
>* Missing mention of persistence layer.
>* Does not specify cardinality of relationships. 
>* Not all operations are represented.

 
<i>Alex</i>
Missing: 	attribute’s data types; method’s result data types; 
implied attributes mentioned in reqs(ex req 11 incorrectSolutionSubmissions); 
entities mentioned in reqs (ex req # 4 DataBase for persistency), methods ( ex req #6 validateCryptogram()).
Some attributes assigned to wrong class (ex req 8 firstName, lastName, userName sh b under Player, not User).
No relationship between Administrator and Cryptogram, between EWS and Cryptogram.

 
**Design 2.**
Design done by akryvorutsky3.

![design2](../Design-Individual/akryvorutsky3/design.png "akryvorutsky3")

Pros: 

<i>Yoshi</i>
>* Player and Adminstrator classes are inherited from the same super class.
>* Good to have the cardinality.
>* Good to have the derived attributes but looks like missing attributes where they come from.
>* Sorter as utility class
>* ExternalWebServis has a relationship with the database.
>* An assosiation Class of PlayerCryptogram class

<i>James</i>
>* Good separation of concerns.
>* Cardinality of relationships is shown.
>* Operations are clearly labeled.
>* Good use of inheritance.

<i>Alex</i>
addressed every entity of the design mentioned in the requirements


Cons:
<i>Yoshi</i>
>* <y3 You said login will send dataservice to persist uid and pw. The uid and pw will be associated with Player or Admin? what happens if he became an admin and also a player. how does the login would know what role he is if the id and pw are associated with both? I noticed that there is enum but never used. the enums have custom items in it. It can not be util classes like date or money in the video. It needs association with others.>
>* Player needs its operations from requirement 2) <ak: as described in my md doc : 2.1 “chose cryptogram”  will be handled by DataBase.getPlayerCryptogram()  and Cryptogram.getCryptogram(). Since Cryptogram is not attribute of player, why “choosing” it (or getting it) should be the operation of Player ? 2.2 “solve cryptograms” is handled by  Cryptogram.validateSolution(), 2.3 “see solved” -- DataBase.getPlayerCryptogram() and PlayerCryptogram.seeSolved(), 2.4 “view player ratings” -- DataBase.getPlayers()  . > I meant if Player doesn’t know how to choose Cryptogram, not sure who’s gonna handle choosing a cryptogram for him. Sure each class, Cryptogram or Database can have those methods  but we need some entry points. <UI or MainActivity will take care of navigation and entry points.> <y2 That will break MVC designs Ui should just be responsible for showing and receiving not should be equipped with any  logic. I didn’t see MainActivity or UI in UML anyway. If they are responsible for such an important parts. They should be in UML. Still I don’t think it’s good idea to give all power to MainActivity. There would be no cohesions. > <y1 Or UI will directly call Cryptogram to solve?  If an object didn’t have the method, that means the object wouldn’t know how to do it.  Cryptogram or Database can’t start event their own. Cryptogram could be Player’s attribute, since for this app, Cryptogram is very essential property for a player. ><ak: I do not believe Cryptogram is property of Player, it is an independent object that exists regardless of Player objects being created or destroyed> <y2 I guess I wanted Cryptogram to also keep track the state of the player’s Cryptogram progress but we can use the Association Class PlayerCryptogram.><y 3 You said “Player will call DataService to getAvailableCryptograms() to choose crypto.” but I don’t see the method in the Player class. Also remember in the instruction in the assignment “"The fact that the system will be implemented on the Android platform should not affect your design, which should not contain Android specific elements (e.g., activities)." >
>* If the Database class meant to be DAO. We need to create ones for each entity. and I don't think we need to specify such a detail here. Also it shouldn't include any logic if it's DAO. <ak: we could present DataBase (and rename if needed for clarity) as a utility class ( that sits on top of DAO) that handles persistency and specific I/O operations to/from  database. When we get to implementation phase we’ll have to have some kind of database , probably it will be SQLite. > I still believe database could just be black box which just stores the data and provide the data if requested. What happens if we have more things to store or need some calculation before sending to database. Those should be handle in service classes not in models. If it meant to be a service class, then maybe better change the name. <please suggest specific changes to UML> <y2 I liked the changes you made>
>* missing confirmation message which could be another entity. <ak: req 9 “admin adds new crypto, gets confirm message with unique cryptoId” -- Administrator.addCryptogram():int  < - returns int cryptoId. At first I thought of using association Admin-Crypto class for this (and updateCrypto(), validateEncodedPhrase() ) methods , then because there were no common Admin-Crypto attributes, I decided to just move it to Admin, now I think maybe Cryptogram would be a better “home” for those methods ?  My notes on req 9 should’ve been clearer, directly addressing each part of req 9 even if they’ve already been addressed above or in UML> again Cryptogram can’t receive confirm message but it could send the message if requested but still there should be another end to receive it. <I’m thinking Admin.addCryptogram() will instantiate Cryptogram class, whose constructor will generate uniqueID. That uniqueId will be obtained via getUniqueID() of Cryptogram  > <y2 sounds good and also message? >
>* not sure about requirement 7). it can contain anything but encoding will be done only on alphabet. so I don't know what to validate but sounds more like implementation detail. <ak: I thought if I can create a method to handle this validation, then I can place it under some class, so i did , as Administrator.validateEncodedPhrase():boolean.  I’m thinking now that maybe that could better be placed with Cryptogram class.> I was not sure what needs to be validated. Basically anything can be inputs except empty. ( I didn’t even see the restriction for this assignment though) So if validating to see if the input is not empty, the name should be changed. Otherwise, encoding it would just encode alphabetical characters and ignores the rest. <I interpret this requirement implies that Crypto should have 1+ alphabetic  : “cryptogram shall only encode alphabetic characters”>  <y2 Assignment 4 said must contains letters but this time it only said “cryptogram shall only encode alphabetic characters” it even suggests that it can include any characters.>


<i>James</i>
>* Some implementation detail leaked into diagram (database design, perhaps validateEncodedPhrase).

<i>Alex</i>  some methods placed under the wrong class (ex: addPlayer() should be under Player; addCryptogram() should be under cryptogram, etc.;  design-information document does not structurally address each detail in corresponding point (requiring reader to remember what was described in previous points, and also to look-up details in the UML design diagram); addPlayer() should be under Player class and should have listed needed inputs as addPlayer(userName, firstName, lastName)
 
 
**Design 3.**
Design done by ymiyamoto3.

![design3](../Design-Individual/ymiyamoto3/design-yoshi.png "ymiyamoto3")

Pros:

<i>Yoshi</i>
>* Having Player Ratings to separate attributes from Player class for easy maintenance.
>* A class User not extended by Administrator and Player so that if any authentication comes into play, it's easier to add in to the User without affecting those other classes.

<i>James</i>
>* The requirements are well-covered.
>* The persistence layer is represented.
>* Good use of classes to define structures required for application.

<i>Alex</i>
I like isAdmin:bool and how it is used in login(isAdmin) method
Application of Factory design pattern for Rolefactory 
Use of interface Role to have common attributes for Admins and Players  - This is really bad!!
I like PlayerRating singleton class. Though not sure why those attributes can't be under Player class ?


Cons:

<i>Yoshi</i>
>* I haven't included cardinalities.
>* Missing an association between ExternalWebService and database
>* Having a class Player Rating in singleton which will be shared among all other players!

<i>James</i>
>* Does not specify cardinality of relationships.
>* Player rating should not be a singleton.
>* Some of the methods defined on Player probably belong elsewhere (perhaps an association class or the web service).
 
 <i>Alex</i> 
In whole UML class diagram: attributes should be private (Encapsulation).
 
req 2, player as a user should be able to do . . ., not Player as class, so I think those operations do not belong with class Player, but with corresponding classes.
 
req 3, md tates "added to design class Admin with attrib Cryptogram", this attribute is not there, and if it was there I think it does not belong there.
I also do not agree with use of Composite (filled diamond) relationship between Admin and Crypto. It is true that Admin creates Crypto, but lifespan of Crypto is not bound by Admin's lifespan. With Composite, when creator destroyed, its creations destroyed too.
Probably addCrypto() and addPlayer() should belong to Crypto and Player classes correspondingly.
 
req 4, not sure if we should show DataBase as  database, probably better stick to classes (since this is Class diagram). Class diagrams do not show interaction with databases, since databases aren't classes.
 
req 7, I think method validateEncodedPhrase() needed under Cryptogram class.
 
req 9, I believe Cryptogram manipulation methods belong under Cryptogram class,
also we could use 'professional' naming conventions , 
meaning, instead of 'enter', 'save' use 'set', as long as we clearly state in 
the description what method does as relates to requirements. Though not sure if this is that important in this assignment.
 
req 10 methods to manipulate Cryptograms should be under Cryptogram class,
having sortPlayerRatings() method is too literal interpretation of the requirements.  Sorting is an implementation detail (though I've listed Sorter utility class ).
 
req 11 attribute UID probably be better named cryptogramID, and be better off having data type of int (though in office hours they said String is OK). 
I do not see how to justify having String ID here.
attribute numOfIncorrect does not belong with Cryptogram class, should be in association class Player-Cryptogram, as it is specific to particular player trying particular cryptogram.
 
req 12 Why attributes of PlayerRating class can't be under Player class ?
 
req 13, I agree that it does not affect design. Can we improve on it? maybe:
One of the Android UI guidelines is that app should be scalable across various screen sizes.  Maybe we could add some kind of ScreenSizeHandler class that would change UI depending on the 'real estate' available. For example, 
if more screen available, show more options (or adds?).
As far as responsive, we could have class DeviceMotionHandler with  feature : "if user shakes the device, reset previously entered solution".
These last suggestions are from "nice to have" and would be attempted if we were done with other "mast have" parts of app.
In other classes I had, these creative 'extra' features were appreciated, and sometimes required.


Team Design.
---
![team design](./design-team.png "team design")

### Commonalities and differences between team design and individual ones.
* Team design has more granularity than design of James.  
* It it similar to design suggested by Alex, but has more details (for example CryptoState enumeration, getCryptogram() was removed from Cryptogram because this requirement already addressed by method of DataService. Some methods were added or moved to different class (for example seeSolved() from PlayerCryptogram to Player).  
* It does not utilize RoleFactory class, as was in Yoshi’s design, because it added unnecessary complexity. It does not use PlayerRating singleton class because it incorrectly limited all player ratings to one shared single class.
### Main design decisions:
* We kept PlayerCryptogram association class as it is properly reflects the attributes and methods common to a particular combination of Player and Cryptogram.
* We’ve renamed DataBase class to DataService to avoid the confusion of having Data Base in the UML diagram.
* We added an enumeration (CryptoState) to better distinguish the status of the PlayerCryptogram.
 
Summary.
---
In the course of working on this deliverable we’ve learned importance of clear and timely communication. In our discussions of designs we gained the clarity of UML usage (for example that Composition relationship implies that lifespan of ‘child’ class depends on the lifespan of the ‘parent’ class).  
We’ve also learned the value and convenience of the good Version Control System (like GitHub), and also shared documentation tools (like google-docs and lucidcharts.com).  
We’ve learned that there are mutliple ways to approach a design, each with pros/cons and tradeoffs that may or may not be acceptable.  
We’ve learned that teams often produce better results than individuals as long as the team is not too large.
We've learned that good knowledge of OOP is very helpful to design software.

