-- 코드를 입력하세요
# SELECT ANIMAL_ID, NAME 
# from ANIMAL_OUTS;
# where ANIMAL_ID in (
#     SELECT ao.ANIMAL_ID
#     from ANIMAL_OUTS ao 
#     left join ANIMAL_INS ai
#     on ai.ANIMAL_ID = ao.ANIMAL_ID
# )

SELECT ao.ANIMAL_ID, ao.NAME
from ANIMAL_OUTS ao 
left join ANIMAL_INS ai on ai.ANIMAL_ID = ao.ANIMAL_ID
where ai.animal_id is null