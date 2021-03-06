--------
--DDL for Employee
-------
create table EMPLOYEE
(
	EMP_ID VARCHAR(36) NOT NULL,
	VERSION NUMERIC(10),
	EMP_NAME VARCHAR(50),
	MAX_OCCUR_IN_WEEK NUMERIC(2),
	PRIMARY KEY(EMP_ID)
);

--------
--DDL for DAILYSCHEDULEDEF
-------
create table DAILYSCHEDULEDEF
(
	DSCHEDDEF_ID VARCHAR(36) NOT NULL,
	VERSION NUMERIC(10),
	DSCHED_NAME VARCHAR(50),
	IS_RESTDAY NUMERIC(1),
	MAX_EMPLOYEE_AMOUNT NUMERIC(2),
	SORTINDEX NUMERIC(2),
	PRIMARY KEY(DSCHEDDEF_ID)
);

--------
--DDL for PREFERDEF
-------
create table PREFERDEF
(
	PFD_ID VARCHAR(36) NOT NULL,
	VERSION NUMERIC(10),
	EMP_ID VARCHAR(36),
	DSCHEDDEF_ID VARCHAR(36),
	PREFER_DATE DATE,
	PRIMARY KEY(PFD_ID)
);

ALTER TABLE PREFERDEF ADD CONSTRAINT FK_PREFERDEF_EMPLOYEE FOREIGN KEY (EMP_ID)
	  REFERENCES EMPLOYEE (EMP_ID);
	  
ALTER TABLE PREFERDEF ADD CONSTRAINT FK_PREFERDEF_DAILYSCHEDULEDEF FOREIGN KEY (DSCHEDDEF_ID)
	  REFERENCES DAILYSCHEDULEDEF (DSCHEDDEF_ID);
	  
--------
--DDL for DAILYSCHEDULEINST
-------
create table DAILYSCHEDULEINST
(
	DSCHED_ID VARCHAR(36) NOT NULL,
	VERSION NUMERIC(10),
	DSCHEDDEF_ID VARCHAR(36),
	SCHED_DATE DATE,
	PRIMARY KEY(DSCHED_ID)
);

ALTER TABLE DAILYSCHEDULEINST ADD CONSTRAINT FK_DAILYSCHEDULEINST_DAILYSCHEDDEF FOREIGN KEY (DSCHEDDEF_ID)
	  REFERENCES DAILYSCHEDULEDEF(DSCHEDDEF_ID);

ALTER TABLE DAILYSCHEDULEINST ADD CONSTRAINT UQ_DAILYSCHEDULINST UNIQUE (DSCHEDDEF_ID, SCHED_DATE);
	  
--------
--DDL for DSD_EMP_INST
-------
create table DSD_EMP_INST
(
	DSCHED_ID VARCHAR(36),
	EMP_ID VARCHAR(36),
	PRIMARY KEY(DSCHED_ID, EMP_ID)
);

ALTER TABLE DSD_EMP_INST ADD CONSTRAINT FK_DSD_EMP_INST_EMPLOYEE FOREIGN KEY (EMP_ID)
	  REFERENCES EMPLOYEE (EMP_ID);
	  
ALTER TABLE DSD_EMP_INST ADD CONSTRAINT FK_DSD_EMP_INST_DAILYSCHEDINST FOREIGN KEY (DSCHED_ID)
	  REFERENCES DAILYSCHEDULEINST (DSCHED_ID);


