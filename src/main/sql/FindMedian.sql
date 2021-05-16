select avg(num) as median
from (
    (
      select min(number) as num
      from (
          select  n1.number, sum(n2.frequency) as cum
          from  numbers n1
          left join numbers n2 on n1.number >= n2.number
          group by  n1.number
        ) as cumtab
      where
        cum >= (
          select  case when total % 2 = 0 then total / 2 else (total + 1)/ 2 end
          from (
              select sum(frequency) as total
              from numbers
          ) as totab
        )
    ) union (
        select min(number) as num
        from (
            select n1.number, sum(n2.frequency) as cum
            from numbers n1
            left join numbers n2 on n1.number >= n2.number
            group by n1.number
        ) as cumtab
        where
          cum >= (
            select case when total % 2 = 0 then total / 2 + 1 else (total + 1)/ 2 end
            from (
                select sum(frequency) as total
                from numbers
            ) as totab
        )
      )
  ) as temp
