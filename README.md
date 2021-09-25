# Gatling statistics with Grafana

Gatling is an open-source load testing tool for web applications, designed for DevOps and Continuous Integration. 

Download Gatling
https://gatling.io/

Download Influxdb 1.8
https://portal.influxdata.com/downloads/

Download Grafana
https://grafana.com/grafana/download

we put the configuration files (galing.conf , influxdb.conf) 

**we generate an example test** 

<pre> [root@gatling bin]# ./gatling.sh 
GATLING_HOME is set to /opt/gatling-charts-highcharts-bundle-3.6.1
Choose a simulation number:
     [0] RecordedSimulation
     [1] altoSimulation
     [2] computerdatabase.BasicSimulation
     [3] computerdatabase.advanced.AdvancedSimulationStep01
     [4] computerdatabase.advanced.AdvancedSimulationStep02
     [5] computerdatabase.advanced.AdvancedSimulationStep03
     [6] computerdatabase.advanced.AdvancedSimulationStep04
     [7] computerdatabase.advanced.AdvancedSimulationStep05
     [8] gatlingdemostore.DemostoreSimulation
1
Select run description (optional)
altoariari
Simulation altoSimulation started... </pre>

**Gatling example**

![Alt text](images/test.png?raw=true "gatling")

We verify that the data has been injected into the influxdb database

<pre> [root@gatling bin]# influx
Connected to http://localhost:8086 version 1.8.9
InfluxDB shell version: 1.8.9
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
gatling,request=Contacto,simulation=altosimulation,status=all
gatling,request=Contacto,simulation=altosimulation,status=ko
gatling,request=Contacto,simulation=altosimulation,status=ok
gatling,request=Contacto_Redirect_1,simulation=altosimulation,status=all
gatling,request=Contacto_Redirect_1,simulation=altosimulation,status=ko
gatling,request=Contacto_Redirect_1,simulation=altosimulation,status=ok
gatling,request=Ecoturismo,simulation=altosimulation,status=all
gatling,request=Ecoturismo,simulation=altosimulation,status=ko
gatling,request=Ecoturismo,simulation=altosimulation,status=ok </pre>


** Grafana dashboard for testing **

![Alt text](images/gatlingdb1.png?raw=true "Dashboard1")

![Alt text](images/gatlingdb2.png?raw=true "Dashboard2")


