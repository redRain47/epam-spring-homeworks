INSERT INTO addresses (id, house, street, city, region, zipcode)
VALUES ('84c56ea4-7415-11ea-bc55-0242ac130003', '38a', 'Some st.', 'Some city', 'Some region', '123455'),
       ('84c570f2-7415-11ea-bc55-0242ac130003', '38a', 'Another st.', 'Another city', 'Another region', '983155');

INSERT INTO universities (id, name)
VALUES ('84c571f6-7415-11ea-bc55-0242ac130003', 'Oles Honchar Dnipro National University'),
       ('84c572c8-7415-11ea-bc55-0242ac130003', 'Taras Shevchenko Kyiv National University');

INSERT INTO students (id, first_name, last_name, address_id, university_id)
VALUES ('84c57494-7415-11ea-bc55-0242ac130003', 'Ivan', 'Ivanov', '84c56ea4-7415-11ea-bc55-0242ac130003',
        '84c571f6-7415-11ea-bc55-0242ac130003'),
       ('84c57566-7415-11ea-bc55-0242ac130003', 'Vasya', 'Pupkin', '84c570f2-7415-11ea-bc55-0242ac130003',
        '84c572c8-7415-11ea-bc55-0242ac130003');