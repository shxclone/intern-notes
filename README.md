# IWSP Internship Journal Repository

## Overview
This repository contains a comprehensive daily journal of my Industrial Work-Study Programme (IWSP) experience at WizVision, documenting technical learning, professional development, and project contributions.

## Repository Structure

### Journal Files
- **`Capstone BiWeekly Submission`** - Submission of Capstone BiWeekly Logs using template provided
- **`Capstone Logs`** - Daily journal of what has been done for Capstone
- **`IWSP BiWeekly Submission`** - Submission of IWSP BiWeekly Logs using template provided
- **`IWSP Logs`** - Daily journal of what has been done for IWSP
- **`Capstone_Project`** - Project app directory
- **`README.md`** - This overview document

### Future Structure
```
├── Capstone BiWeekly Submission
│   ├── Capstone Biweekly Logs Week 1 & 2.docx
│   ├── Capstone Biweekly Logs Week 1 & 2.pdf
│   └── ...
├── Capstone Logs
│   ├── tri-1-week-1-and-2.md
│   ├── tri-1-week-3-and-4.md
│   └── ...
├── IWSP BiWeekly Submission
│   ├── IWSP Biweekly Logs Week 1 & 2.docx
│   ├── IWSP Biweekly Logs Week 1 & 2.pdf
│   └── ...
├── IWSP Logs
│   ├── tri-1-week-1-and-2.md
│   ├── tri-1-week-3-and-4.md
│   └── ...
├── Capstone_Project
│   ├── ...
│   ├── ...
│   └── ...
└── README.md
```

## Capstone Deliverables
- **System Design & Architecture**
    - 29th September 2025 – 2nd November 2025
    - Define architecture, technology stack, and integration approach.
- **Job Execution Tracking & History Logging**
    - 3rd November 2025 – 7th December 2025
    - Develop core tracking features to capture job states, runtimes, and history.

- **Rule-Based Governance Engine**
    - 8th December 2025 – 11th January 2026
    - Implement configurable rules such as runtime thresholds and inactivity detection.

- **Redundancy & Obsolescence Flagging**
    - 12th January 2026 – 25th January 2026
    - Build features to identify outdated, redundant, or inactive jobs.

- **Automated Alerting System**
    - 26th January 2026 – 15th February 2026
    - Implement notifications for jobs breaching configured rules.

- **User Dashboard**
    - 16th February 2026 – 8th March 2026
    - Develop an interactive dashboard to visualize job states, and governance insights.

- **System Testing & Validation**
    - 9th March 2026 – 18th March 2026
    - Conduct functional testing, rules validation, and administrative review

### Notes/Future considerations
- Global error handler / retry policy
- Cleanup/archiving policy for old job_execution_history rows (so the table doesn't grow endlessly)
- Indexing for faster look up
- How to resolve jobs that depend on a gateway or with external parties - we have no jurisdiction over their system
- Machine learning for anomaly detection in job behaviour
- Role-based access control

## Program Details
- **Company**: WizVision
- **Duration**: 7 months and 9 days
- **Focus Areas**: Software development, cloud technologies, government systems
- **Team Assignment**: Java Development Team - MOE iEASA and NSG