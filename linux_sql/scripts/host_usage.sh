#!/bin/bash
#set up arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#validate arguments
if [ "$#" -ne 5 ]; then
  echo "Illegal number of parameters"
  exit 1
fi

vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)

timestamp=$(vmstat -t | awk '{print $18, $19}' | tail -n1 | xargs)
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | awk '{print $15}'| tail -n1 | xargs)
cpu_kernel=$(echo "$vmstat_mb" | awk '{print $14}'| tail -n1 | xargs)
disk_io=$(vmstat -d | awk '{print $10}' | tail -n1 | xargs)
disk_available=$(df -BM / | awk '{print ($4+0)}' | tail -n1 | xargs)

insert_stmt="INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel,disk_io, disk_available) VALUES('$timestamp', "$host_id", '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');"

#set up env var for the psql command
export PGPASSWORD=$psql_password

#Inserts data into the host_info table
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?

