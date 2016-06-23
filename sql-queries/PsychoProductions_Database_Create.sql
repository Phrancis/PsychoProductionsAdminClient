use Francis;
go
begin transaction;

/* REFERENCE VALUES TABLES */

create table PsychoProductions.PersonRoles (
    Id int identity(1,1) primary key,
    Name varchar(50) not null unique
);
insert into PsychoProductions.PersonRoles (Name) 
values
    ('Owner'),
    ('Partner'),
    ('Customer'),
    ('Vendor'),
    ('Session musician');

create table PsychoProductions.BillingMethods (
    Id int identity(1,1) primary key,
    Name varchar(50) not null unique
);
insert into PsychoProductions.BillingMethods (Name)
values
    ('Unassigned'),
    ('Net 30'),
    ('Net 15'),
    ('Cash on delivery'),
    ('Cash with order');

create table PsychoProductions.Products (
    Id int identity(1,1) primary key,
    Name varchar(200) not null unique,
    Price decimal(6,2) null,
    IsStandard bit null default 1,
    IsTaxable bit null default 0,
    CreatedDate datetime default getdate()
);
insert into PsychoProductions.Products (Name, Price, IsStandard, IsTaxable)
values
    ('Basic musical arrangement (3 or fewer)', 40, 1, 0), 
    ('Basic musical arrangement (4 or more)', 30, 1, 0), 
    ('Advanced musical arrangement (3 or fewer)', 60, 1, 0), 
    ('Advanced musical arrangement (4 or more)', 50, 1, 0), 
    ('Instrumental leasing (3 or fewer)', 25, 1, 0), 
    ('Instrumental leasing (4 or more)', 20, 1, 0), 
    ('Instrumental leasing (NAPH 3 or more)', 20, 1, 0), 
    ('Graphic design (album sleeve)', 80, 1, 0), 
    ('Graphic design (full CD & sleeve)', 130, 1, 0), 
    ('Graphic design (full CD, sleeve & booklet)', 200, 1, 0), 
    ('Graphic design (flyer)', 30, 1, 0), 
    ('Graphic design (logo)', 30, 1, 0),
    ('Graphic design (t-shirt)', 40, 1, 0), 
    ('Graphic design (sticker, buttons, small items)', 20, 1, 0),
    ('Rush uplift charge (Basic project)', 10, 1, 0),
    ('Rush uplift charge (Advanced project)', 20, 1, 0);

create table PsychoProductions.InvoiceStatuses (
    Id int identity(1,1) primary key,
    Name varchar(50) not null unique
);
insert into PsychoProductions.InvoiceStatuses (Name)
values
    ('Open'),
    ('Paid'),
    ('Partially Paid'),
    ('Loss'),
    ('Cancelled');

create table PsychoProductions.TransactionTypes (
    Id int identity(1,1) primary key,
    Name varchar(50) not null unique
);
insert into PsychoProductions.TransactionTypes (Name)
values
    ('Debit'),
    ('Credit');

create table PsychoProductions.AddressTypes (
    Id int identity(1,1) primary key,
    Name varchar(50) not null unique
);
insert into PsychoProductions.AddressTypes (Name)
values
    ('Unique'),
    ('Physical'),
    ('Shipping'),
    ('Billing'),
    ('Mailing');

create table PsychoProductions.PhoneTypes (
    Id int identity(1,1) primary key,
    Name varchar(50) not null unique
);
insert into PsychoProductions.PhoneTypes (Name)
values
    ('Mobile'),
    ('Business'),
    ('Home'),
    ('Fax');

create table PsychoProductions.EmailTypes (
    Id int identity(1,1) primary key,
    Name varchar(50) not null unique
);
insert into PsychoProductions.EmailTypes (Name)
values
    ('Business'),
    ('Personal');

/* 
    PRIMARY TABLES 
*/

/* PERSONS TABLE */

create table PsychoProductions.Persons (
    Id int identity(1,1) primary key,
    PersonRoleId int not null 
        references PsychoProductions.PersonRoles(Id),
    Name varchar(500) null,
    Organization varchar(500) null,
    Website varchar(500) null,
    DefaultBillingMethodId int not null
        references PsychoProductions.BillingMethods(Id),
    IsActive bit default 1,
    CreatedDate datetime default getdate()
);

