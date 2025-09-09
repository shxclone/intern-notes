# IWSP Journal

## Day 1 (01/09/25)
* Onboarding done by Vienna (HR) on what WizVision is about
* Collection of work laptop and set up access by IT
* Francis (CEO) came to talk to us about internship experience and expectations - 3As: Attitute, Aptitude, Adaptability
* Installation of softwares required for work
* Introduction to Tomcat mini lab - deployed a WebApp using .war file
* Went through mini lab on Java & MySQL

## Day 2 (02/09/25)
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

 ## Day 3 (03/09/25)
 * Training on support - handling customers by Jia Jun (Team Lead) & Yee Tang (PM)
   * Level 1 - Basic support: Ticket logging, password reset, help desk stuff
   * Level 2 - Intermediate support: Troubleshoot data, Configuration issues, Apply fixes that do not require code changes
   * Level 3 - Advanced support: Code-level issue resolutions and enhancements
   * Escalate up the levels if unable to resolve (Escalation is NOT a failure)
   * Learned about response times and how they're linked to the severity of the situation: the higher the severity, the lower the response time
   * Usually during code fixes, as developers we should test it in System Integration Testing (SIT). After testing and the issue is fixed, code changes are pushed to User Acceptance Testing (UAT) for the client to make sure the program is in accordance to their requirements. Once approved, the changes will be pushed to production.
* Training on Git Lab done by Bernie & Chloe
   * Went through basic commands such as:
       * `git checkout` - change branch
       * `git merge` - merge branches
   * Go through proper sequencing - Pull from main before you make any changes on your own branch as main branch should always be the latest working version
* Introduced to SHIP-HATS - CI/CD used by Singapore Government Tech Stack

## Day 4 (04/09/25)
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

## Day 5 (05/09/25)
* Francis shared on how to map network drive:
  * Right click This PC > Map Network Drive
  * Using CLI:
    * `net use` - see what connections you currently have
    * `net use` <Drive(e.g. P:)> /del - delete a drive mapping
    * `net use`  <Drive (e.g. P: \\10.0.0.106\projects)> - add a mapping
* Went through Tech Talk #1 on Docker done by Francis
* Went through the mini lab on Docker following the Tech Talk by Francis
  * Practiced docker commands to pull image, run container, login and clean up:
    * `login.bat` - bash file set up to handling logging in
    * `docker-clean.bat` - bash file set up to clean up images and containers
    * `docker pull <image>` - pull image from Docker Hub
    * `docker run -p <external-port>:<internal-port> --name <image name>` - run docker image
    * `docker tag <image> <new image tag>` - create a new tag pointing to the same image
    * `docker push <image>` - push image to Docker Hub
    * `docker build -t <container name> .` - build all the files in current directory
    * `docker run --rm <contianer name>` - runs the container and automatically removes the container when it exits
  * Set up Dockerfile, python file and text file containing requirements
  * Ran a simple python program on docker that outputs Hello World and the current time in Singapore timezone
  * Ran a web based api chat app python program
* Went through a lesson on Solution Architecture & Micro-services done by Kwan Jin (Chief Solutions Architect)
  * How to practice solutions architecture
  * Capacity planning
  * Must be able to scale and easy to maintain
  * Code modularity
  * Componenets of a solution architecture
    * Application
    * Data
    * Infrastructure
    * Integration
  * Impact areas if architecture is not well designed:
    * Scalability
    * Security
    * Performance
    * Maintainability
  * Test driven design methodology
  * Automated testing, to ensure results are consistent even after code change
  * Leverage on things like micro-services
    * Promotes modularity
    * API communication
    * Fault isolation
    * Technology diversity - each service can have their own technology stack
    * Independent services
  * Why standards matter:
    * Consistency
    * Seamless interoperability
    * Compliance - Security & Regulations
    * Avoid unpredictability - Achieve reliabilty
    * Easy to integrate new people
  * Best Practices:
    * Follow design patterns - makes code easier to understand (MVC, MVVM)

## Day 6 (08/09/25)
* Training on .NET/C# by Yan Long
  * .NET 5 and above is .NET Core; Below that is .NET Framework (Legacy)
  * .NET 9 is the latest stable version, so that's what we will be using for now
  * Windows Server is more expensive than others such as Linux/MacOS as they include the Windows License in the price
  * Web Forms:
    * Event-driven
    * Drag and drop development
    * Requires ViewState, PostBack, IIS
    * Not available for .NET Core
    * Lifecycle includes Page_Init, Page_Load, PostBack, etc
    * Harder to maintain and scale
    * Legacy, visual and tightly coupled
  * Razor View Engine (MVC)
    * Uses HTML and C#
    * Uses @model, @html, loops, conditions inside views
    * Clean separation from controller logic
  * MVC (Framework) - At this point should only be used to maintain legacy apps, not recommended for new projects
  * MVC (Core) - Use for new applications
  * Tech Refresh refers to the updating of softwares to newer technologies - E.g. .NET Framework to .NET Core, implemented with maybe Docker, AI, etc
  * Praticed on C# mini lab:
    * Worked on a 3 tier app using .NET Core to display an image of the school, with our names pulled from the database displayed below it
    * Worked on a email sender program using C#
  * Practiced on Python - did the same 3 tier app and email sender in Python

## Day 7 (09/09/25)
* .NET Framework with Live Demo by Daphne & Han Zheng
  * Goverment on Commercial Cloud (GCC) - used to facilitate secure and efficient cloud services
  * Went through Singapore Memories architecture
  * Went through Singapore Memories site demo & source code
  * Did a mini 3 question quiz to test out understanding
  * Went through deployment to staging:
    * After changes are made and published, the changed file is copied over to NLB issued thumbdrive
    * The thumbdrive is then connected to the NLB issued laaptop to extract the changed file
    * Once extracted, FileZilla is used to upload the chnaged file onto the file server
    * Access CyberArk to access the server and use winSCP to extract the file out of the file server
    * Copy the original file into a back up folder in case it requires roll back
    * Once backup is saved, the new file can be changed to deployment
  * Need to know how to read application logs
  * Many applications are data driven
  * Knowing how to use triggers in sql can be useful as database will definitely be faster than code logic
  * Oobee (previously known as Purple HATS or Purple A11y) is a open source accessibility tool that helps to identify accessibility issues in websites
    * Has screen reader that reads aloud the contents displayed on a screen
    * Clickable elements needs to have accessible labels so that screen readers are able to convey the purpose of the element
  * Burp Suite is a tool used for penetration testing of web applicaitons for vulnerabilities
    * Has a proxy function that can intercept packets - HTTP request and response
    * Shows the importance of server validations as man in the middle is able to bypass the frontend validation and send in scripts 
  * Google Lighthouse is an extention tool to audit performance, accessibility and best practices of a web page
    * Categories include:
      * Performance
      * Accessibility
      * SEO
      * Best Practices
    * According to Government benchmark, performance needs to be a minimum of 90%; page should not take more than 3 seconds to load
  * These tools are usually used to replicate issues brought up by 3rd party vendors that are hired to do penetration testing
  * Once the issues are deemed resolved, these tools are used again to verify if the issue is truly resolved
  * Francis came in to share about Microsoft SQL
