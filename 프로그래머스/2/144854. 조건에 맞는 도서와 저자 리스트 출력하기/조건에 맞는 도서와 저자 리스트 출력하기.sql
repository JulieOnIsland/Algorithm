SELECT b.BOOK_ID, a.AUTHOR_NAME, 
DATE_FORMAT(b.PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE
from Book b inner join Author a
on b.author_id = a.author_id
where CATEGORY = '경제'
order by PUBLISHED_DATE ASC