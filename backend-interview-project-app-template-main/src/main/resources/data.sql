-- INSERT INTO SAMPLE VALUES('1', 'VALUE1');
-- INSERT INTO SAMPLE VALUES('2', 'VALUE2');


-- DEVICES
INSERT INTO DEVICE VALUES('1','Windows 10','Windows');
INSERT INTO DEVICE VALUES('2','Windows Server','Windows');
INSERT INTO DEVICE VALUES('3','Windows Phone','Windows');
INSERT INTO DEVICE VALUES('4','Windows XP','Windows');

INSERT INTO DEVICE VALUES('5','Machintosh','Mac');
INSERT INTO DEVICE VALUES('6','Ipad','Mac');
INSERT INTO DEVICE VALUES('7','Iphone','Mac');

INSERT INTO DEVICE VALUES('8','Fedora','Linux');
INSERT INTO DEVICE VALUES('9','Ubuntu','Linux');


-- SERVICES
INSERT INTO SERVICE(id,description,type,cost) VALUES('1','Antivirus for Windows','Windows',5);
INSERT INTO SERVICE(id,description,type,cost) VALUES('2','Antivirus for Mac','Mac',7);
INSERT INTO SERVICE(id,description,type,cost) VALUES('3','Antivirus for Linux','Linux',10);

INSERT INTO SERVICE(id,description,type,cost) VALUES('4','Backup for Windows','Windows',3);
INSERT INTO SERVICE(id,description,type,cost) VALUES('5','Backup for Mac','Mac',3);
INSERT INTO SERVICE(id,description,type,cost) VALUES('6','Backup for Linux','Linux',3);

INSERT INTO SERVICE(id,description,type,cost) VALUES('7','Screen Share for Windows','Windows',1);
INSERT INTO SERVICE(id,description,type,cost) VALUES('8','Screen Share for Mac','Mac',1);
INSERT INTO SERVICE(id,description,type,cost) VALUES('9','Screen Share for Linux','Linux',1);


-- CUSTOMERS
INSERT INTO CUSTOMER(id,name,number_of_devices) VALUES('1','Christian',5);
INSERT INTO CUSTOMER(id,name,number_of_devices) VALUES('2','Samuel',1);


-- SERVICE_ASSIGNATIONS
-- WINDOWS
INSERT INTO SERVICE_ASSIGNATION(id,device_id,service_id,customer_id,quantity) VALUES('1','1','1','1',2);
INSERT INTO SERVICE_ASSIGNATION(id,device_id,service_id,customer_id,quantity) VALUES('2','1','4','1',1);
INSERT INTO SERVICE_ASSIGNATION(id,device_id,service_id,customer_id,quantity) VALUES('3','1','7','1',2);
-- MAC
INSERT INTO SERVICE_ASSIGNATION(id,device_id,service_id,customer_id,quantity) VALUES('4','5','2','1',3);
INSERT INTO SERVICE_ASSIGNATION(id,device_id,service_id,customer_id,quantity) VALUES('5','5','5','1',2);
INSERT INTO SERVICE_ASSIGNATION(id,device_id,service_id,customer_id,quantity) VALUES('6','5','8','1',2);


