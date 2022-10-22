# Gatling statistics with Grafana

 [![Docker pulls](https://img.shields.io/docker/pulls/010203901/gatling)](https://hub.docker.com/r/010203901/gatling)


Gatling is an open-source load testing tool for web applications, designed for DevOps and Continuous Integration. 

Download Gatling
https://gatling.io/

Download Influxdb 1.8
https://portal.influxdata.com/downloads/

Download Grafana
https://grafana.com/grafana/download

we put the configuration files (galing.conf , influxdb.conf) 

**we generate an example test** 

Start 
´´´ 
[root@gatling bin]# docker-compose up -d 
´´´ 

<pre> [root@gatling bin]# docker-compose exec -T gatling /opt/gatling/bin/gatling.sh -rm local -sf /opt/gatling/user-files/ -s demostore -rf /opt/gatling/results/
Simulation demostore started...


================================================================================
---- Global Information --------------------------------------------------------
> request count                                       1800 (OK=1800   KO=0     )
> min response time                                    172 (OK=172    KO=-     )
> max response time                                   2626 (OK=2626   KO=-     )
> mean response time                                  1156 (OK=1156   KO=-     )
> std deviation                                       1057 (OK=1057   KO=-     )
> response time 50th percentile                        298 (OK=298    KO=-     )
> response time 75th percentile                       2385 (OK=2385   KO=-     )
> response time 95th percentile                       2483 (OK=2483   KO=-     )
> response time 99th percentile                       2508 (OK=2508   KO=-     )
> mean requests/sec                                   9.73 (OK=9.73   KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                          1048 ( 58%)
> t ≥ 800 ms <br> t < 1200 ms                            0 (  0%)
> t ≥ 1200 ms                                          752 ( 42%)
> failed                                                 0 (  0%)
================================================================================

Reports generated in 0s.
Please open the following file: file:///opt/gatling/results/demostore-20220902031902287/index.html

</pre>

**Gatling example**

![Alt text](images/test.png?raw=true "gatling")

We verify that the data has been injected into the influxdb database

<pre> [root@gatling ]# docker exec -it  influxdb bash 
root@e0a5814f43cc:/# influx
Connected to http://localhost:8086 version 1.8.10
InfluxDB shell version: 1.8.10
> show databases 
name: databases
name
----
_internal
gatlingdb
> use gatlingdb
Using database gatlingdb
> show series
key
---
gatling,request=Home,simulation=demostore,status=all
gatling,request=Home,simulation=demostore,status=ko
gatling,request=Home,simulation=demostore,status=ok
gatling,request=allRequests,simulation=demostore,status=all
gatling,request=allRequests,simulation=demostore,status=ko
gatling,request=allRequests,simulation=demostore,status=ok
gatling.users,request=allUsers,simulation=demostore
gatling.users,request=demostore,simulation=demostore
 </pre>


**Grafana dashboard for testing**

![Alt text](images/gatlingdb1.png?raw=true "Dashboard1")


**download grafana dashboard**

https://grafana.com/grafana/dashboards/15046
