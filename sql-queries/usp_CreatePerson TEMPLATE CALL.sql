/* 
Inserts a new person into the PsychoProductions database.
- Fields which are marked a NULL are not necessary.
- Commented out queries added to find the type IDs for some fields

*/
USE Francis;
GO

-- select * from PsychoProductions.PersonRoles order by Id;
DECLARE @PersonRoleId int = 3
--MAKE SURE TO HAVE AT LEAST A NAME *OR* ORGANIZATION (PREFERABLY BOTH)
DECLARE @Name varchar(500) = NULL
DECLARE @Organization varchar(500) = NULL
DECLARE @Website varchar(500) = NULL
-- select * from PsychoProductions.BillingMethods order by Id;
DECLARE @DefaultBillingMethodId int = 1
-- select * from PsychoProductions.AddressTypes order by Id;
DECLARE @AddressTypeId int = NULL
DECLARE @StreetAddress varchar(500) = NULL
DECLARE @City varchar(200) = NULL
DECLARE @State char(2) = NULL
DECLARE @Zip varchar(10) = NULL
-- select * from PsychoProductions.PhoneTypes order by Id;
DECLARE @PhoneTypeId int = NULL
DECLARE @PhoneNumber varchar(20) = NULL
-- select * from PsychoProductions.EmailTypes order by Id;
DECLARE @EmailTypeId int = NULL
DECLARE @EmailAddress varchar(200) = NULL

--RUN WHOLE SCRIPT ONCE THE FIELDS ARE FILLED IN
DECLARE @RC int
EXECUTE @RC = [PsychoProductions].[usp_CreatePerson] 
   @PersonRoleId
  ,@Name
  ,@Organization
  ,@Website
  ,@DefaultBillingMethodId
  ,@AddressTypeId
  ,@StreetAddress
  ,@City
  ,@State
  ,@Zip
  ,@PhoneTypeId
  ,@PhoneNumber
  ,@EmailTypeId
  ,@EmailAddress
GO

--confirm:
select * 
from PsychoProductions.Persons as p
left join PsychoProductions.PersonAddresses as pa on p.Id = pa.PersonId
left join PsychoProductions.PersonPhones as pp on p.Id = pp.PersonId
left join PsychoProductions.PersonEmails as pe on p.Id = pe.PersonId
order by p.Id desc