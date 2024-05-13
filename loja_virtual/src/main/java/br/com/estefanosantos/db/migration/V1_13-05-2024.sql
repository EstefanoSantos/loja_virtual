select constraint_name from information_schema.constraint_column_usage
where table_name = 'usuario_role' and column_name = 'role_id' and 
constraint_name <> 'unique_role_user';

alter table usuario_role drop constraint "uk_qpqh5on1cqa0ktsitg2vhmirv";