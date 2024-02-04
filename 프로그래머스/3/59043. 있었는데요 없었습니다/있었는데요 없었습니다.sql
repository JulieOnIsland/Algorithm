-- 코드를 입력하세요
SELECT ai.ANIMAL_ID, ai.NAME
From ANIMAL_INS ai left join ANIMAL_OUTS ao
on ai.ANIMAL_ID	= ao.ANIMAL_ID
where ai.DATETIME >= ao.DATETIME
order by ai.DATETIME ASC