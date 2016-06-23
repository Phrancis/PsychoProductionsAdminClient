if exists (
    select 
        * 
    from 
        INFORMATION_SCHEMA.ROUTINES
    where
        ROUTINE_NAME = 'usp_GetSchemaDetails'
        and SPECIFIC_SCHEMA = 'PsychoProductions'
        and ROUTINE_TYPE = 'PROCEDURE'
)
drop procedure PsychoProductions.usp_GetSchemaDetails;
go

create procedure PsychoProductions.usp_GetSchemaDetails
as
begin

select 
    t.object_id, 
    t.name as [table],
    c.column_id,
    c.name as [column],
    coalesce(i.name, '') as [index],
    coalesce(i.type_desc, '') as [index_type]
from sys.schemas as s
join sys.tables as t on s.schema_id = t.schema_id
join sys.columns as c on t.object_id = c.object_id
left join sys.index_columns as ic on c.object_id = ic.object_id and c.column_id = ic.column_id
left join sys.indexes as i on ic.object_id = i.object_id and ic.index_id = i.index_id
where s.name = 'PsychoProductions'
order by t.name, c.column_id;

end
go
--verify:
--execute PsychoProductions.usp_GetSchemaDetails