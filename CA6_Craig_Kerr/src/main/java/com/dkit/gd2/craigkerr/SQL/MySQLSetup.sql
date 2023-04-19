--Create table with fields, plane id int, plane name varchar, plane rating float--
CREATE TABLE Planes (
    PlaneID int NOT NULL AUTO_INCREMENT,
    PlaneName varchar(255) NOT NULL,
    PlaneRating float NOT NULL,
    PRIMARY KEY (PlaneID)
);


--fill table with 10 planes--
INSERT INTO Planes (PlaneName, PlaneRating) VALUES
('RyanAir 101', 5.0),
('RyanAir 102', 4.0),
('RyanAir 103', 5.0),
('RyanAir 104', 5.0),
('RyanAir 105', 3.5),
('RyanAir 106', 4.5),
('RyanAir 107', 4.0),
('RyanAir 108', 3.0),
('RyanAir 109', 4.0),
('RyanAir 110', 5.0);