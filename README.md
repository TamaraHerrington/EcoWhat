<h1> Welcome to EcoWhat? - a full stack project for the Bright Network Technology Academy. </h1>


<h3>Introduction</h3>


<h3>About the project</h3>
<hr>
Using React

<h4>Features</h4>
<ul>
  <li><strong>Encrypted passwords</strong> for secure user login</li>
  <li><strong>Find your constituency</strong> through our postcode search or <strong>interactive map</strong> that redirects you to the matching constituency page</li>
  <li><strong>Comments</strong> for logged in users, which can be upvoted or downvoted and are censored for inappropriate language</li>
  <li><strong>Declarative component routing</strong> using React Router to create several 'pages'</li>
  <li><strong>Up-to-date data</strong> from the government's MP and votes APIs</li>
  <li><strong>Tweet or email your MP</strong> from their constituency page</li>
</ul>

<h3>How to Set Up and Use</h3>
<hr>
<h4>Setup</h4>
First, clone or download this repository. 
The Spring Boot backend requires a local PostgreSQL database. If you do not have Psql set up on your machine, install it! This website provides a great walkthrough, just make sure to keep a note of your Psql username and password: https://www.prisma.io/dataguide/postgresql/setting-up-a-local-postgresql-database  

Then, create a local PostgreSQL database called ecowhat. You will need to update the application.properties file in the server side of the repository, changing the username and password to your Psql username and password. This file can be found in the /server/proj/proj/src/main/resources folder. You may also need to connect the Spring application to your local ecowhat database.  
After this, you should be able to run the Spring application by running the main method in the ProjApplication.java file, which can found in the /server/proj/proj/src/main/java/com/capston/proj folder. Or, you may be able to run the project as a whole from your IDE. It may take a few seconds for the application to fully start up.

To set up the React portion of the application, navigate to the client/my-app directory and run ```$ npm install``` to install all the required dependencies. Then, use ```$ npm start``` to start the application, on port 3000 by default. This will automatically open the website or you can go to http://localhost:3000/ to access it.

<h4>Use</h4>
Sign up by clicking on Sign Up from the navigation bar, and submitting a completed signup form. Once you have successfully created an account, you will be redirected to the Log In page which you can also access from the navbar.  
As a logged in user, you can then access all the features of the website. The extra functionality for logged in users includes the ability to leave coments and a Profile page which shows you your details and comment history.
The About page provide information on the background of the project, and the What You Can Do page provides resources for offline actions that individuals can take to help protect the environment.
Most of the content is on the constituency pages, which can be accessed either by selecting a consituency through the map, or submitting a postcode through the search bar. Users can then toggle a list of of climate related votes the MP for that constituency has taken, as well as send them a tweet or email asking them to do more for the environment. Each constituency has a comments section, where logged in users can post comments and upvote or downvote them.  


<h4>Team:</h4>
<ul>
  <li>Helena</li>
   <li>Chaam</li>
   <li>Vinh</li>
   <li>Jonathan</li>
   <li>Tamara</li>
</ul>
