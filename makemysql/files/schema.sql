CREATE DATABASE IF NOT EXISTS allianz DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE allianz;

CREATE TABLE students (
  enrolment_number int(11) NOT NULL,
  name varchar(255) NOT NULL,
  surname varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `students`
--
ALTER TABLE students
  ADD UNIQUE KEY enrolment_number (enrolment_number);

USE allianz;
--
-- Dumping data for table students
--

INSERT IGNORE INTO students (enrolment_number, name, surname) VALUES(1, 'Sadik', 'Bakiu');
INSERT IGNORE INTO students (enrolment_number, name, surname) VALUES(2, 'Sadik', 'Bakiu');
