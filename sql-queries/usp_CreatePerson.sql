if exists (
    select 
        * 
    from 
        INFORMATION_SCHEMA.ROUTINES
    where
        ROUTINE_NAME = 'usp_CreatePerson'
        and SPECIFIC_SCHEMA = 'PsychoProductions'
        and ROUTINE_TYPE = 'PROCEDURE'
)
drop procedure PsychoProductions.usp_CreatePerson;
go

create procedure PsychoProductions.usp_CreatePerson
    @PersonRoleId int,
    @Name varchar(500) = null,
    @Organization varchar(500) = null,
    @Website varchar(500) = null,
    @DefaultBillingMethodId int = 1, --unassigned
    @AddressTypeId int = null,
    @StreetAddress varchar(500) = null,
    @City varchar(200) = null,
    @State char(2) = null,
    @Zip varchar(10) = null,
    @PhoneTypeId int = null,
    @PhoneNumber varchar(20) = null,
    @EmailTypeId int= null,
    @EmailAddress varchar(200) = null
as
set nocount on;

begin transaction;

if not exists (select 1 from PsychoProductions.PersonRoles where Id = @PersonRoleId)
begin;
    throw 50001, 'Invalid PersonRoleId', 1
    rollback transaction;
end;
else 
begin;
    insert into PsychoProductions.Persons (
        PersonRoleId,
        Name,
        Organization,
        Website,
        DefaultBillingMethodId,
        IsActive
    ) values (
        @PersonRoleId,
        @Name,
        @Organization,
        @Website,
        @DefaultBillingMethodId,
        1
    );

    declare @NewPersonId int = @@IDENTITY;

    if @AddressTypeId is not null
    begin;
        if not exists (select 1 from PsychoProductions.AddressTypes where Id = @AddressTypeId)
        begin;
            throw 50001, 'Invalid AddressTypeId', 1;
            rollback transaction;
        end;
        else 
        begin;
            insert into PsychoProductions.PersonAddresses (
                PersonId,
                AddressTypeId,
                StreetAddress,
                City,
                St,
                Zip
            ) values (
                @NewPersonId,
                @AddressTypeId,
                @StreetAddress,
                @City,
                @State,
                @Zip
            );
        end;
    end;

    if @PhoneTypeId is not null
    begin;
        if not exists (select 1 from PsychoProductions.PhoneTypes where Id = @PhoneTypeId)
        begin;
            throw 50001, 'Invalid PhoneTypeId', 1;
            rollback transaction;
        end;
        else
        begin;
            insert into PsychoProductions.PersonPhones (
                PersonId,
                PhoneTypeId,
                PhoneNumber
            ) values (
                @NewPersonId,
                @PhoneTypeId,
                @PhoneNumber
            );
        end;
    end;

    if @EmailTypeId is not null
    begin;
        if not exists (select 1 from PsychoProductions.EmailTypes where Id = @EmailTypeId)
        begin;
            throw 50001, 'Invalid EmailTypeId', 1;
            rollback transaction;
        end;
        else
        begin;
            insert into PsychoProductions.PersonEmails (
                PersonId,
                EmailTypeId,
                Email
            ) values (
                @NewPersonId,
                @EmailTypeId,
                @EmailAddress
            );
        end;
    end;
    commit transaction;
end;

go