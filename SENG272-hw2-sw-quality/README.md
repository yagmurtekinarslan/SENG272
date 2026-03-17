# SENG272 - HW2  
## Software Quality Evaluation (ISO/IEC 25010 & 25023)

This project implements a software quality evaluation system based on ISO/IEC 25010 and ISO/IEC 25023 standards.

---

## Overview

The system evaluates software quality using:

- Quality dimensions  
- Metrics  
- Weighted scoring  
- Gap analysis  

It calculates:
- Metric scores  
- Dimension scores  
- Overall quality score  
- Primary improvement area  

---

## Structure

- Criterion → metric  
- QualityDimension → quality characteristic  
- SWSystem → system  
- SWSystemData → test data  
- Main → runs evaluation  

---

## ISO/IEC 25023 Metrics Used

| Dimension | Metric | Direction | Unit |
|----------|--------|----------|------|
| Functional Suitability | Functional Completeness Ratio | Higher | % |
| Functional Suitability | Functional Correctness Ratio | Higher | % |
| Reliability | Availability Ratio | Higher | % |
| Reliability | Defect Density | Lower | defect/KLOC |
| Performance Efficiency | Response Time | Lower | ms |
| Performance Efficiency | CPU Utilisation Ratio | Lower | % |
| Maintainability | Test Coverage Ratio | Higher | % |
| Maintainability | Cyclomatic Complexity | Lower | score |

---

## Gap Analysis

The lowest scoring dimension is reported as the  
Primary Improvement Area.

---

## Run

Run Main.java to see the evaluation report.
