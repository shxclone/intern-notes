# Log Period: Trimester 1 / Week 3 & 4

## Day 11 (15/09/25)
* Wrapped up the CR on splitting the batch job and push up to GitLab

## Day 12 (16/09/25)
* Started on the first CR - creating 4 batch jobs to generate reports for different levels
* Completed the CR and moved onto another CR which is similar to the first
* Wrote the SQL queries used to insert batch jobs into the database so DBA on MOE side can replicate

## Day 13 (17/09/25)
* Wrapping up the CR, wrote the test case for UAT
* Fixed some minor details pertaining to the last 2 CRs
* Faced issue - when user is trying to download a report, it should give the report pertaining to the user but instead it gives the consolidated report

## Day 14(18/09/25) 
* Continue working on the issue faced yesterday
* Fixed the issue, wrapped up the CR and pushed up to GitLab
    * Struggled for a few hours on this as I thought that saving a file will automatically rebuild the code
    * Turns out that is only true for server side files
    * Need to manually recompile using GWT for client side files
* Updated the test case to ensure checking that the user gets the correct report
* Had the first meeting with both Academic and Industry Supervisor, Prof. Jacob Abraham and Francis Teo respectively
* Joined and listened in on a meeting - Sec/JC Briefing on 2025 Edusave Awards Selection Exercise and iEASA
    * Jia Jun presented on Overview of iEASA
        * Went through functional flow and function
    * Gave a demo on the iEASA system
* Was assigned another CR to work on another batch job which I do not quite understand, need further clarrification but Jia Jun is not around. May have to do so tomorrow

## Day 15(19/09/25)
* Clarrified with Jia Jun on the details of the CR. Turns out it is also similar to the previous few CRs I had done
* Completed the CR, pending review from Jia Jun
* Wrote the SQL queries used to insert batch jobs for replication
* Wrote the test case for UAT
* Was told that report generated had to be filtered by student_type
* I previously just removed the filter as I thought it was not needed, now have to put it back
* Had to generate some dummy data to test whether the filter works 
* Update UAT to check for additional filter

## Day 16 (22/09/25)
* Spent most of the day focusing on Capstone 
* Add on to previous CR to create new batch jobs for a different student type
* Fixed spelling error on generated report 
* Fixed report type erorr causing the report to appear under the wrong user account

## Day 17 (23/09/25)
* Lesson by Francis on Load Balancing
    * Played around with Locust
* Continue brain-storming about Capstone idea
* Had to continue working on previous CR due to changes in requirement - previously had the report generated in excel using jasper but new requirement needs the reports in txt and there are several reports to be generated (Follow what was previously done)

## Day 18 (24/09/25)
* Continued working on the previous CR as was informed of changes required quite late yesterday, so unable to finish
* Even more changes in requirement today - now need the same batch job but for every student type
* Only converted 4 batch jobs yesterday, need to convert 4 more for Poly students
* Need to create 8 more batch jobs for ITE batch 1 and ITE batch 2

## Day 19 (25/09/25)
* Continued working on the changes presented yesterday
* Finished converting all the batch job to meet the changed requirements, tested in SIT and pushed to Git 

## Day 20 (26/09/25)
* Back to Campus for workshop