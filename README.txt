ENSF409 Final Project Milestone 3
Niyousha Raeesinejad - niyousha.raeesinejad@ucalgary.ca
Dunsin Shitta-Bey - oluwadunsin.shittabe@ucalgary.ca

Arrangement of project

Final Project -->
		src -->
			clientController (Package)			   			
			clientView (Package)		
			serverController(Package)	
			serverModel (Package) 		
				  	    
To run this Student Course Registration Program, please go through the following sequence:

1] Extract the Final Project from the zip folder onto an IDE (preferably eclipse)

2] Run ServerCommsController.java from the serverController package

3] Then run the GUIController.java file from the clientController package

4] Follow the instructions on the GUI

5] Before running ServerCommsController again, please comment out the init() method in the second line inside the constructor of   	    DBController() because the mySQL tables would have been created at the first run of the program

Bonus Features Implemented:
1. Deployed project; running the server and client on separate machines
2. Maintained a list of Student users (with pre-set names and id numbers) and developed a login/out feature for both Admin and Student
3. Created a separate GUI for an “Admin” with the functionality of creating new courses 
