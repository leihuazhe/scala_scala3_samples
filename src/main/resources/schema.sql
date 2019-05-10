MySQL [fin_ad_report]> desc ad_real_metrics;
+--------------+------------------+------+-----+-------------------+----------------+
| Field        | Type             | Null | Key | Default           | Extra          |
+--------------+------------------+------+-----+-------------------+----------------+
| id           | int(11) unsigned | NO   | PRI | NULL              | auto_increment |
| customer_id  | bigint(11)       | YES  |     | NULL              |                |
| metric_date  | varchar(255)     | YES  |     | NULL              |                |
| metric_hour  | int(11)          | YES  |     | NULL              |                |
| app_name     | varchar(255)     | YES  |     | NULL              |                |
| company_name | varchar(255)     | YES  |     | NULL              |                |
| metrics      | text             | YES  |     | NULL              |                |
| create_time  | timestamp        | YES  |     | CURRENT_TIMESTAMP |                |
+--------------+------------------+------+-----+-------------------+----------------+
