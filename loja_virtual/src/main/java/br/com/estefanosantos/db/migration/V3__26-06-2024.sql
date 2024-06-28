CREATE TABLE public.qtd_acessos_end_point (
	nome_end_point character varying,
	qtd_acessos integer);
	
INSERT INTO public.qtd_acessos_end_point(
	nome_end_point, qtd_acessos)
	VALUES ('END-POINT-NOME-PESSOA-FISICA', 0);	
	
INSERT INTO public.qtd_acessos_end_point(
	nome_end_point, qtd_acessos)
	VALUES ('END-POINT-BUSCAR-PF-CPF', 0);
	
alter table qtd_acessos_end_point add constraint nome_end_point_unique UNIQUE (nome_end_point);