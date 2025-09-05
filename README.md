# IWSP Journal

## Day 1:
* Onboarding done by Vienna (HR) on what WizVision is about
* Collection of work laptop and set up access by IT
* Francis (CEO) came to talk to us about internship experience and expectations - 3As: Attitute, Aptitude, Adaptability
* Installation of softwares required for work
* Introduction to Tomcat mini lab - deployed a WebApp using .war file
* Went through mini lab on Java & MySQL

## Day 2
* Micro training overview done by Bernie & Chloe
  * Went through a list of softwares we might use
  * Mentioned that company can sponsor further studies/certifications
    * AWS Certification
    * Professional Scrum Master I (scrum.org)
  * Shared on their capstone experience
* Need to start brainstorming ideas for capstone
* Wrote a simple Java email program as requested by Francis
  * Used the package Jakarta Mail to get the email sent out
  * Had to generate app password on Gmail
  * Used SMTP (Simple Mail Transfer Protocol)
  * Port 587 for TLS (Transport Layer Security); Port 465 for SSL (Secure Sockets Layer)
* Refresher on Git for the crash course tomorrow

 ## Day 3
 * Training on support - handling customers by Jia Jun (Team Lead) & Yee Tang (PM)
   * Level 1 - Basic support: Ticket logging, password reset, help desk stuff
   * Level 2 - Intermediate support: Troubleshoot data, Configuration issues, Apply fixes that do not require code changes
   * Level 3 - Advanced support: Code-level issue resolutions and enhancements
   * Escalate up the levels if unable to resolve (Escalation is NOT a failure)
   * Learned about response times and how they're linked to the severity of the situation: the higher the severity, the lower the response time
   * Usually during code fixes, as developers we should test it in System Integration Testing (SIT). After testing and the issue is fixed, code changes are pushed to User Acceptance Testing (UAT) for the client to make sure the program is in accordance to their requirements. Once approved, the changes will be pushed to production.
* Training on Git Lab done by Bernie & Chloe
   * Went through basic commands such as:
       * git checkout - change branch
       * git merge - merge branches
   * Go through proper sequencing - Pull from main before you make any changes on your own branch as main branch should always be the latest working version
* Introduced to SHIP-HATS - CI/CD used by Singapore Government Tech Stack

## Day 4
* Training on Java by Seng Yong
  * Gave us a run through of the code base used for HDB Place2Lease - uses MVC structure, Angular for front end, SpringBoot
  * Practiced on a Java mini lab - Worked on a Servlet app, with DB connections to MySQL, hosted by Tomcat9 to display an image of the school, with our names pulled from the database displayed below it
  * Learned how to use Servlet as I have never used it before
* Francis sharing:
  * Before you declare your work done, test beyond your computer
  * As a Software Engineer, everything can be done. It is just whether the amount of money is right, how long it takes, and it is worth it do attempt
  * Change Tomcat port - under Tomcat 9.0 > conf > server.xml
  * .war file is where the program becomes portable
  * Production should only have JRE (Java Run Time Environment)  and should not have JDK (Java Development Kit) - should not be able to write code in production
* Went through a mandatory IT Security Awareness training session to ensure everyone understands the importance of protecting the company’s data and maintaining strong cybersecurity practices
  * Watched a Security Awareness Training video
  * Completed a questionnaire to test our understanding - 90% to pass

## Day 5
* Francis shared on how to map network drive:
  * Right click This PC > Map Network Drive
  * Using CLI:
    * net use - see what connections you currently have
    * net use <Drive(e.g. P:)> /del - delete a drive mapping
    * net use  <Drive (e.g. P: \\10.0.0.106\projects)> - add a mapping
* Went through Tech Talk #1 on Docker done by Francis
* Went through the mini lab on Docker following the Tech Talk by Francis
  * Practiced docker commands to pull image, run container, login and clean up:
    * login.bat - bash file set up to handling logging in
    * docker-clean.bat - bash file set up to clean up images and containers
    * docker pull <image> - pull image from Docker Hub
    * docker run -p <external-port>:<internal-port> --name <image name> - run docker image
    * docker tag <image> <new image tag> - create a new tag pointing to the same image
    * docker push <image> - push image to Docker Hub
    * docker build -t <container name> . - build all the files in current directory
    * docker run --rm <contianer name> - runs the container and automatically removes the container when it exits
  * Set up Dockerfile, python file and text file containing requirements
  * Ran a simple python program on docker that outputs Hello World and the current time in Singapore timezone
  * Ran a web based api chat app python program
* 
