SELECT * FROM TABELA
WHERE ID IN ({0})
AND (? IS NULL OR NOME like '%' || ? || '%')
AND (? IS NULL OR IDADE like '%' || ? || '%')
AND (? IS NULL OR SEXO like '%' || ? || '%')
