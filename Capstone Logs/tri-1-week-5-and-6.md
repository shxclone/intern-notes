# Log Period: Trimester 1 / Week 5 & 6

## Day 21 (29/09/25)
* Set up the environment:
    * Set up Spring Boot for back end 
    * Set up Vue.js for front end 
    * Included Bootstrap in the setup just in case I need to use it down the line
    * Set up PostGres running on docker for database with tables managed by Flyway migrations

## Day 22 (30/09/25)
* Added Quartz dependency and integrated it with Spring Boot
* Set up a dummy job for Quartz to run

## Day 23 (01/10/25)
* Set up services to update table as job runs
* Set up abstract class BaseQuartzJob for reusability
* Set up Quartz to read database upon start up and schedule batch jobs based on CRON expression
* Ensure batch jobs are scheduled and run when it is the scheduled time 

## Day 24(02/10/25) 
* Setup backend logic so that users can run/edit batchjob
* Starting to set up frontend for job execution and history tracking

## Day 25(03/10/25)
* Setup frontend for jobs page to display jobs table with 'Run Now' and 'Edit' actions
* Edit to pop up a window, pulling current data from database and display as default value
* Table should refresh and display new data when saved
* Run now to trigger job straight away and pop up status feedback ("success or failure")
* Faced some bugs where edit pop up was not showing up

## Day 26 (06/10/25)
* Fixed bug on edit pop up
* Added on to edit function to ensure changes to CRON expression is translated to the next trigger date
* History table to display the last 100 jobs with most recent jobs at the top


## Day 27 (07/10/25)


## Day 28 (08/10/25)


## Day 29 (09/10/25)


## Day 30 (10/10/25)