			            Food for the needy app 

Description:
			The app is designed to provide a platform for redistribution of excess food and leftovers in households and different functions. Once the user has signed up successfully, he/she can donate food by informing the concerned team through the app, which automatically recognizes the location. Generous people can also donate money through any kind of upi used and we keep a track of each and every activity of the user with the help of a database.

Splash Screen:
		The app  opens up with a designed splash screen which portrays the major motive of the app at a single glance. It was designed with a display time of five seconds and an attractive look with the help of animations. The splash screen after its completion directs the user to the login page.

Splash_main.xml for the layout design;
bottom_anim, side_anim, top_anim for animations; Splashscreen.java for the page implementation.

Login Page:
		An existing user can login using his email address which is authenticated and the individual password. New users can click on the sign in option to register for the app. The user can authorize the local storage whether to remember the credentials or not. In case if the user has forgotten his password, he/she can click on the forgot password option, a link will be sent to the particular authenticated email address and the user can set his new password.

ac_login.xml, layout_login.xml for the layout designs;
Login.java for the page implementation.

Register Page:
		A new user can register with his valid email address, phone number, password and username. A verification link will be sent to the email address the user has registered with. A user cannot login into the app until his email address has been verified. On successful registration the user is redirected to the login page. An existing user can also navigate to the login page. 

ac_register.xml, layout_register.xml for the layout designs; 
Register.java for the page implementation. 

Dashboard Page:
		On successful login the user is redirected to the dashboard page. The page is filled with the present statistics of how people around the globe are suffering due to lack of food and how important it is to donate the excess food one has to the needy people. It also includes the targets of Zero Hunger and their necessity in achieving zero hunger. The page has a side navigation bar which we can use to navigate to donate food, donate money, contact us, about us and profile. Users can log out if there is some kind of problem. 

activity_dashboard.xml, featured_design.xml, featured_design2.xml, main_menu.xml, menu_header.xml for the layout design;
FeaturedHelperClass.java, FeaturedAdapter.java, FeaturedHelperClass2.java, featuredAdapter2.java, dashboard.java for the page implementation.

Donate Food Page :
		Users can donate food after filling the food details, quantity, date and time of preparation. The major accomplishment of the application is it can automatically recognize the user location using GPS. Once the person has successfully submitted the details, the data is stored in the database and food details are printed for the sake of the user to view and after thanking the user it is directed back to the dashboard page.

activity_donatefood.xml, activity_print_details.xml, activity_thankyou.xml for layout designs;
Donatefood.java, Print_details.java, Thankyou.java for the page implementations.

Donate Money Page:
		People who wish to help the needy by donating money can do it with the help of bank details provided by the app or using upi payment method.

activity_money_upi.xml, activity_donate_money.xml, ac_money_upi.xml for the layout design;
Donate_money.java, MoneyUpi.java for the page implementations.



About Us Page:
		The page provides the description of the app and also why one needs to care for the other’s hunger. People can also reach out to us through email.

activity_about.xml for layout design;
About.java for the page implementation.

Contact Us Page:
		The page contains the contact details of the app owner, who can be contacted for any queries related to food donation. 

activity_conatct.xml for layout design;
Contact.java for the page implementation.

Profile Page:
	         The profile displays the user details namely the username, email address, phone number and keeps a track of all the food donations made by the user.

ac_profile.xml for layout design;
profile.java for the page implementation.


		
		 
			

