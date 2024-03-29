SELECT FLAVOR
FROM (
    SELECT flavor,
           SUM(first_half_total_order) + SUM(july_total_order) AS total_order
    FROM (
        SELECT flavor, SUM(total_order) AS first_half_total_order, 0 AS july_total_order
        FROM FIRST_HALF
        GROUP BY flavor

        UNION ALL

        SELECT flavor, 0 AS first_half_total_order, SUM(total_order) AS july_total_order
        FROM JULY
        GROUP BY flavor
    ) AS combined_orders
    GROUP BY flavor
    ORDER BY total_order DESC
    LIMIT 3
) AS top_flavors;