
<table>
<tr><th><img src="https://avatars.githubusercontent.com/u/5073958?v=4" alt="Shelson Ferrari's GitHub Profile Picture" width="100" height="100"></th>
<th align="left"><p><h1>SHELSON FERRARI</h1></p>
<p><strong>Curriculum Vitae - English Version</strong></p>
<ul><li><a href="./README_pt_br.md">Portuguese version - pt_br</a></li><li><a href="./README_es.md">Spanish version - es</a></li></ul>
</th>
</tr>
</table>

## Software Engineer | Cloud Architect

> 47 yo, single, Brazilian - São Paulo, Brazil

## Contacts

- [LinkedIn](https://www.linkedin.com/in/shelson/)
- [WhatsApp](https://wa.me/5511916257345)
- [Email](mailto:shelson@gmail.com)
- [GitHub](https://github.com/33mestre)

---

## Professional Summary

**Certified Professional (70+ certifications) with 23 years of experience** in designing and orchestrating creative, efficient, and effective business solutions through technology. Skilled in multi-cloud solution architecture, full-stack software engineering, and cognitive modern interfaces.

```java
/*
 * Copyright (c) 2024, Shelson Ferrari
 *
 * Licensed under the MIT License and the Apache License, Version 2.0 (the "Licenses"); you may not use this file except in
 * compliance with the Licenses. You may obtain a copy of the Licenses at
 *
 * MIT License:
 * https://opensource.org/licenses/MIT
 *
 * Apache License, Version 2.0:
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the Licenses é
 * distribuído em uma base "COMO ESTÁ", SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, expressas ou implícitas. Veja
 * os Licenses para a linguagem específica que rege permissões e limitações sob os Licenses.
 */

package com.shelson.application.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelson.application.dto.CurrencyConversionDTO;
import com.shelson.domain.model.Currency;
import com.shelson.domain.model.CurrencyConversion;
import com.shelson.domain.repository.CurrencyConversionRepository;
import com.shelson.infrastructure.exception.BusinessException;
import com.shelson.infrastructure.exception.ResourceNotFoundException;

/**
 * Camel route for handling currency conversion requests.
 * This route takes the source currency, target currency, and amount as headers,
 * processes the conversion using the {@link CurrencyConversionService}, and returns the conversion details.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see com.shelson.application.dto.CurrencyConversionDTO
 * @see com.shelson.application.service.CurrencyConversionService
 * @see com.shelson.application.processors.ExchangeRateProcessor
 * @see org.apache.camel.ProducerTemplate
 * @see com.shelson.domain.model.Currency
 */
@Service
public class CurrencyConversionService {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CurrencyConversionRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts the given amount from the source currency to the target currency.
     *
     * @param sourceCurrency The source {@link Currency} to convert from.
     * @param targetCurrency The target {@link Currency} to convert to.
     * @param amount The amount to be converted.
     * @return A {@link CurrencyConversionDTO} containing the conversion details.
     * @throws BusinessException if sourceCurrency or targetCurrency is null, or if the amount is less than or equal to zero.
     * @throws ResourceNotFoundException if an error occurs while fetching the exchange rates.
     */
    public CurrencyConversionDTO convertCurrency(Currency sourceCurrency, Currency targetCurrency, double amount) {
        logger.info("Starting currency conversion: source={}, target={}, amount={}", sourceCurrency, targetCurrency, amount);

        if (sourceCurrency == null || targetCurrency == null) {
            logger.error("Source or target currency is null");
            throw new BusinessException("Source and target currencies must not be null");
        }
        if (amount <= 0) {
            logger.error("Invalid amount: {}", amount);
            throw new BusinessException("Amount must be greater than zero");
        }

        Map<String, Double> rates = null;
        try {
            Object response = producerTemplate.requestBodyAndHeader("direct:fetchRate", null, "sourceCurrency", sourceCurrency.getCode());
            if (response != null) {
                rates = objectMapper.convertValue(response, new TypeReference<Map<String, Double>>() {});
                logger.info("Fetched exchange rates: {}", rates);
            }
        } catch (Exception ex) {
            logger.error("Error fetching exchange rates from API: {}", ex.getMessage());
            throw new ResourceNotFoundException("Error fetching exchange rates from API", ex);
        }

        if (rates == null || !rates.containsKey(targetCurrency.getCode())) {
            logger.error("Invalid or missing target currency rate: {}", targetCurrency);
            throw new BusinessException("Invalid or missing target currency rate");
        }

        Double rate = rates.get(targetCurrency.getCode());
        double convertedAmount = amount * rate;
        CurrencyConversion conversion = new CurrencyConversion(sourceCurrency, targetCurrency, rate, LocalDateTime.now());
        repository.save(conversion);
        logger.info("Currency conversion saved: {}", conversion);

        CurrencyConversionDTO result = new CurrencyConversionDTO(sourceCurrency, targetCurrency, rate, LocalDateTime.now(), amount, convertedAmount);
        logger.info("Conversion result: {}", result);
        return result;
    }
}

```

---

## Specialization Certificates

- [AWS Cloud Technology Consultant](https://www.coursera.org/account/accomplishments/specialization/2YWFXBTBA5MG)
  - [Skills for Working as an AWS Cloud Consultant](https://www.coursera.org/account/accomplishments/verify/PF6B33LBTBA8)
  - [Introduction to Information Technology and AWS Cloud](https://www.coursera.org/account/accomplishments/records/5TN4TLHSPBDR)
  - [AWS Cloud Technical Essentials](https://www.coursera.org/account/accomplishments/records/CCBPJ8NQDTM2)
  - [Automation in the AWS Cloud](https://www.coursera.org/account/accomplishments/records/3B58RKKWTKW9)
  - [DevOps on AWS and Project Management](https://www.coursera.org/account/accomplishments/records/UKA8REGUTX93)
  - [Data Analytics and Databases on AWS](https://www.coursera.org/account/accomplishments/records/EW94SWR67646)
  - [Developing Applications in Python on AWS](https://www.coursera.org/account/accomplishments/records/3PVDR2CWFMR9)
  - [Capstone: Following the AWS Well Architected Framework](https://www.coursera.org/account/accomplishments/records/ADGU2ZJY8GS6)
  - [Providing Technical Support for AWS Workloads](https://www.coursera.org/account/accomplishments/records/TDXSDFCJEJTT)

- [AWS Cloud Solutions Architect](https://www.coursera.org/account/accomplishments/specialization/2H2TB7499JNH)
  - [AWS Cloud Technical Essentials](https://www.coursera.org/account/accomplishments/records/CCBPJ8NQDTM2)
  - [Architecting Solutions on AWS](https://www.coursera.org/account/accomplishments/records/RT5SM3G3NZBN)
  - [Introduction to Designing Data Lakes on AWS](https://www.coursera.org/account/accomplishments/records/R7N5KVCKYVWG)
  - [Exam Prep: AWS Certified Solutions Architect – Associate](https://www.coursera.org/account/accomplishments/records/PXX6L6XCGZQQ)

- [AWS Fundamentals](https://www.coursera.org/account/accomplishments/specialization/D2T6DDPUJ3XG)
  - [AWS Cloud Technical Essentials](https://www.coursera.org/account/accomplishments/records/CCBPJ8NQDTM2)
  - [Architecting Solutions on AWS](https://www.coursera.org/account/accomplishments/records/RT5SM3G3NZBN)
  - [Exam Prep: AWS Certified Solutions Architect – Associate](https://www.coursera.org/account/accomplishments/records/PXX6L6XCGZQQ)

- [IBM DevOps, Cloud, and Agile Foundations](https://www.coursera.org/account/accomplishments/specialization/FDWHBX6Z3YAQ)
  - [Introduction to DevOps](https://www.coursera.org/account/accomplishments/records/NZUT4V4YPLAH)
  - [Introduction to Cloud Computing](https://www.coursera.org/account/accomplishments/records/Q8876RHGVN49)
  - [Introduction to Agile Development and Scrum](https://www.coursera.org/account/accomplishments/records/SQ39N7GPVLW2)

- [IBM – Key Technologies for Business](https://www.coursera.org/account/accomplishments/specialization/QYMDWJG8GLRW)
  - [Introduction to Cloud Computing](https://www.coursera.org/account/accomplishments/records/Q8876RHGVN49)
  - [Introduction to Artificial Intelligence (AI)](https://www.coursera.org/account/accomplishments/records/98ZFARCDQM8R)
  - [What is Data Science?](https://www.coursera.org/account/accomplishments/records/SFWWHNK33DK7)

- **Hackerrank**
  - [Frontend Developer (React)](https://www.hackerrank.com/certificates/64f5d8b936cb)
  - [Software Engineer](https://www.hackerrank.com/certificates/c327146fcc2d)
  - [Software Engineer Intern](https://www.hackerrank.com/certificates/4d2de19fe1f7)

### Technical Certifications

- **Database / Data Engineering**
  - [Oracle Autonomous Database Administration](https://www.coursera.org/account/accomplishments/records/7QK7FELRHMDN)
  - [Oracle Database Foundations](https://www.coursera.org/account/accomplishments/records/8AZ7ZHGTPX8A)
  - [Introduction to NoSQL Databases](https://www.coursera.org/account/accomplishments/records/AXKP447EHTM2)
  - [SQL [Basic]](https://www.hackerrank.com/certificates/a8c9dfc06f73)
  - [SQL [Intermediate]](https://www.hackerrank.com/certificates/fc749108b8ec)
  - [SQL [Advanced]](https://www.hackerrank.com/certificates/8eaf18885155)
  - [Introduction to Data Engineering](https://www.coursera.org/account/accomplishments/records/P67LLVHGFSJW)

- **Full-Stack**
  - [Full Stack Application Development Capstone Project](https://www.coursera.org/account/accomplishments/records/7TMUTQCC2Q96)
  - [Django Application Development with SQL and Databases](https://www.coursera.org/account/accomplishments/records/UC37TVKJE4Q6)
  - [Hands-on Introduction to Linux Commands and Shell Scripting](https://www.coursera.org/account/accomplishments/records/QJKTR2UEKLDW)
  - [Introduction to Software Engineering](https://www.coursera.org/account/accomplishments/records/BXH8WCR7E5RR)
  - [Rest API [Intermediate]](https://www.hackerrank.com/certificates/2c49094a74fc)
  - [Problem Solving [Basic]](https://www.hackerrank.com/certificates/e2bbaea5334d)
  - [Problem Solving [Intermediate]](https://www.hackerrank.com/certificates/105fff34eeb8)
  - [Full Stack Software Developer Assessment](https://www.coursera.org/account/accomplishments/records/K64TNQ5LTCRB)
  - [Getting Started with Git and GitHub](https://www.coursera.org/account/accomplishments/records/TR4SR7C7WFXC)
  - [Software Developer Career Guide and Interview Preparation](https://www.coursera.org/account/accomplishments/records/A7XNMRXD7MJK)

- **Front-End**
  - [Angular [Basic]](https://www.hackerrank.com/certificates/fb8b541780f1)
  - [Angular [Intermediate]](https://www.hackerrank.com/certificates/f239eee2d523)
  - [Introduction to HTML, CSS, & JavaScript](https://www.coursera.org/account/accomplishments/records/9WUV74DA9J5M)
  - [Developing Front-End Apps with React](https://www.coursera.org/account/accomplishments/records/KNK5KCGHYWDP)
  - [CSS [Basic]](https://www.hackerrank.com/certificates/b77cae214086)
  - [React [Basic]](https://www.hackerrank.com/certificates/0f371b433017)
  - [Introduction to Web Development with HTML, CSS, JavaScript](https://www.coursera.org/account/accomplishments/records/LM2FPTGWSNFL)

- **DevOps**
  - [Get Started with Cloud Native, DevOps, Agile, and NoSQL](https://www.coursera.org/account/accomplishments/records/KF5J5FW627YE)
  - [Application Security for Developers and DevOps Professionals](https://www.coursera.org/account/accomplishments/records/YXEC576L69BH)
  - [Introduction to Containers w/ Docker, Kubernetes & OpenShift](https://www.coursera.org/account/accomplishments/records/N55XUKZJMNXK)

- **Back-End**
  - [Introduction to Back-End Development](https://www.coursera.org/account/accomplishments/records/SZG85L43M9YC)
  - [Developing AI Applications with Python and Flask](https://www.coursera.org/account/accomplishments/records/Q5FNL86VVQX4)
  - [Java [Basic]](https://www.hackerrank.com/certificates/86341b73f13b)
  - [JavaScript [Basic]](https://www.hackerrank.com/certificates/12bc6281d253)
  - [JavaScript [Intermediate]](https://www.hackerrank.com/certificates/96a9e19d87e9)
  - [Node.js [Basic]](https://www.hackerrank.com/certificates/79fa786e07bb)
  - [Node.js [Intermediate]](https://www.hackerrank.com/certificates/c1111002c37b)
  - [Go [Basic]](https://www.hackerrank.com/certificates/8d9feafd7ba9)
  - [Go [Intermediate]](https://www.hackerrank.com/certificates/0b6b83089543)
  - [C# [Basic]](https://www.hackerrank.com/certificates/6b20888c8b52)
  - [Python [Basic]](https://www.hackerrank.com/certificates/e9436a5b59bb)
  - [R [Basic]](https://www.hackerrank.com/certificates/7641b1eec9c6)
  - [R [Intermediate]](https://www.hackerrank.com/certificates/6a97bf08a037)
  - [JavaScript Back-end Capstone Project](https://www.coursera.org/account/accomplishments/verify/QEBDA4FHAZF7)
  - [Developing Back-End Apps with Node.js and Express](https://www.coursera.org/account/accomplishments/records/MU9Q97FPY4N5)


---

## Skills and Digital Competencies

### Summary of Qualifications

| Skill                | Level (1 to 5) | Experience (yrs) | Type        |
|----------------------|----------------|------------------|-------------|
| **Java, JavaScript**     | 5              | 23               | Professional|
| **Python**               | 4              | 5                | Professional|
| **R, C#, Go**            | 3              | 1                | Academic    |
| **AWS**                  | 5              | 12               | Professional|
| **GCP**                  | 4              | 5                | Professional|
| **IBM**                  | 3              | 2                | Academic    |
| **Microservices**        | 5              | 12               | Professional|
| **BPMS / BPM**           | 4              | 5                | Professional|
| **Digital Security**     | 5              | 14               | Professional|
| **Distributed Systems**  | 5              | 12               | Professional|
| **DevOps**               | 4              | 12               | Professional|
| **Domain-Driven Design** | 5              | 12               | Professional|
| **Front-End React, Angular**| 5          | 10               | Professional|
| **SQL**                  | 5              | 22               | Professional|
| **NoSQL**                | 5              | 10               | Professional|
| **In-Memory**            | 5              | 10               | Professional|
| **Geospatial**           | 2              | 2                | Academic    |

### Technologies and Tools

- **Amazon Web Services**: EC2, ECS, Fargate, Lambda, S3, Glacier, RDS, DynamoDB, ElastiCache for Redis, QLDB, Route 53, VPC, SQS, SNS, SES, CloudWatch, API Gateway, CloudFormation, Elastic Beanstalk
- **Google Cloud Platform**: Compute Engine, GKE, Cloud Run, Cloud Storage, Cloud Storage Coldline, Cloud SQL, MemoryStore for Redis, BigQuery, Cloud DNS, Cloud Pub/Sub, Operations Suite, API Gateway, Cloud Functions
- **IBM Cloud**: Virtual Servers, Kubernetes Service, Code Engine, Cloud Object Storage, Cold Vault, Db2 on Cloud, IBM Cloud Databases for Redis, IBM Cloud SQL Query, IBM Cloud Internet Services, VPC, IBM Event Streams, IBM Cloud Monitoring with Sysdig, API Connect, IBM Cloud Functions
- **BPMS Camunda**:
  - **Camunda Platform**: Experience with implementation and configuration of the Camunda Platform for business process automation;
  - **Camunda Cockpit**: Advanced use of Camunda Cockpit for monitoring, managing, and analyzing running processes;
  - **Camunda Modeler**: Creation and maintenance of process models using Camunda Modeler, including BPMN, DMN, and CMMN;
  - **Camunda Optimize**: Implementation of monitoring and optimization solutions with Camunda Optimize, providing detailed insights and reports;
  - **Camunda Tasklist**: Efficient management of tasks and workflows through Camunda Tasklist, ensuring correct execution of processes;
  - **Integration with Legacy Systems**: Integration of Camunda with legacy systems and other IT tools to ensure interoperability;
  - **Plugin and Extension Development**: Development of custom plugins and extensions to meet specific business needs;
  - **Complex Process Automation**: Projects involving the automation of complex processes using Camunda to improve efficiency and reduce errors;
  - **Case Management**: Utilization of CMMN (Case Management Model and Notation) for case management with Camunda;
  - **Performance Monitoring**: Implementation of process performance monitoring practices to ensure continuous quality and efficiency.
- **BPM**:
  - **Process Automation**: Specialist in business process automation to increase efficiency and reduce operational costs;
  - **Workflow Management**: Experience in workflow management to ensure effective execution of business activities;
  - **BPMN 2.0**: Deep knowledge of BPMN 2.0 for modeling, analyzing, and executing business processes;
  - **Process Design**: Competence in designing business processes, aligning them with the organization's strategic goals;
  - **Business Process Management**: Implementation and continuous improvement of business processes using BPM methodologies;
  - **International Methodologies**: Support for international process mapping methodologies such as BPMN, CMMN, and DMN;
  - **Process Auditing**: Conducting comprehensive audits of process execution and history to ensure compliance and identify improvements;
  - **Decision Modeling**: Application of DMN (Decision Model and Notation) for modeling and automating business decisions;
  - **BPM Tools Integration**: Integration of BPM tools with other corporate systems for more robust process management;
  - **Process Optimization**: Application of process optimization techniques to improve the efficiency and effectiveness of business processes.
- **Digital Security**: Management of 2048-bit private keys, TLS v1.2 and v1.3, HTTP/2, Public key pinning, DNSSEC, DANE, SSL Labs Server Test
- **Development Tools**: Git (GitHub, BitBucket), Jira, Trello, Confluence, Slack, Swagger, Postman, Visual Studio Code, Eclipse, JetBrains IDE, NPM, Maven, PM2, JWT, Serverless, SAM, Gulp, Mongoose, DynaMoose
- **Distributed Systems**: Amazon SQS, Amazon SNS, Google Cloud Pub/Sub, IBM MQ, IBM Event Streams
- **DevOps**: GitHub Actions, Jenkins, GitLab CI, CircleCI, Ansible, Puppet, Chef, AWS CloudFormation, Google Cloud Deployment Manager, IBM Cloud Automation Manager, Prometheus, Grafana, Nagios, Zabbix, ELK Stack, Splunk, Graylog, IBM Log Analysis with LogDNA, Docker, Kubernetes, Terraform, JUnit, TestNG, Selenium, Postman, pytest, unittest, Jenkins, GitLab CI/CD, IBM UrbanCode, Bamboo
- **Java**: Spring Core, Boot, Data JPA, Security, Web MVC, JUnit, Mockito, Apache Tomcat, JBoss / WildFly
- **JavaScript**: ReactJS, Angular, Jasmine, Mocha, Node.js, Express.js, NestJS
- **Python**: Django, Flask, pytest, unittest, Django, Flask, FastAPI
- **SQL**: PostgreSQL
- **NoSQL**: MongoDB, DynamoDB
- **In-Memory Databases**: Redis
- **Geospatial Databases**: Postgis

---

## Education

### PUC – Pontifical Catholic University of SP

- **Course**: Business Administration
- **Period**: 2017 – 2020
- **Qualification Awarded**: Bachelor's degree in Business Administration (Incomplete)

### Bradesco Foundation

- **Course**: Electronics
- **Period**: 1992 – 1995
- **Qualification Awarded**: Technical Course in Electronics

### Language Skills

- **Mother Tongue**: Portuguese
- **Other Languages**:
  - **English**: C2 (Listening, Reading), C1 (Oral interaction, Oral production), B2 (Writing)
  - **Spanish**: C1 (Listening, Reading), B2 (Oral interaction, Oral production, Writing)

### Language Certification – English (C1)

- **[EFSET English Certificate](https://cert.efset.org/CpUPqt)**: Level C1, 27 Jun 2024

---

## Professional Experience

### Dev Back-End Sr

- **Employer**: Rodio Tech
- **Client**: CERC
- **Project**: Command Center Automation
- **Period**: February 2024 – May 2024
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Financial, Banking
- **Work Arrangement**: Hybrid
- **Activities**:
  - Development of applications: building cloud automation microservices
  - Systems analysis: data modeling, systems documentation
- **Technologies and Tools**:
  - Languages: Node.js, Java 17, Python
  - GCP: Storage, Functions, pub/sub, SQL, Firestore

### Dev Back-End Java / Camunda Sr

- **Employer**: NTConsult
- **Client**: Banco Macro
- **Project**: Bank Innovation
- **Period**: May 2023 – December 2023
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Financial, Banking
- **Work Arrangement**: Home Office
- **Activities**:
  - Business Processes: Development of advanced resources for the orchestration and automation of business processes, focusing on creating efficient workflows
  - Architecture and Development: Design and implementation of robust and scalable architectures for complex systems
- **Technologies and Tools**:
  - Languages: Java 17
  - BPMS Camunda: Platform, Cockpit, Modeler, Optimize, Tasklist

### JavaScript Software Engineer

- **Employer**: Vitplay
- **Client**: Vitplay
- **Project**: Low-Code System
- **Period**: September 2022 – May 2023
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Services
- **Work Arrangement**: Home Office
- **Activities**:
  - System Analysis and Architecture: Creation of an exclusive architecture, design and implementation of diagrams, data modeling, creation of user stories, product documentation
  - Development: Low-code and no-code system with a unique cognitive interface
  - UX/UI: Hypothesis collection, desk research, customer journeys, usability testing, wireframes, accessibility, prototyping, documentation
- **Technologies and Tools**:
  - Language / Server: JavaScript, Node.js
  - OpenAPI: Modeling RESTful Web APIs with Swagger
  - AWS: EC2, ECS, RDS, S3, SNS, SQS, API Gateway, Lambda, DynamoDB

### Java Software Engineer / Technical Lead

- **Employer**: Favo
- **Client**: Favo
- **Project**: Core System
- **Period**: December 2021 – September 2022
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Retail
- **Work Arrangement**: Home Office
- **Activities**:
  - System Analysis and Architecture: Collaboration with other teams to ensure consistent implementation of architecture, integration problem solving, design and implementation of diagrams, data modeling, creation of user stories, product documentation
  - Development: Building reactive web applications and automated testing
- **Technologies and Tools**:
  - Languages / Servers: Java 8, JavaScript, Java Spring, Node.js
  - OpenAPI: Modeling RESTful Web APIs with Swagger
  - AWS: EC2, ECS, RDS, S3, SNS, SQS, API Gateway, Lambda, DynamoDB
  - Databases: DynamoDB, PostgreSQL, Redis

### Dev Back-End Java Sr

- **Employer**: Wayon
- **Client**: Tokio Marine
- **Project**: Core Integration
- **Period**: June 2020 – January 2021
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Insurance
- **Work Arrangement**: Home Office
- **Activities**:
  - Development of integrations for associated insurance companies
  - Design of business flows using BPMN
  - Building applications using microservices
  - Application Server Management
- **Technologies and Tools**:
  - Languages and Frameworks: Java 8, Spring [Core, Boot, Batch], JPA, JSF, JSP, Servlet, AX-WS, JAX-RS, JavaScript
  - Databases: Oracle DB, SQL Server, MongoDB
  - Technologies and Tools: GIT, APIs [REST, SOAP], Docker, Kubernetes
  - Application Servers: JBoss, WildFly, Spring

### Dev Back-End Java Sr

- **Employer**: Certsys
- **Client**: Banco BV
- **Project**: Migração BV
- **Period**: January 2020 – March 2020
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Finance, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Development and architecture of microservices for multi-brand transaction issuance (acquirer) and bank slips
  - Development and migration of service components
  - Adjustment and optimization of stored procedures in the database
  - Efficient processing of multi-brand transactions (acquirer)
- **Technologies and Tools**:
  - Languages and Frameworks: Java 8, JavaScript, Shell Script
  - Databases: Relational databases, SQL SP's, Pure JDBC
  - Version Control: GIT, GitLab
  - Application Servers: IBM WebSphere, Apache Tomcat 8

### Full-Stack / Technical Lead

- **Employer**: Capgemini
- **Client**: Santander
- **Project**: Bank slip
- **Period**: September 2019 – December 2019
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Development and architecture of microservices for multi-brand transaction issuance (acquirer) and bank slips
  - Development and migration of service components
  - Adjustment and optimization of stored procedures in the database
  - Implementation of DevOps practices, including process automation and source code management
- **Technologies and Tools**:
  - Languages and Frameworks: Java 8, JavaScript, Shell Script
  - Databases: Relational databases, SQL SP's, Pure JDBC
  - Services and APIs: Apache Kafka, SOAP, REST
  - Application Servers: IBM WebSphere, Apache Tomcat 8

### Full-Stack / Technical Lead

- **Employer**: ZUP
- **Client**: CIELO
- **Project**: Multi-Brand Project
- **Period**: May 2019 – August 2019
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Architecture and programming of computer systems, developing solutions for multi-brand transaction issuance and bank slips
  - Project management as a technical leader, collaborating with other leaders and partners
  - Development and migration of service components, optimizing stored procedures in the database
  - Ensuring efficient processing of financial transactions with a focus on security and compliance with banking requirements
- **Technologies and Tools**:
  - Languages and Frameworks: Java 8, JavaScript, Shell Script
  - Databases: Relational databases, SQL Stored Procedures, Pure JDBC
  - Services and APIs: Apache Kafka, REST, SOAP
  - Application Servers: IBM WebSphere, Apache Tomcat 8
  - Cloud Platform: Amazon Web Services

### Full-Stack JavaScript

- **Employer**: Saferocket
- **Client**: Saferocket
- **Project**: Swift Project
- **Period**: November 2018 – March 2019
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Finance
- **Work Arrangement**: Hybrid
- **Activities**:
  - Development of a money transfer system for international transactions, ensuring a seamless user experience and compliance with global standards, including integration with Swift and IBAN
- **Technologies and Tools**:
  - Node.js, Heroku, MongoDB, React, Vanilla JavaScript, Swift

### Full-Stack Java

- **Employer**: Essence
- **Client**: C&A Modas
- **Project**: Migração de Sistemas POS
- **Period**: August 2018 – November 2018
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Retail
- **Work Arrangement**: Onsite
- **Activities**:
  - Migration project of POS systems for hundreds of stores in an international clothing retail chain, utilizing Oracle technologies and extensive knowledge of Oracle industry solutions (retail)
  - Development of all platform systems, integration with acquirers, CPF and CNPJ verification services, geolocation services, credit and debit card payment systems, and improvement of technical processes
- **Technologies and Tools**:
  - Oracle Retail, Java 8, Java Swing, Java Spring, Oracle DB

### Dev Back-End Java / Technical Lead

- **Employer**: Valemobi
- **Client**: Valemobi
- **Project**: Home Broker and Investment Funds
- **Period**: April 2018 – August 2018
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Finance, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Development of Home Broker and multi-market investment fund projects
  - Technical leadership of a small team
  - System analysis across various projects
  - Development of customized solutions for the platform
  - Migration of the front-end framework to a more updated version, improving performance and functionality
  - Bug fixing and performance optimization using bug tracking tools
  - Definition of best practices for developers
- **Technologies and Tools**:
  - Java 8, Node.js, Angular 2 and 4, TypeScript, Sentry, VSC, Eclipse, GIT

### Full-Stack Java / JavaScript

- **Employer**: Indra
- **Client**: Santander
- **Project**: Santander Improvements
- **Period**: January 2018 – March 2018
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Services
- **Work Arrangement**: Onsite
- **Activities**:
  - Creation of internal systems for resource and employee management
  - Platform migration, business process management, system analysis, and development of REST microservices
- **Technologies and Tools**:
  - Java 8, Node.js, Angular 4 with TypeScript, Linux CentOS, Linux CentOS RPM, PostgreSQL, GIT, Jira, Apache Maven, JUnit, Spring (security, core container, AOP and instrumentation, data access/integration, Web, testing), RESTful web services, JavaScript, VSC, Eclipse, GIT

### Full-Stack Java / JavaScript

- **Employer**: Decision
- **Client**: Diebold
- **Project**: POS SAT / SEFAZ
- **Period**: September 2017 – December 2017
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Retail
- **Work Arrangement**: Onsite
- **Activities**:
  - Migration project of POS systems, including POS for hypermarkets, pharmacies, gas stations, and customer service counters
  - System development and analysis
  - Application of tax, legal, and electronic invoice rules
  - Integration with SAT and SEFAZ
- **Technologies and Tools**:
  - Linux CentOS, C, C++, Java 8, Node.js, PostgreSQL, GIT, Bamboo, Jira, Apache Maven, Mockito, JUnit, Spring (security, core container, AOP and instrumentation, data access/integration, Web, testing), RESTful web services, Angular 4 with TypeScript, JavaScript, Eclipse

### Dev Back-End Java Sr

- **Employer**: Global Hitss
- **Client**: Itaú
- **Project**: Artificial Intelligence Project
- **Period**: August 2013 – October 2013
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Finance, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Development of an AI project for Java source code enhancement
  - System analysis to identify improvements and affinities
  - Development of the core code generation
  - Identification of problems and opportunities, interaction with the client to gather requirements and process details, and production of specification documents
- **Technologies and Tools**:
  - WebSphere, Java 5, SQL

### Dev Back-End Java Sr

- **Employer**: Stefanini
- **Client**: Itaú
- **Project**: Thermometer Project
- **Period**: November 2010 – February 2011
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Finance, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Development, architecture, and analysis of the Java system for the client's project – a system aimed at evaluating the satisfaction of all client professionals
- **Technologies and Tools**:
  - Java EE 5, Eclipse 3.2, IBM WebSphere, Struts, Hibernate, Oracle DB, XML and XSL, JBoss Seam, JSF, JPA, RichFaces, JUnit, DBUnit, Selenium, DHTML

### Dev Back-End Java Sr

- **Employer**: Wunderwerk
- **Client**: Bradesco
- **Project**: Bradesco IT Improvements Project
- **Period**: May 2010 – August 2010
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Finance, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Development, architecture, and analysis of the Java system for the client's project – a system for partners within IT development standards
- **Technologies and Tools**:
  - Java, IBM WebSphere, IBM DB2

### Dev Back-End Java Sr

- **Employer**: Vitoretti
- **Client**: Bradesco
- **Project**: Financial Dashboard Project
- **Period**: May 2006 – March 2007
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Finance, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Development of the client's financial management system
- **Technologies and Tools**:
  - Java 5, Eclipse, Struts, JDBC, IBM DB2, DHTML

### Dev Back-End Java Sr

- **Employer**: SONDA
- **Client**: Bradesco
- **Project**: Financial Dashboard
- **Period**: March 2006 – May 2006
- **Location**: São Paulo – Brazil
- **Sectors**: IT, Finance, Banking
- **Work Arrangement**: Onsite
- **Activities**:
  - Development of Java systems for the client's project – a financial management system
- **Technologies and Tools**:
  - Java 5, Eclipse, Struts, JDBC, DB2, DHTML
