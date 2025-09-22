## Capstone proposal

Project Proposal: Simplified Batch Job Creation and Management Platform

Problem statement:
In the current system, creating and managing batch jobs is cumbersome and error-prone. To introduce a new job, many steps are required:
- Insert database entries for job definition
- Create new java class for logic
- Update uitiity classes and property files
- Configure function to interact with Jasper files
- Design jrxml templates
- Link components together manually

This multi-step process is time-consuming, repetitive and prone to errors. While a 'View Batch Job' page currently exists, it provides limited visibility. The report scheduler I have been working on does not even have a page to view status. When the batch job is ran, it says 'Selected Job submitted successfully' regardless of whether the batch job fails. I have to manually go to the terminal console to see if there are any errors as there is no feed back or alerts when the batch job is complete or failed.

Objectives:
1. Simplify Job Creation (Core focus):
    - Provide a UI wizard to define new jobs without manual database edits or multiple config changes
    - Store job definitions, parameters, and configurations in a centralized registry
    - Offer reusable templates as batch jobs could be similar in nature (e.g. report generation, CSV export, email notification)