create table PsychoProductions.PersonAddresses (
    Id int identity(1,1) primary key,
    PersonId int not null
        references PsychoProductions.Persons(Id),
    AddressTypeId int not null
        references PsychoProductions.AddressTypes(Id),
    StreetAddress varchar(500) null,
    City varchar(200) null,
    St char(2) null,
    Zip varchar(10) null,
    CreatedDate datetime default getdate()
);

create table PsychoProductions.PersonPhones (
    Id int identity(1,1) primary key,
    PersonId int not null
        references PsychoProductions.Persons(Id),
    PhoneTypeId int not null
        references PsychoProductions.PhoneTypes(Id),
    PhoneNumber varchar(20) not null,
    CreatedDate datetime default getdate()
);

create table PsychoProductions.PersonEmails (
    Id int identity(1,1) primary key,
    PersonId int not null
        references PsychoProductions.Persons(Id),
    EmailTypeId int not null
        references PsychoProductions.EmailTypes(Id),
    Email varchar(200) not null,
    CreatedDate datetime default getdate()
);

/* PROJECTS TABLES */

create table PsychoProductions.Projects (
    Id int identity(1,1) primary key,
    RequestPersonId int not null
        references PsychoProductions.Persons(Id),
    AssignPersonId int not null
        references PsychoProductions.Persons(Id),
    ProjectName varchar(500) not null,
    ProjectDescription varchar(1000) null,
    BillingMethodId int not null
        references PsychoProductions.BillingMethods(Id),
    OrderDate date not null,
    DueDate date null,
    CompletedDate date null,
    CreatedDate datetime default getdate()
);

/* INVOICING / FINANCE */

create table PsychoProductions.Invoices (
    Id int identity(1,1) primary key,
    ProjectId int not null
        references PsychoProductions.Projects(Id),
    CreatedByPersonId int not null
        references PsychoProductions.Persons(Id),
    BillToPersonId int not null
        references PsychoProductions.Persons(Id),
    BillToAddressId int not null
        references PsychoProductions.PersonAddresses(Id),
    ShipToAddressId int null
        references PsychoProductions.PersonAddresses(Id),
    InvoiceStatusId int not null
        references PsychoProductions.InvoiceStatuses(Id),
    InvoiceDate date not null,
    PaidDate date null,
    CreatedDate datetime default getdate()
);

create table PsychoProductions.InvoiceDetails (
    Id int identity(1,1) primary key,
    InvoiceId int not null
        references PsychoProductions.Invoices(Id),
    LineItem int not null,
    ProductId int not null
        references PsychoProductions.Products(Id),
    Quantity int not null,
    TaxableRate decimal(4,2) default 0,
    CreatedDate datetime default getdate()
);

create table PsychoProductions.AccountingTransactions (
    Id int identity(1,1) primary key,
    TransactionTypeId int not null
        references PsychoProductions.TransactionTypes(Id),
    InvoiceId int not null
        references PsychoProductions.Invoices(Id),
    PaidByPersonId int not null
        references PsychoProductions.Persons(Id),
    PaidToPersonId int not null
        references PsychoProductions.Persons(Id),
    TransactionDate date not null,
    Note varchar(500) null,
    CreatedDate datetime default getdate()
);

--rollback transaction
commit transaction;


--verify tables/columns/indexes
select 
    t.object_id, 
    t.name as [Tbl],
    c.column_id as [ColId],
    c.name as [Col],
    i.name as [Idx],
    i.type_desc as [IdxType]
from sys.schemas as s
join sys.tables as t on s.schema_id = t.schema_id
join sys.columns as c on t.object_id = c.object_id
left join sys.index_columns as ic on c.object_id = ic.object_id and c.column_id = ic.column_id
left join sys.indexes as i on ic.object_id = i.object_id and ic.index_id = i.index_id
where s.name = 'PsychoProductions'
order by t.name, c.column_id;

--verify table data
select 'PersonRoles' as [Table], * from PsychoProductions.PersonRoles order by Id;
select 'BillingMethods' as [Table], * from PsychoProductions.BillingMethods order by Id;
select 'Products' as [Table], * from PsychoProductions.Products order by Id;
select 'InvoiceStatuses' as [Table], * from PsychoProductions.InvoiceStatuses order by Id;
select 'TransactionTypes' as [Table], * from PsychoProductions.TransactionTypes order by Id;
select 'AddressTypes' as [Table], * from PsychoProductions.AddressTypes order by Id;
select 'PhoneTypes' as [Table], * from PsychoProductions.PhoneTypes order by Id;
select 'EmailTypes' as [Table], * from PsychoProductions.EmailTypes order by Id;