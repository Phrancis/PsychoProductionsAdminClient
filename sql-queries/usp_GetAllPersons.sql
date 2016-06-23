use Francis;
go

if exists (
    select 
        * 
    from 
        INFORMATION_SCHEMA.ROUTINES
    where
        ROUTINE_NAME = 'usp_GetAllPersons'
        and SPECIFIC_SCHEMA = 'PsychoProductions'
        and ROUTINE_TYPE = 'PROCEDURE'
)
drop procedure PsychoProductions.usp_GetAllPersons;
go

create procedure PsychoProductions.usp_GetAllPersons 
as
begin
select
    [PersonId] = P.Id,
    [Name] = P.Name,
    [Role] = PR.Name,
    [Organization] = P.Organization,
    [PhoneType] = PT.Name,
    [Phone] = PP.PhoneNumber,
    [EmailType] = ET.Name,
    [Email] = PE.Email,
    [Website] = P.Website,
    [Address] = PA.StreetAddress +' '+ PA.City +' '+ PA.St +' '+ PA.Zip,
    [AddressType] = AT.Name,
    [DefaultBillingMethod] = BM.Name,
    [Active?] = case P.IsActive 
        when 1 then 'Yes' 
        when 0 then 'No' 
        else 'Unknown' end,
    [DateAdded] = cast(P.CreatedDate as date)
    --*
from PsychoProductions.Persons as P
join PsychoProductions.PersonRoles as PR
    on P.PersonRoleId = PR.Id
join PsychoProductions.BillingMethods as BM
    on P.DefaultBillingMethodId = BM.Id
left join PsychoProductions.PersonAddresses as PA
    on P.Id = PA.PersonId
left join PsychoProductions.AddressTypes as AT
    on PA.AddressTypeId = AT.Id
left join PsychoProductions.PersonPhones as PP
    on P.Id = PP.PersonId
left join PsychoProductions.PhoneTypes as PT
    on PP.PhoneTypeId = PT.Id
left join PsychoProductions.PersonEmails as PE
    on P.Id = PE.PersonId
left join PsychoProductions.EmailTypes as ET
    on PE.EmailTypeId = ET.Id
order by P.Id asc;
end

go

execute PsychoProductions.usp_GetAllPersons