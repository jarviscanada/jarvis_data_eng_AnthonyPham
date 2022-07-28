# Linux Cluster Monitoring Agent
The Linux Cluster Monitoring Agent is a project that creates a method for monitoring data collected into SQL tables running on a containerized PostgreSQL platform. Through a virtual machine hosted on the google cloud platform, a docker container is created which hosts the Postgres database. The database is populated with hardware specifications of the active computer via various bash scripts after which several SQL queries can be run to check on the integrity of the data received. The process of data collection is automated by use of a crontab, and the job is set to collect a new piece of data every minute. The whole application is built and managed with Git and stored on a Github repository. To summarize the following technologies were used to build the project: Linux, CentOS, Docker, PostgreSQL, Bash, Git, Github, Google Cloud Platform, Crontab, Intellij

#Quick Start
1. Start a psql instance using psql_docker.sh
```./scripts/psql_docker.sh start [db_username][db_password]```
2. Create tables using ddl.sql
```psql -h localhost -U postgres -d host_agent -f sql/ddl.sql```
3. Insert hardware specs data into the DB using host_info.sh
```./scripts/host_info.sh [hostname] [portnumber] [dbname] [psqluser] [psqlpassword]```
4. Insert hardware usage data into the DB using host_usage.sh
```./scripts/host_usage.sh [hostname] [portnumber] [dbname] [psqluser] [psqlpassword]```
5. Crontab setup
First access crontab by using ```Crontab -e```
and then insert the following statement
```* * * * * bash ~/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log```

#Implementation
The implementation of the project uses a virtual machine hosted on google cloud platform to host a docker container which then contains the PSQL database which stores all the data. The table is populated via a crontab which periodically inserts data which can then be tested and validated via SQL queries.

#Architecture
![Architecture](/home/centos/dev/jarvis_data_eng_anthonypham/linux_sql/assets/Architecture.jpg?raw=true)

#Scripts
1. ```psql_docker.sh```
This script creates, starts and stops the docker container for psql.
```./scripts/psql_docker.sh [create/start/stop] [db_username][db_password]```

2. ```host_info.sh```
This script gathers the host's hardware information including hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_memory, and timestamp and inserts it into its database.
```./scripts/host_info.sh [hostname] [portnumber] [dbname] [psqluser] [psqlpassword]```

3. ```host_usage.sh```
This script gathers the host's hardware usage informaton including timestamp, hostname, free_memory, cpu_idle, cpu_kernel, disk_io, and disk_available and inserts it into its database.
```./scripts/host_usage.sh [hostname] [portnumber] [dbname] [psqluser] [psqlpassword]```

4. ``` Crontab -e```
This Cron job runs the host_usage.sh script in order to gather the hardware usage data every minute and inserts it into its database.

5. ```queries.sql```
The queries are a way to verify and validate the data being stored in the databases. Queries.sql ocontains 3 main queries:
1) List of hosts and their total memory size grouped by CPU
2) List the average amount of memory used by each host in 5-minute intervals
3) Detect host failures in the localhost 
```psql -h localhost -U postgres -d host_agent -f sql/queries.sql```

#Database Modeling

host_info table:

| Attributes | Data Type | Contraints | Description |
| ---	     |	---	 |	---   |	---	    |
| id	     | SERIAL    | PRIMARY KEY| Auto-incremented indentifier for data in database |
| hostname   | VARCHAR   | NOT NULL UNIQUE | Host name |
| cpu_number | INTEGER   | NOT NULL   | Number of CPUS in host pc |
| cpu_architecture | VARCHAR |NOT NULL| The CPU architecture |
| cpu_model  | VARCHAR   | NOT NULL   | The CPU model |
| cpu_mhz    | FLOAT     | NOT NULL   | The CPU speed (MHz) |
| L2_cache   | VARCHAR   | NOT NULL   | L2 cache size (kB) |
| total_mem  | INTEGER   | NOT NULL   | Host pc's total memory (kB) |
| timestamp  | TIMESTAMP | NOT NULL   | Time of data insertion |

host_usage table:

| Attributes | Data Type | Contraints | Description |
| ---	     |	---	 |	---   |	---	    |
| timestamp  | TIMESTAMP | NOT NULL   | Time of data insertion |
| host_id    | SERIAL    | FOREIGN KEY| Referece to id in host_info table |
| memory_free| INTEGER   | NOT NULL   | Total unused memory (mB) |
| cpu_idle   | INTEGER   | NOT NULL   | CPU processer idle time (%) |
| cpu_kernel | INTEGER   | NOT NULL   | CPU kenrel run time (%) |
| disk_io    | INTEGER   | NOT NULL   | Number of disk I/O |
| disk_available| INTEGER   | NOT NULL   | Total available disk space (mB) |


#Test
The project was tested on the virtual machine hosted by GCP using a combination of CLI commands and via the terminal in Intellij. Data was tested using the SQL queries in queries.sql and all scripts were tested using execution in terminals.

#Deployment
All data was uploaded to the containerized PSQL database with Crontab periodically executing to populate said table. The source code for the project was uploaded to Github via git commands across several feature branches.

#Improvements
Create a GUI to visualize the data
Create more queries to more throughly test the data
Create feature to handle hardware updates





 







